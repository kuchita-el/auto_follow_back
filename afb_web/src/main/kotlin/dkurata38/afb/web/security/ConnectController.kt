package dkurata38.afb.web.security

import org.springframework.social.connect.ConnectionFactoryLocator
import org.springframework.social.connect.ConnectionRepository
import org.springframework.social.connect.web.ConnectController
import org.springframework.stereotype.Controller
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.servlet.view.RedirectView

@Controller
class ConnectController(connectionFactoryLocator: ConnectionFactoryLocator?, connectionRepository: ConnectionRepository?) : ConnectController(connectionFactoryLocator, connectionRepository) {
    override fun connectionStatusRedirect(providerId: String?, request: NativeWebRequest?): RedirectView {
        return RedirectView("/")
    }
}