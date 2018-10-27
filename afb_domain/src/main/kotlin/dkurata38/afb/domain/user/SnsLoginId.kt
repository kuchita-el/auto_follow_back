package dkurata38.afb.domain.user

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class SnsLoginId internal constructor(): Serializable{

    @Column(name = "snsUserId")
    private lateinit var snsUserId: String

    constructor(sns_user_id: String): this(){
        this.snsUserId = sns_user_id
    }

    fun getSnsUserId(): String {
        return snsUserId
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SnsLoginId

        if (snsUserId != other.snsUserId) return false

        return true
    }

    override fun hashCode(): Int {
        return snsUserId.hashCode()
    }
}