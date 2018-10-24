package dkurata38.afb.web.security

import org.springframework.social.connect.ConnectionFactoryLocator
import org.springframework.social.connect.ConnectionRepository
import org.springframework.social.connect.UsersConnectionRepository
import org.springframework.social.connect.support.OAuth1ConnectionFactory
import org.springframework.social.connect.web.ConnectController
import org.springframework.social.connect.web.ConnectSupport
import org.springframework.social.connect.web.HttpSessionSessionStrategy
import org.springframework.social.connect.web.SessionStrategy
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.servlet.view.RedirectView

@Controller
class ConnectController(private val connectionFactoryLocator: ConnectionFactoryLocator, connectionRepository: ConnectionRepository,
                        private val usersConnectionRepository: UsersConnectionRepository) : ConnectController(connectionFactoryLocator, connectionRepository) {
    override fun connectionStatusRedirect(providerId: String?, request: NativeWebRequest?): RedirectView {
        return RedirectView("/")
    }

    @RequestMapping(value = "/{providerId}", method = [RequestMethod.GET], params = ["oauth_token"])
    override fun oauth1Callback(@PathVariable providerId: String?, request: NativeWebRequest?): RedirectView {
        val sessionStrategy: SessionStrategy = HttpSessionSessionStrategy()
        try {
            val connectionFactory = connectionFactoryLocator.getConnectionFactory(providerId) as OAuth1ConnectionFactory<*>
            val connectSupport: ConnectSupport = ConnectSupport(sessionStrategy)
            val connection = connectSupport.completeConnection(connectionFactory, request)
            val userIds = usersConnectionRepository.findUserIdsWithConnection(connection)
            usersConnectionRepository.createConnectionRepository(userIds[0]).updateConnection(connection)
        } catch (e: Exception) {
            sessionStrategy.setAttribute(request, PROVIDER_ERROR_ATTRIBUTE, e)
        }
        return RedirectView("/")
    }
}