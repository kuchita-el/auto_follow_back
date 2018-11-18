package dkurata38.afb.web.api

import dkurata38.afb.web.usecase.autofollow.FollowUseCase
import dkurata38.afb.web.usecase.security.UserSession
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpSession

@RestController
@RequestMapping(value = ["/api/follow"])
class FollowController(private val httpSession: HttpSession, private val autoFollowUseCase: FollowUseCase) {

    @PostMapping(value = ["/"], consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun execute(@RequestBody keyword: KeywordRequest?): PostFollowResponse {
        val userSession = httpSession.getAttribute("userSession") as? UserSession?

        if (userSession == null || !userSession.authenticated()) {
            return PostFollowResponse(401, 0)
        }

        if (keyword == null || keyword.keyword.isNullOrEmpty()) {
            return PostFollowResponse(400, 0)
        }

        val followedUser =
                autoFollowUseCase.autoFollow(userSession.getUser()!!.getToken(), keyword.keyword)
        return PostFollowResponse(200, followedUser.size)
    }
}