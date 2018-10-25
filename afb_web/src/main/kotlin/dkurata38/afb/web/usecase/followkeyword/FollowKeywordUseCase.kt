package dkurata38.afb.web.usecase.followkeyword

import dkurata38.afb.domain.followkeyword.FollowKeyword
import dkurata38.afb.domain.followkeyword.FollowKeywordJpaRepository
import dkurata38.afb.domain.userconncetion.UserConnectionKey
import org.springframework.stereotype.Service

@Service
class FollowKeywordUseCase(private val followKeywordJpaRepository: FollowKeywordJpaRepository) {
    fun configure(userId: String, keyword: String){
        val userConnectionKey = UserConnectionKey(userId, "twitter")
        var followKeyword: FollowKeyword? = followKeywordJpaRepository.findByUserConnectionKey(userConnectionKey)
        if (followKeyword == null){
            followKeyword = FollowKeyword(userConnectionKey, keyword)
        } else {
            followKeyword.configure(keyword)
        }
        followKeywordJpaRepository.saveAndFlush(followKeyword)
    }
}