package dkurata38.afb.domain.followkeyword

import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table


@Entity
@Table(name = "follow_keyword")
class FollowKeyword() {
    @EmbeddedId
    private val followKeywordKey: FollowKeywordKey? = null
    private val keyword: String? = null

    fun getFollowKeywordKey(): FollowKeywordKey? {
        return followKeywordKey
    }

    fun getKeyword(): String? {
        return keyword
    }
}