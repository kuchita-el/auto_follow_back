package dkurata38.afb.domain.userconncetion

import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Token internal constructor() {
    @Column(name = "accesstoken")
    private var accessToken: String? = null

    @Column(name = "secret")
    private var secret: String? = null

    @Column(name = "refreshtoken")
    private var refreshtoken: String? = null

    @Column(name = "expiretime")
    private var expiretime: LocalDateTime? = null

    constructor(accessToken: String, secret: String): this(){
        this.accessToken = accessToken
        this.secret = secret
    }

    fun getAccessToken(): String = accessToken!!

    fun getSecret(): String = accessToken!!
}