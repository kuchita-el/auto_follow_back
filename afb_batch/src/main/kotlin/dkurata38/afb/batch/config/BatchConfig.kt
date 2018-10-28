package dkurata38.afb.batch.config

import dkurata38.afb.batch.autofollow.AutoFollowProcessor
import dkurata38.afb.batch.autofollow.AutoFollowResouceDto
import dkurata38.afb.batch.autofollow.AutoFollowWriter
import dkurata38.afb.domain.autofollow.AutoFollowService
import dkurata38.afb.domain.automateuser.AutomateUser
import dkurata38.afb.domain.automateuser.AutomateUserRepository
import dkurata38.afb.domain.followkeyword.FollowKeywordRepository
import dkurata38.afb.domain.user.UserRepository
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.item.data.RepositoryItemReader
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.Sort

@Configuration
@EnableBatchProcessing
class BatchConfig(
		private val jobBuilderFactory: JobBuilderFactory,
		private val stepBuilderFactory: StepBuilderFactory,
		private val autoFollowService: AutoFollowService,
		private val automateUserRepository: AutomateUserRepository,
		private val userRepository: UserRepository,
		private val followKeywordRepository: FollowKeywordRepository) {

	@Bean
	fun autoFollowReader(): RepositoryItemReader<AutomateUser> {
		val repositoryItemReader = RepositoryItemReader<AutomateUser>()
		repositoryItemReader.setRepository(automateUserRepository)
		repositoryItemReader.setMethodName("findAll")

		val sort = HashMap<String, Sort.Direction>()
		sort["id"] = Sort.Direction.ASC
		return repositoryItemReader
	}

	@Bean
	fun autoFollowProcessor(): AutoFollowProcessor{
		return AutoFollowProcessor(userRepository, followKeywordRepository)
	}

	@Bean
	fun autoFollowWriter(): AutoFollowWriter {
		return AutoFollowWriter(autoFollowService)
	}
	
	@Bean
	fun step(): Step {
		return stepBuilderFactory.get("step1")
				.chunk<AutomateUser, AutoFollowResouceDto>(10)
				.reader(autoFollowReader())
				.processor(autoFollowProcessor())
				.writer(autoFollowWriter())
				.build()
	}
	
	@Bean
	fun job(): Job {
		return jobBuilderFactory.get("job1")
				.start(step())
				.build()
	}
}