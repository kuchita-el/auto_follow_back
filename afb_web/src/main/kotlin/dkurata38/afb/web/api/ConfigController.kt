package dkurata38.afb.web.api

import dkurata38.afb.web.usecase.automateuser.AutomateUserUseCase
import dkurata38.afb.web.usecase.followkeyword.FollowKeywordUseCase
import dkurata38.afb.web.usecase.security.UserSession
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpSession

@RestController
@RequestMapping(value = ["/api/config"])
class ConfigController(private val automateUserUseCase: AutomateUserUseCase,
                       private val followKeywordUseCase: FollowKeywordUseCase,
                       private val httpSession: HttpSession) {

    @GetMapping(value = ["/"])
    fun show(): ConfigResponse {
        val userSession = httpSession.getAttribute("userSession") as? UserSession?

        if (userSession == null || !userSession.authenticated()) {
            return ConfigResponse(401, "", null)
        }

        val userId = userSession.getUser()!!.getId()!!
        val followKeyword = followKeywordUseCase.getCurrentConfig(userId)
        val isScheduled = automateUserUseCase.isEnabledAutomation(userId)

        return ConfigResponse(200, followKeyword.getKeyword(), isScheduled)
    }

    @PatchMapping(value = ["/"])
    fun update(@RequestBody config: ConfigRequest): ApiResponse {
        val userSession = httpSession.getAttribute("userSession") as? UserSession?

        if (userSession == null || !userSession.authenticated()) {
            return ApiResponse(401)
        }

        if (config == null || config.keyword.isNullOrEmpty()) {
            return ApiResponse(400)
        }

        val userId = userSession.getUser()!!.getId()!!
        followKeywordUseCase.configure(userId, config.keyword)
        automateUserUseCase.configureAutomation(userId, config.scheduled)

        return ApiResponse(200)
    }
}