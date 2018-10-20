package dkurata38.afb.batch.autofollow

import org.springframework.batch.item.ItemWriter
import dkurata38.afb.domain.twitteruser.TwitterUser
import dkurata38.afb.domain.autofollow.AutoFollowService

class AutoFollowWriter(val autoFollowService: AutoFollowService): ItemWriter<TwitterUser> {
	override fun write(items: List<TwitterUser>) {
		autoFollowService.autoFollow("", "", "エンジニア")
	}
}