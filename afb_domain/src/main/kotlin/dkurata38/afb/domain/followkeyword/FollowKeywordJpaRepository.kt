package dkurata38.afb.domain.followkeyword

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FollowKeywordJpaRepository: JpaRepository<FollowKeyword, FollowKeywordKey> {

    override fun getOne(followKeywordKey: FollowKeywordKey): FollowKeyword

    fun saveAndFlush(followKeyword: FollowKeyword): FollowKeyword
}