package dkurata38.afb.domain.user

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class Token internal constructor() : Serializable {
    @Column(name = "access_token")
    private var accessToken: String? = null

    @Column(name = "secret")
    private var secret: String? = null

    constructor(accessToken: String, secret: String) : this() {
        this.accessToken = accessToken
        this.secret = secret
    }

    fun getAccessToken(): String = accessToken!!

    fun getSecret(): String = secret!!
}