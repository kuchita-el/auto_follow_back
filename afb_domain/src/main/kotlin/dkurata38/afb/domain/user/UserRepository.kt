package dkurata38.afb.domain.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {
    fun findBySnsLoginId(snsLoginId: SnsLoginId): User?
}