package dkurata38.afb.web.security

import dkurata38.afb.domain.user.User
import dkurata38.afb.domain.user.UserJpaRepository
import org.springframework.social.connect.Connection
import org.springframework.social.connect.ConnectionSignUp
import org.springframework.stereotype.Component
import java.util.*


@Component
class SocialSignUp(private val userRepository: UserJpaRepository): ConnectionSignUp {
    /**
     * Sign up a new user of the application from the connection.
     * @param connection the connection
     * @return the new user id. May be null to indicate that an implicit local user profile could not be created.
     */
    override fun execute(connection: Connection<*>): String {
        val userProfile = connection.fetchUserProfile()
        var userId: String
        while(true){
            val tmpUserId = UUID.randomUUID().toString()
            val user: User? = userRepository.findOne(tmpUserId)
            if(user == null){
                userId = tmpUserId
                break
            }
        }
        val user = User.registerSocialUser(userId, userProfile.username)
        val registeredUser: User = userRepository.saveAndFlush(user)
        return registeredUser.getUserId()
    }
}