package dkurata38.afb.domain.twitteruser

import org.springframework.stereotype.Component

@Component
interface TwitterClient {
    fun getFollowersIds(token: String, secret: String): List<Long>

    fun getFriendsIds(token: String, secret: String): List<Long>

    fun lookUpUsers(token: String, secret: String, ids: List<Long>): List<TwitterUser>

    fun createFriendShip(token: String, secret: String, id: Long)

//	fun getFollowersIds(accessToken: Token): List<Long>
//
//	fun getFriendsIds(accessToken: Token): List<Long>
//
//	fun lookUpUsers(accessToken: Token, ids: List<Long>): List<TwitterUser>
//
//	fun createFriendShip(accessToken: Token, id: Long)
}