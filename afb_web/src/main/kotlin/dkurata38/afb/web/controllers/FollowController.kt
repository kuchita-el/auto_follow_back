package dkurata38.afb.web.controllers

import dkurata38.afb.domain.followkeyword.FollowKeyword
import dkurata38.afb.domain.followkeyword.FollowKeywordRepository
import dkurata38.afb.web.usecase.autofollow.FollowUseCase
import dkurata38.afb.web.usecase.followkeyword.FollowKeywordUseCase
import dkurata38.afb.web.usecase.security.UserSession
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView
import javax.servlet.http.HttpSession

@Controller
@RequestMapping(value = ["/follow"])
class FollowController(private val autoFollowUseCase: FollowUseCase, private val followKeywordUseCase: FollowKeywordUseCase,
                       private val httpSession: HttpSession) {
    @GetMapping("/")
    fun index(modelAndView: ModelAndView): ModelAndView {
        val userSession = httpSession.getAttribute("userSession") as? UserSession?
        if (userSession == null || !userSession.authenticated()) {
            return ModelAndView("/home/index")
        }
        modelAndView.viewName = "/follow/index"
        return modelAndView
    }


    @GetMapping("/form")
    fun config(model: Model): ModelAndView {
        val userSession = httpSession.getAttribute("userSession") as? UserSession?
        if (userSession == null || !userSession.authenticated()) {
            return ModelAndView("/home/index")
        }

        val followKeyword = followKeywordUseCase.getCurrentConfig(userSession.getUser()!!.getId()!!)

        val modelAndView = ModelAndView()
        modelAndView.viewName = "/follow/form"
        modelAndView.addObject("followKeyword", followKeyword)
        return modelAndView
    }


    @PostMapping(value = ["/"], params = ["keyword"])
    fun execute(@RequestParam("keyword") keyword: String, redirectAttributes: RedirectAttributes): RedirectView {
        val userSession = httpSession.getAttribute("userSession") as? UserSession?
        if (userSession == null || !userSession.authenticated()) {
            return RedirectView("/home/index")
        }

        if (keyword.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "フォロバする条件を入力してください。")
            return RedirectView("/follow/form")
        }

        followKeywordUseCase.configure(userSession.getUser()!!.getId()!!, keyword)
        autoFollowUseCase.autoFollow(userSession.getUser()!!.getToken(), keyword)
        return RedirectView("/")
    }
}