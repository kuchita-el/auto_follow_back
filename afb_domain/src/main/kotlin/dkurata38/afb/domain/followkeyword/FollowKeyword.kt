package dkurata38.afb.domain.followkeyword

import dkurata38.afb.domain.user.SnsLoginId
import java.io.Serializable
import javax.persistence.*


@Entity
@Table(name = "follow_keyword")
class FollowKeyword internal constructor() :Serializable{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_follow_keyword_id")
    @SequenceGenerator(name = "seq_follow_keyword_id", sequenceName = "seq_follow_keyword_id")
    private var id: Int? = null

    @Column(name = "user_id")
    private var userId: Int? = null

    private var keyword: String? = null

    constructor(userId: Int, keyword: String):this(){
        this.userId = userId
        this.keyword = keyword
    }

    fun getUserId(): Int {
        return userId!!
    }

    fun getKeyword(): String {
        return keyword!!
    }

    fun configure(keyword: String) {
        this.keyword = keyword
    }

    companion object {
        fun defaultKeyword(userId: Int): FollowKeyword {
            val followKeyword = FollowKeyword()
            followKeyword.userId = userId
            followKeyword.keyword = "エンジニア"
            return followKeyword
        }
    }
}