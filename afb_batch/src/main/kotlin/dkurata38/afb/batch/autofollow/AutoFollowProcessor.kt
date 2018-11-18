package dkurata38.afb.batch.autofollow

import dkurata38.afb.domain.automateuser.AutomateUser
import dkurata38.afb.domain.followkeyword.FollowKeywordRepository
import dkurata38.afb.domain.user.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.batch.item.ItemProcessor

class AutoFollowProcessor(private val userRepository: UserRepository,
                          private val followKeywordRepository: FollowKeywordRepository) : ItemProcessor<AutomateUser, AutoFollowResouceDto> {
    private val log = LoggerFactory.getLogger(AutoFollowProcessor::class.java)

    /**
     * Process the provided item, returning a potentially modified or new item for continued
     * processing.  If the returned result is null, it is assumed that processing of the item
     * should not continue.
     *
     * @param item to be processed
     * @return potentially modified or new item for continued processing, null if processing of the
     * provided item should not continue.
     *
     * @throws Exception thrown if exception occurs during processing.
     */
    override fun process(item: AutomateUser?): AutoFollowResouceDto? {
        if (item == null) {
            log.info("アイテムがありません")
            return null
        }
        val user = userRepository.findById(item.getUserId()!!)
        if (!user.isPresent) {
            log.warn("存在しないユーザです。")
            return null
        }

        val followKeyword = followKeywordRepository.findByUserId(item.getUserId()!!)
        if (followKeyword == null) {
            log.warn("キーワードが未設定です。")
            return null
        }

        return AutoFollowResouceDto(followKeyword, user.get().getToken())
    }
}