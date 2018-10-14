package dkurata38.afb.web.controllers

import org.springframework.social.connect.ConnectionRepository
import org.springframework.social.twitter.api.Twitter
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class HomeController(private val twitter: Twitter, private val connectionRepository: ConnectionRepository) {

    @GetMapping
    fun index(model: Model): String {
        if (connectionRepository.findPrimaryConnection(Twitter::class.java) == null){
            return "redirect:/connect/twitter"
        }
        model.addAttribute("twitterProfile" ,twitter.userOperations().userProfile)
        val friendOperations = twitter.friendOperations()
        model.addAttribute("friends", friendOperations)
        return "home/index"
    }
}