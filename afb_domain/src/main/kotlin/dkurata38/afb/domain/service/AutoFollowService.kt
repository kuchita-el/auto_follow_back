package dkurata38.afb.domain.service

import dkurata38.afb.domain.client.TwitterClient
import org.springframework.stereotype.Service

@Service
class AutoFollowService(private val twitterClient: TwitterClient) {
	fun autoFollow(token: String, secret: String, followKeyWord: String){
        // search followerId
        val followersIDs = twitterClient.getFollowersIds(token, secret)
        val friendsIDs = twitterClient.getFriendsIds(token, secret)
        System.out.println("followersCount: ${followersIDs.size}, friendsCount: ${friendsIDs.size}")

        val oneWayFriendIds = followersIDs.filterNot { e -> friendsIDs.contains(e) }
        System.out.println("oneWayFriendsCount: ${oneWayFriendIds.size}")
        val oneWayFriends = twitterClient.lookUpUsers(token, secret, oneWayFriendIds)
        System.out.println("oneWayFriendsCount: ${oneWayFriends.size}")

        //if not send follow request and contains keyword
        oneWayFriends
                .filter{f -> (!f.sentRequest()) && f.matchDescriptionTo(followKeyWord)}
                .forEach{
					f -> twitterClient.createFriendShip(token, secret, f.getId())
					System.out.println("follow " + f.getScreanName())
				}
	}
}