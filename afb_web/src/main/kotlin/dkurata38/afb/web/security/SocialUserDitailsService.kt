//package dkurata38.afb.web.security
//
//import dkurata38.afb.domain.user.UserRepository
//import org.springframework.social.security.SocialUserDetails
//import org.springframework.social.security.SocialUserDetailsService
//
//class SocialUserDitailsService(private val userRepository: UserRepository): SocialUserDetailsService {
//    /**
//     * @see UserDetailsService.loadUserByUsername
//     * @param userId the user ID used to lookup the user details
//     * @return the SocialUserDetails requested
//     */
//    override fun loadUserByUserId(userId: String): SocialUserDetails {
//        val user = userRepository.findById(userId.toInt())
//        return dkurata38.afb.web.security.SocialUserDetails(user!!)
//    }
//}