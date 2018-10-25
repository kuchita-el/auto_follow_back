package dkurata38.afb.domain.followkeyword

import dkurata38.afb.domain.userconncetion.UserConnectionKey
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FollowKeywordJpaRepository: JpaRepository<FollowKeyword, Int> {

    override fun getOne(followKeywordKey: Int): FollowKeyword?

    fun findByUserConnectionKey(userConnectionKey: UserConnectionKey): FollowKeyword?

    fun saveAndFlush(followKeyword: FollowKeyword): FollowKeyword
}