package dkurata38.afb.domain.followkeyword

import dkurata38.afb.domain.userconncetion.UserConnectionKey
import javax.persistence.*


@Entity
@Table(name = "follow_keyword")
class FollowKeyword internal constructor() {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private var id: Int? = null

    @Embedded
    private var userConnectionKey: UserConnectionKey? = null
    private var keyword: String? = null

    constructor(userConnectionKey: UserConnectionKey, keyword: String):this(){
        this.userConnectionKey = userConnectionKey
        this.keyword = keyword
    }

    fun getId(): Int? {
        return id
    }

    fun getKeyword(): String {
        return keyword!!
    }

    fun configure(keyword: String): Unit {
        this.keyword = keyword
    }
}