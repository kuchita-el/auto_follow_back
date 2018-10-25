package dkurata38.afb.domain.userconncetion

import org.springframework.data.jpa.repository.JpaRepository

interface UserConnectionRepository: JpaRepository<UserConnection, UserConnectionKey> {
    override fun findOne(userConnectionKey: UserConnectionKey): UserConnection?
}