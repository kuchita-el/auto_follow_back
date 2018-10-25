package dkurata38.afb.domain.userconncetion

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class UserConnectionKey internal constructor(): Serializable {

    @Column(name = "userid")
    private var userId: String? = null
    @Column(name = "providerid")
    private var providerId: String? = null

    constructor(userId: String, providerId: String): this(){
        this.userId = userId
        this.providerId = providerId
    }
}