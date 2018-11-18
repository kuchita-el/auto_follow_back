package dkurata38.afb.web.api

import dkurata38.afb.web.usecase.followkeyword.FollowKeywordUseCase
import dkurata38.afb.web.usecase.security.UserSession
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpSession

@RestController
@RequestMapping(value = ["/api/follow_keyword"])
class FollowKeywordController(private val followKeywordUseCase: FollowKeywordUseCase,
                              private val httpSession: HttpSession) {

    @GetMapping(value = ["/"])
    fun show(): FollowKeywordResponse {
        val userSession = httpSession.getAttribute("userSession") as? UserSession?
        if (userSession == null || !userSession.authenticated()) {
            return FollowKeywordResponse(401, "")
        }
        val followKeyword = followKeywordUseCase.getCurrentConfig(userSession.getUser()!!.getId()!!)
        return FollowKeywordResponse(200, followKeyword.getKeyword())
    }

    @PatchMapping(value = ["/"], consumes = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun update(@RequestBody keyword: KeywordRequest?): ApiResponse {
        val userSession = httpSession.getAttribute("userSession") as? UserSession?
        if (userSession == null || !userSession.authenticated()) {
            return ApiResponse(401)
        }

        if (keyword == null || keyword.keyword.isNullOrEmpty()) {
            return ApiResponse(400)
        }

        followKeywordUseCase.configure(userSession.getUser()!!.getId()!!, keyword.keyword)
        return ApiResponse(200)
    }
}