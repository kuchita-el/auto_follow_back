package dkurata38.afb.web.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping

import dkurata38.afb.domain.autofollow.AutoFollowService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.ui.Model
import org.springframework.social.connect.ConnectionRepository
import org.springframework.social.twitter.api.Twitter
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.servlet.view.RedirectView

@Controller
@RequestMapping("/follow")
class FollowController(private val autoFollowService: AutoFollowService, private val connectionRepository: ConnectionRepository) {
	
	@GetMapping("config")
	fun config(model: Model){
		
	}

	@PatchMapping(value ="config")
	fun configure(model: Model){

	}


	@PostMapping(value = "")
	fun execute(): RedirectView{
		val connection = connectionRepository.findPrimaryConnection(Twitter::class.java) ?: return RedirectView("/")
		val connectionData = connection.createData()
		autoFollowService.autoFollow(connectionData.accessToken, connectionData.secret, "エンジニア");
		return RedirectView("/")
	}
}