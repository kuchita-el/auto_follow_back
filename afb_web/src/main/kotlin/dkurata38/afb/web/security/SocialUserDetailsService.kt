package dkurata38.afb.web.security

import dkurata38.afb.domain.user.UserJpaRepository
import org.springframework.social.security.SocialUserDetails
import org.springframework.social.security.SocialUserDetailsService
import org.springframework.stereotype.Service

@Service
class SocialUserDetailsService(private val userRepository: UserJpaRepository): SocialUserDetailsService {
    /**
     * @see UserDetailsService.loadUserByUsername
     * @param userId the user ID used to lookup the user details
     * @return the SocialUserDetails requested
     */
    override fun loadUserByUserId(userId: String): SocialUserDetails {
        val user = userRepository.findOne(userId)
        return dkurata38.afb.web.security.SocialUserDetails(user!!)
    }
}