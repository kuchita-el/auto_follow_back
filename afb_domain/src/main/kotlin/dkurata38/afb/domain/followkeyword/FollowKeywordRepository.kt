package dkurata38.afb.domain.followkeyword

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FollowKeywordRepository : JpaRepository<FollowKeyword, Int> {
    fun findByUserId(userId: Int): FollowKeyword?
}