package dkurata38.afb.batch.autofollow

import org.springframework.batch.item.ItemWriter
import dkurata38.afb.domain.entity.TwitterUser
import dkurata38.afb.domain.service.AutoFollowService

class AutoFollowWriter(val autoFollowService: AutoFollowService): ItemWriter<TwitterUser> {
	override fun write(items: List<TwitterUser>) {
	}
}