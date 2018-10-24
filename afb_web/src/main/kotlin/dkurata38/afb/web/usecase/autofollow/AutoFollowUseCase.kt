package dkurata38.afb.web.usecase.autofollow

import dkurata38.afb.domain.autofollow.AutoFollowService
import dkurata38.afb.domain.followkeyword.FollowKeywordJpaRepository
import org.springframework.stereotype.Service

@Service
class AutoFollowUseCase(private val followKeywordRepository: FollowKeywordJpaRepository,
                        private val autoFollowService: AutoFollowService) {
    fun autoFollow(userId: String): Unit {
        //keyword取得

        //
    }
}