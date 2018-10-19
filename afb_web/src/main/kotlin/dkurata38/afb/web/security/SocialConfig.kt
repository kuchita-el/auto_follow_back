package dkurata38.afb.web.security

import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.encrypt.Encryptors
import org.springframework.social.UserIdSource
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer
import org.springframework.social.config.annotation.EnableSocial
import org.springframework.social.config.annotation.SocialConfigurerAdapter
import org.springframework.social.connect.ConnectionFactoryLocator
import org.springframework.social.connect.UsersConnectionRepository
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository
import javax.sql.DataSource

@Configuration
@EnableSocial
class SocialConfig(private val dataSource: DataSource): SocialConfigurerAdapter(){
    override fun getUsersConnectionRepository(connectionFactoryLocator: ConnectionFactoryLocator?): UsersConnectionRepository {
        val usersConnectionFactory = JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText())
		return usersConnectionFactory
    }

    override fun getUserIdSource(): UserIdSource {
        return UserIdSource {
            val authentication = SecurityContextHolder.getContext().authentication ?: throw IllegalStateException("Unable to get a ConnectionRepository: no user signed in")
            authentication.name
        }
    }

    override fun addConnectionFactories(connectionFactoryConfigurer: ConnectionFactoryConfigurer?, environment: Environment?) {
        super.addConnectionFactories(connectionFactoryConfigurer, environment)
    }
}