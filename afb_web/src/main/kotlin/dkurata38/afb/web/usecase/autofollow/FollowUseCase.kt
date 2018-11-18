package dkurata38.afb.web.usecase.autofollow

import dkurata38.afb.domain.autofollow.AutoFollowService
import dkurata38.afb.domain.twitteruser.TwitterUser
import dkurata38.afb.domain.user.Token
import org.springframework.stereotype.Service

@Service
class FollowUseCase(private val autoFollowService: AutoFollowService) {
    fun autoFollow(token: Token, keyword: String): List<TwitterUser> {
        //keyword取得
        return autoFollowService.autoFollow(token, keyword)
    }
}