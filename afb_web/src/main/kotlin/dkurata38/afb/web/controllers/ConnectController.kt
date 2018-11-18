package dkurata38.afb.web.controllers

import dkurata38.afb.web.usecase.security.TwitterAuthenticationUseCase
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.servlet.view.RedirectView
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.auth.RequestToken
import javax.servlet.http.HttpSession

@Controller
@RequestMapping(value = ["/connect"])
class ConnectController(private val twitterAuthenticationUseCase: TwitterAuthenticationUseCase, private val httpSession: HttpSession) {

    @PostMapping(value = ["/twitter"])
    fun login(model: Model): RedirectView {
        val twitter: Twitter = TwitterFactory.getSingleton()
        val requestToken: RequestToken = twitter.getOAuthRequestToken("http://localhost:8080/connect/twitter")

        httpSession.setAttribute("requestToken", requestToken)
        return RedirectView(requestToken.authorizationURL)
    }

    @GetMapping(value = ["/twitter"], params = ["oauth_verifier"])
    fun callback(request: NativeWebRequest, @RequestParam("oauth_verifier") oauthVerifier: String): RedirectView {
        val requestToken = httpSession.getAttribute("requestToken") as RequestToken
        val userSession = twitterAuthenticationUseCase.authenticate(requestToken, oauthVerifier)
        httpSession.removeAttribute("requestToken")
        httpSession.setAttribute("userSession", userSession)
        return RedirectView("/")
    }
}