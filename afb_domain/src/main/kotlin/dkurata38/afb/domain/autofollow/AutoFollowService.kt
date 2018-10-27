package dkurata38.afb.domain.autofollow

import dkurata38.afb.domain.twitteruser.TwitterClient
import dkurata38.afb.domain.user.Token
import org.springframework.stereotype.Service

@Service
class AutoFollowService(private val twitterClient: TwitterClient) {
	fun autoFollow(token: Token, followKeyWord: String){
        // search followerId
        val accessToken: String = token.getAccessToken()
        val secret: String = token.getSecret()
        val followersIDs = twitterClient.getFollowersIds(accessToken, secret)
        val friendsIDs = twitterClient.getFriendsIds(accessToken, secret)
        System.out.println("followersCount: ${followersIDs.size}, friendsCount: ${friendsIDs.size}")

        val oneWayFriendIds = followersIDs.filterNot { e -> friendsIDs.contains(e) }
        System.out.println("oneWayFriendsCount: ${oneWayFriendIds.size}")
        val oneWayFriends = twitterClient.lookUpUsers(accessToken, secret, oneWayFriendIds)
        System.out.println("oneWayFriendsCount: ${oneWayFriends.size}")

        //if not send follow request and contains keyword
        oneWayFriends
                .filter{f -> (!f.sentRequest()) && f.matchDescriptionTo(followKeyWord)}
                .forEach{
					f -> twitterClient.createFriendShip(accessToken, secret, f.getId())
					System.out.println("follow " + f.getScreanName())
				}
	}
}