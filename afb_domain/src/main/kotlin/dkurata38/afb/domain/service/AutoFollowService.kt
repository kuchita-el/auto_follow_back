package dkurata38.afb.domain.service

import org.springframework.stereotype.Service
import dkurata38.afb.domain.client.ITwitterClient

@Service
class AutoFollowService(private val twitterClient: ITwitterClient) {
	fun autoFollow(token: String, secret: String, followKeyWord: String){
        // search followerId
        val followersIDs = twitterClient.getFollowersIds(token, secret)
        val friendsIDs = twitterClient.getFriendsIds(token, secret)
        System.out.println("followersCount: ${followersIDs.size}, friendsCount: ${friendsIDs.size}")

        val oneWayFriendIds = followersIDs.filterNot { e -> friendsIDs.contains(e) }
        val oneWayFriends = twitterClient.lookUpUsers(token, secret, oneWayFriendIds)
        System.out.println("oneWayFriendsCount: ${oneWayFriends.size}")

        //if not send follow request and contains keyword
        oneWayFriends
                .stream()
                .filter{f -> !f.sentRequest() && f.matchDescriptionTo(followKeyWord)}
                .peek{
					f -> twitterClient.createFriendShip(token, secret, f.getId())
					System.out.println("follow " + f.getScreanName())
				}
	}
}