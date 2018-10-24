package dkurata38.afb.domain.user

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
class User internal constructor() : Serializable {

    @Id
    @Column(name = "userid")
    private var userId: String? = null
    @Column(name="username")
    private var userName: String? = null
    @Column(name="password")
    private var encodedPassword: String? = null
    constructor(userId: String, userName: String, encodedPassword: String):this(){
        this.userId = userId
        this.userName = userName
        this.encodedPassword = encodedPassword
    }

    fun getUserId(): String {
        return userId!!
    }

    fun getUserName(): String {
        return userName!!
    }

    fun getEncodedPassword(): String{
        return encodedPassword!!
    }

    companion object {
        fun registerSocialUser(userId: String, userName: String): User{
            val encodedPassword: String = UUID.randomUUID().toString()
            return User(userId, userName, encodedPassword)
        }
    }
}