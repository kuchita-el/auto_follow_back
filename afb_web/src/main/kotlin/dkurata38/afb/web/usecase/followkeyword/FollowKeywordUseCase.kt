package dkurata38.afb.web.usecase.followkeyword

import dkurata38.afb.domain.followkeyword.FollowKeyword
import dkurata38.afb.domain.followkeyword.FollowKeywordJpaRepository
import org.springframework.stereotype.Service

@Service
class FollowKeywordUseCase(private val followKeywordJpaRepository: FollowKeywordJpaRepository) {
    fun configure(userId: String, keyword: String){
        FollowKeyword()
    }
}