//package dkurata38.afb.web.security
//
//import dkurata38.afb.domain.user.User
//import dkurata38.afb.domain.user.UserFactory
//import dkurata38.afb.domain.user.UserRepository
//import org.springframework.social.connect.Connection
//import org.springframework.social.connect.ConnectionSignUp
//import org.springframework.stereotype.Component
//import java.util.*
//
//
//@Component
//class ConnectionSignUp(private val userRepository: UserRepository): ConnectionSignUp {
//    /**
//     * Sign up a new user of the application from the connection.
//     * @param connection the connection
//     * @return the new user id. May be null to indicate that an implicit local user profile could not be created.
//     */
//    override fun execute(connection: Connection<*>): String {
//        val userProfile = connection.fetchUserProfile()
//        val user = UserFactory.registSocialUser(userProfile.username)
//        val registeredUser: User = userRepository.insert(user)
//        return registeredUser.getId()!!.toString()
//    }
//}