package dkurata38.afb.batch.autofollow

import dkurata38.afb.domain.followkeyword.FollowKeyword
import dkurata38.afb.domain.user.Token

class AutoFollowResouceDto(private val followKeyword: FollowKeyword, private val token: Token) {
    fun getToken(): Token = token
    fun getFollowKeyword(): FollowKeyword = followKeyword
}