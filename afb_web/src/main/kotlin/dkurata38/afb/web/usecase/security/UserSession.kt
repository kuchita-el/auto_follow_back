package dkurata38.afb.web.usecase.security

import dkurata38.afb.domain.user.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils

class UserSession internal constructor(){

    private var user: User? = null

    constructor(user: User): this(){
        this.user = user
    }

    fun getUser(): User? = user

    fun authenticated(): Boolean = user != null
}