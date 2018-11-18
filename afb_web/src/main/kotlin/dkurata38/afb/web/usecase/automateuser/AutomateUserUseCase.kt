package dkurata38.afb.web.usecase.automateuser

import dkurata38.afb.domain.automateuser.AutomateUser
import dkurata38.afb.domain.automateuser.AutomateUserRepository
import org.springframework.stereotype.Service

@Service
class AutomateUserUseCase(private val automateUserRepository: AutomateUserRepository) {
    fun configureAutomation(userId: Int, scheduled: Boolean) {
        if (scheduled) {
            enableAutomation(userId)
        } else {
            disableAutomation(userId)
        }
    }

    private fun enableAutomation(userId: Int) {
        var automateUser = automateUserRepository.findByUserId(userId)
        if (automateUser == null) {
            automateUser = AutomateUser(userId)
            automateUserRepository.saveAndFlush(automateUser)
        }
    }

    private fun disableAutomation(userId: Int) {
        var automateUser = automateUserRepository.findByUserId(userId)
        if (automateUser != null) {
            automateUserRepository.delete(automateUser)
        }
    }

    fun isEnabledAutomation(userId: Int): Boolean {
        val automateUser = automateUserRepository.findByUserId(userId)
        return automateUser != null
    }

}