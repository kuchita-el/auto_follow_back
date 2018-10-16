package dkurata38.afb.domain.entity

open class TwitterUser (
		private val id: Long,
		private val screenName: String,
		private val description: String,
		private val sentRequest: Boolean){
	
	fun getId(): Long {
		return id
	}
	
	fun getScreanName(): String {
		return screenName
	}

	fun matchDescriptionTo(followKeyWord: String): Boolean {
		return description.contains(followKeyWord)
	}

	fun sentRequest(): Boolean {
		return sentRequest
	}
}