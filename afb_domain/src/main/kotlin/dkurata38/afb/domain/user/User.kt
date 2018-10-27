package dkurata38.afb.domain.user

import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "sns_user")
class User internal constructor() : Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE, generator = "seq_user_id")
    @SequenceGenerator(name = "seq_user_id", sequenceName = "seq_user_id")
    private var id: Int? = null

    @Embedded
    private var snsLoginId: SnsLoginId? = null

    @Column(name="display_name")
    private var displayName: String? = null

    @Embedded
    private var token: Token? = null
    constructor(snsLoginId: SnsLoginId, displayName: String, token: Token):this(){
        this.snsLoginId = snsLoginId
        this.displayName = displayName
        this.token = token
    }

    fun getId(): Int? {
        return id
    }

    fun getSndLoginId(): SnsLoginId {
        return snsLoginId!!
    }

    fun getDisplayName(): String {
        return displayName!!
    }

    fun getToken(): Token {
        return token!!
    }

    fun updateToken(token: Token) {
        this.token = token
    }
}