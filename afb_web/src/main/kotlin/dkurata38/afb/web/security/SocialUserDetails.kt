package dkurata38.afb.web.security

import dkurata38.afb.domain.user.User
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.social.security.SocialUser

class SocialUserDetails(private val user: User): SocialUser(
        user.getUserName(), user.getEncodedPassword(), AuthorityUtils.createAuthorityList("ROLE_USER")) {

    override fun getUserId(): String {
        return user.getUserId()
    }

}