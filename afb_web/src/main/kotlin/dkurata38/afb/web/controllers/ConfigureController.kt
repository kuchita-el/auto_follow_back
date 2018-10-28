package dkurata38.afb.web.controllers

import dkurata38.afb.domain.followkeyword.FollowKeyword
import dkurata38.afb.domain.followkeyword.FollowKeywordRepository
import dkurata38.afb.web.usecase.automateuser.AutomateUserUseCase
import dkurata38.afb.web.usecase.followkeyword.FollowKeywordUseCase
import dkurata38.afb.web.usecase.security.UserSession
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.view.RedirectView
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/configure")
class ConfigureController(private val automateUserUseCase: AutomateUserUseCase,
						  private val followKeywordUseCase: FollowKeywordUseCase,
						  private val httpSession: HttpSession) {

	@GetMapping("/form")
	fun config(model: Model): ModelAndView{
		val userSession = httpSession.getAttribute("userSession") as? UserSession?
		if (userSession == null || !userSession.authenticated()) {
			return ModelAndView("/home/index")
		}

		var followKeyword = followKeywordUseCase.getCurrentConfig(userSession.getUser()!!.getId()!!)
		var isScheduled = automateUserUseCase.isEnabledAutomation(userSession.getUser()!!.getId()!!)
		val modelAndView = ModelAndView()
		modelAndView.viewName = "/configure/form"
		modelAndView.addObject("followKeyword", followKeyword)
		modelAndView.addObject("scheduled", isScheduled)
		return modelAndView
	}

	@PostMapping(value = ["/"], params = ["keyword", "scheduled"])
	fun configureAlias(@RequestParam("keyword") keyword:String,
					   @RequestParam("scheduled") scheduled: Boolean): RedirectView{
		return configure(keyword, scheduled)
	}

	@PutMapping(value = ["/"], params = ["keyword", "scheduled"])
	fun configure(@RequestParam("keyword") keyword:String,
				  @RequestParam("scheduled") scheduled: Boolean): RedirectView{
		val userSession = httpSession.getAttribute("userSession") as? UserSession?
		if (userSession == null || !userSession.authenticated()) {
			return RedirectView("/home/index")
		}

		automateUserUseCase.configureAutomation(userSession.getUser()!!.getId()!!, scheduled)
		followKeywordUseCase.configure(userSession.getUser()!!.getId()!!, keyword)
		return RedirectView("/follow/index")
	}
}