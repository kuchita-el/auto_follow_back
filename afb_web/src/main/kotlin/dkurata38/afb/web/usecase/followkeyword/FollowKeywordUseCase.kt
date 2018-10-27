package dkurata38.afb.web.usecase.followkeyword

import dkurata38.afb.domain.followkeyword.FollowKeyword
import dkurata38.afb.domain.followkeyword.FollowKeywordRepository
import org.springframework.stereotype.Service

@Service
class FollowKeywordUseCase(private val followKeywordJpaRepository: FollowKeywordRepository) {
    fun configure(userId: Int, keyword: String){
        //TODO findByRepository
        var followKeyword: FollowKeyword? = null
        if (followKeyword == null){
            followKeyword = FollowKeyword(userId, keyword)
        } else {
            followKeyword.configure(keyword)
        }
        followKeywordJpaRepository.saveAndFlush(followKeyword)
    }
}