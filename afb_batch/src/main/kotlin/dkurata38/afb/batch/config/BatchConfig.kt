package dkurata38.afb.batch.config

import org.springframework.context.annotation.Configuration
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.context.annotation.Bean
import dkurata38.afb.batch.autofollow.AutoFollowWriter
import org.springframework.batch.core.Step
import dkurata38.afb.domain.twitteruser.TwitterUser
import org.springframework.batch.core.Job
import dkurata38.afb.domain.autofollow.AutoFollowService

@Configuration
@EnableBatchProcessing
class BatchConfig(val jobBuilderFactory: JobBuilderFactory, val stepBuilderFactory: StepBuilderFactory, val autoFollowService: AutoFollowService) {
	@Bean
	fun autoFollowWriter(): AutoFollowWriter {
		return AutoFollowWriter(autoFollowService)
	}
	
	@Bean
	fun step(): Step {
		return stepBuilderFactory.get("step")
				.chunk<TwitterUser, TwitterUser>(10)
				.writer(autoFollowWriter())
				.build()
	}
	
	@Bean
	fun job(): Job {
		return jobBuilderFactory.get("job")
				.start(step())
				.build()
	}
}