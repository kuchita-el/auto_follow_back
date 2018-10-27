package dkurata38.afb.web.controllers

import org.springframework.stereotype.Controller

import dkurata38.afb.domain.autofollow.AutoFollowService
import dkurata38.afb.domain.followkeyword.FollowKeyword
import dkurata38.afb.domain.followkeyword.FollowKeywordRepository
import dkurata38.afb.web.usecase.security.UserSession
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
//import org.springframework.social.connect.ConnectionRepository
//import org.springframework.social.twitter.api.Twitter
import org.springframework.web.servlet.view.RedirectView
import java.security.Principal
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/follow")
class FollowController(private val autoFollowService: AutoFollowService, private val followKeywordRepository: FollowKeywordRepository, private val httpSession: HttpSession) {

	@GetMapping("config")
	fun config(model: Model): ModelAndView{
		val userSession = httpSession.getAttribute("userSession") as? UserSession?
		if (userSession == null || !userSession.authenticated()) {
			return ModelAndView("/home/index")
		}

		var followKeyword = followKeywordRepository.findByUserId(userSession.getUser()!!.getId()!!)
		if (followKeyword == null){
			followKeyword = FollowKeyword.defaultKeyword(userSession.getUser()!!.getId()!!)
		}

		val modelAndView = ModelAndView()
		modelAndView.viewName = "/follow/config"
		modelAndView.addObject("followKeyword", followKeyword)
		return modelAndView
	}

	@PatchMapping(value = ["config"])
	fun configure(model: Model){

	}


	@PostMapping(value = [""], params = ["keyword"])
	fun execute(@RequestParam("keyword") keyword: String): RedirectView{
		val userSession = httpSession.getAttribute("userSession") as? UserSession?
		if (userSession == null || !userSession.authenticated()) {
			return RedirectView("/home/index")
		}

		autoFollowService.autoFollow(userSession.getUser()!!.getToken(), keyword);
		return RedirectView("/")
	}
}