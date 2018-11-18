package dkurata38.afb.domain.automateuser

import org.springframework.data.jpa.repository.JpaRepository

interface AutomateUserRepository : JpaRepository<AutomateUser, Int> {
    fun findByUserId(userId: Int): AutomateUser?
}