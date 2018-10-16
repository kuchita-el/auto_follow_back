package dkurata38.afb.domain.client

import dkurata38.afb.domain.entity.TwitterUser

interface TwitterClient {
	fun getFollowersIds(token: String, secret: String): List<Long>
	
	fun getFriendsIds(token: String, secret: String): List<Long>
	
	fun lookUpUsers(token: String, secret: String, ids: List<Long>): List<TwitterUser> 
	
	fun createFriendShip(token: String, secret: String, id: Long)
}