package dkurata38.afb.web.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping

import dkurata38.afb.domain.service.AutoFollowService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.ui.Model

@Controller
@RequestMapping("follow")
class FollowController(val autoFollowService: AutoFollowService) {
	
	@GetMapping("config")
	fun new(model: Model){
		
	}
	
	@PostMapping("")
	fun aaa(model: Model){
		autoFollowService.autoFollow("", "", "エンジニア");
	}
}