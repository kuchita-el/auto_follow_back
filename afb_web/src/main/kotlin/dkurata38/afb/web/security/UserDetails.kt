package dkurata38.afb.web.security

import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.User

class UserDetails(private val user:dkurata38.afb.domain.user.User): User(
        user.getUserName(), user.getEncodedPassword(), AuthorityUtils.createAuthorityList("ROLE_USER")) {

}