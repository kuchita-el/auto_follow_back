package dkurata38.afb.domain.followkeyword

import java.io.Serializable
import javax.persistence.Embeddable

@Embeddable
class FollowKeywordKey(): Serializable {
    var userId: String? = null
    var providerId: String? = null


}