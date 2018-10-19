package dkurata38.afb.web.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping

import dkurata38.afb.domain.service.AutoFollowService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.ui.Model
import org.springframework.social.connect.ConnectionRepository
import org.springframework.social.twitter.api.Twitter

@Controller
@RequestMapping("/follow")
class FollowController(private val autoFollowService: AutoFollowService, private val connectionRepository: ConnectionRepository) {
	
	@GetMapping("config")
	fun new(model: Model){
		
	}
	
	@PostMapping("")
	fun execute(): String{
		val connection = connectionRepository.findPrimaryConnection(Twitter::class.java)
		if (connection == null){
			return "redirect:/home/index"
		}
		val connectionData = connection.createData()
		autoFollowService.autoFollow(connectionData.accessToken, connectionData.secret, "エンジニア");
		return "redirect:/"
	}
}