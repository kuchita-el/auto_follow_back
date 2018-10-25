package dkurata38.afb.web.controllers

import dkurata38.afb.domain.user.User
import dkurata38.afb.web.security.UserDetails
import org.springframework.security.core.Authentication
import org.springframework.social.connect.ConnectionRepository
import org.springframework.social.twitter.api.Twitter
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.View
import org.thymeleaf.spring4.view.ThymeleafView
import java.security.Principal

@Controller
@RequestMapping("/")
class HomeController {

    @GetMapping
    fun index(modelAndView: ModelAndView, principal: Principal?): ModelAndView {
        var user: User? = null
        if (principal is Authentication){
            val userDetails = principal.principal
            if (userDetails is UserDetails){
                user = userDetails.getUser()
            }
        }
        modelAndView.viewName = "/home/index"
        modelAndView.addObject("user", user)
        return modelAndView
    }
}