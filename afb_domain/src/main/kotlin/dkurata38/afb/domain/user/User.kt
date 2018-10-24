package dkurata38.afb.domain.user

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "users")
class User(
        @Id
        @Column(name = "userid")
        private val userId: String,
        @Column(name="username")
        private val userName: String,
        @Column(name="password")
        private val encodedPassword: String) : Serializable {

    fun getUserId(): String {
        return userId
    }

    fun getUserName(): String {
        return userName
    }

    fun getEncodedPassword(): String{
        return encodedPassword
    }

    companion object {
        fun registerSocialUser(userId: String, userName: String): User{
            val encodedPassword: String = UUID.randomUUID().toString()
            return User(userId, userName, encodedPassword)
        }
    }
}