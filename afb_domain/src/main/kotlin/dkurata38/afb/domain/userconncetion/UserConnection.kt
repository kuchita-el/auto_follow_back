package dkurata38.afb.domain.userconncetion

import javax.persistence.*

@Entity
@Table(name = "userConnection")
class UserConnection internal constructor(){
    @EmbeddedId
    private var userConnectionKey: UserConnectionKey? = null

    @Column(name = "provideruserid")
    private var providerUserId: String? = null

    @Column(name = "displayname")
    private var displayName: String? = null

    @Embedded
    private var token: Token? = null

    fun getUserConnectionKey(): UserConnectionKey = userConnectionKey!!

    fun getDisplayName(): String = displayName!!

    fun getToken(): Token = token!!
}