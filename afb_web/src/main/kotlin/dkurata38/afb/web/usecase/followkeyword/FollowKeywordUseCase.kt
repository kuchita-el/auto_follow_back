package dkurata38.afb.web.usecase.followkeyword

import dkurata38.afb.domain.followkeyword.FollowKeyword
import dkurata38.afb.domain.followkeyword.FollowKeywordRepository
import org.springframework.stereotype.Service

@Service
class FollowKeywordUseCase(private val followKeywordJpaRepository: FollowKeywordRepository) {
    fun configure(userId: Int, keyword: String): FollowKeyword{
        var followKeyword: FollowKeyword? = followKeywordJpaRepository.findByUserId(userId)
        if (followKeyword == null){
            followKeyword = FollowKeyword(userId, keyword)
        } else {
            followKeyword.configure(keyword)
        }
        val savedKeyword = followKeywordJpaRepository.saveAndFlush(followKeyword)
        return savedKeyword
    }

    fun getCurrentConfig(userId: Int): FollowKeyword{
        var followKeyword: FollowKeyword? = followKeywordJpaRepository.findByUserId(userId)
        if (followKeyword == null){
            followKeyword = FollowKeyword.defaultKeyword(userId)
        }
        return followKeyword
    }
}