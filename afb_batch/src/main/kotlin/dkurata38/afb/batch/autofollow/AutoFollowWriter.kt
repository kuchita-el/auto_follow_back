package dkurata38.afb.batch.autofollow

import dkurata38.afb.domain.autofollow.AutoFollowService
import org.slf4j.LoggerFactory
import org.springframework.batch.item.ItemWriter

class AutoFollowWriter(private val autoFollowService: AutoFollowService) : ItemWriter<AutoFollowResouceDto> {
    private val log = LoggerFactory.getLogger(AutoFollowWriter::class.java)

    override fun write(items: List<AutoFollowResouceDto>) {
        log.info("Writer開始")
        items.forEach { i ->
            autoFollowService.autoFollow(i.getToken(), i.getFollowKeyword().getKeyword())
            val userId = i.getFollowKeyword().getUserId()
            log.info("ユーザ{$userId}を処理しました。")
        }
        log.info("Writer終了")
    }
}