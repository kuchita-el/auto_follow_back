package dkurata38.afb.web.controllers

//import org.springframework.social.connect.ConnectionRepository
//import org.springframework.social.twitter.api.Twitter
import dkurata38.afb.web.usecase.security.UserSession
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/")
class HomeController(private val httpSession: HttpSession) {

    @GetMapping
    fun index(modelAndView: ModelAndView): ModelAndView {
        val userSession = httpSession.getAttribute("userSession") as? UserSession?
        modelAndView.viewName = "/home/index"
        modelAndView.addObject("userSession", userSession)
        return modelAndView
    }
}