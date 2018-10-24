package dkurata38.afb.domain.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository: JpaRepository<User, String>{
    fun saveAndFlush(user: User): User

    fun findByUserName(userName: String): User?

    override fun findOne(userId: String): User?
}