package dkurata38.afb.domain.followkeyword

interface FollowKeywordRepository {
    fun findByFollowKeywordKey(followKeywordKey: FollowKeywordKey): FollowKeyword

    fun save()
}