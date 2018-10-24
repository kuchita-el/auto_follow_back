package dkurata38.afb.web.security

import org.springframework.context.annotation.Configuration
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.encrypt.Encryptors
import org.springframework.social.UserIdSource
import org.springframework.social.config.annotation.EnableSocial
import org.springframework.social.config.annotation.SocialConfigurerAdapter
import org.springframework.social.connect.ConnectionFactoryLocator
import org.springframework.social.connect.UsersConnectionRepository
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository
import javax.sql.DataSource

@Configuration
@EnableSocial
class SocialConfig(private val dataSource: DataSource, private val socialSignUp: SocialSignUp): SocialConfigurerAdapter(){

    override fun getUsersConnectionRepository(connectionFactoryLocator: ConnectionFactoryLocator?): UsersConnectionRepository {
        val jdbcUsersConnectionRepository = JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText())
        jdbcUsersConnectionRepository.setConnectionSignUp(socialSignUp)
		return jdbcUsersConnectionRepository
    }

    override fun getUserIdSource(): UserIdSource {
        return UserIdSource {
            val authentication = SecurityContextHolder.getContext().authentication ?: throw IllegalStateException("Unable to get a ConnectionRepository: no user signed in")
            authentication.name
        }
    }
}