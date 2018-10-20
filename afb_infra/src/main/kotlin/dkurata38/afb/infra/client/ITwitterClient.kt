package dkurata38.afb.infra.client

import dkurata38.afb.domain.twitteruser.TwitterClient
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.auth.AccessToken

import dkurata38.afb.domain.twitteruser.TwitterUser
import java.util.stream.Collectors
import org.springframework.stereotype.Component

@Component
class ITwitterClient: TwitterClient {
	override fun getFollowersIds(token: String, secret: String): List<Long> {
		val twitter = createTwitterInstance(token, secret)
		return twitter.getFollowersIDs(-1).iDs.asList()
	}
	
	override fun getFriendsIds(token: String, secret: String): List<Long> {
		val twitter = createTwitterInstance(token, secret)
		return twitter.getFriendsIDs(-1).iDs.asList()
	}
	
	override fun lookUpUsers(token: String, secret: String, ids: List<Long>): List<TwitterUser> {
		val twitter = createTwitterInstance(token, secret)
		val chunkedIds = ids.chunked(50)
		val users = chunkedIds.map { e -> twitter.lookupUsers(*e.toLongArray()) }.flatten()
		val twitterUsers = users.stream().map{u -> TwitterUser(u.id, u.screenName, u.description, u.isFollowRequestSent) }.collect(Collectors.toList())
		return twitterUsers
	}
	
	override fun createFriendShip(token: String, secret: String, id: Long) {
		val twitter = createTwitterInstance(token, secret)
		twitter.createFriendship(id)
	}
	
	private fun createTwitterInstance(token: String, secret: String): Twitter {
		val accessToken: AccessToken = AccessToken(token, secret)
		val twitter: Twitter = TwitterFactory().getInstance(accessToken)
		return twitter		
	}
}