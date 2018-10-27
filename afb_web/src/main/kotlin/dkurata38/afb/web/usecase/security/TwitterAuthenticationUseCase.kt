package dkurata38.afb.web.usecase.security

import dkurata38.afb.domain.user.Token
import dkurata38.afb.domain.user.SnsLoginId
import dkurata38.afb.domain.user.User
import dkurata38.afb.domain.user.UserRepository
import org.springframework.stereotype.Service
import twitter4j.Twitter
import twitter4j.TwitterFactory
import twitter4j.auth.RequestToken
import javax.security.sasl.AuthenticationException
import javax.transaction.Transactional

@Service
class TwitterAuthenticationUseCase(private val userRepository: UserRepository) {
    @Transactional
    fun authenticate(requestToken: RequestToken, oauthVerifier: String): UserSession {
        val twitter: Twitter = TwitterFactory.getSingleton()
        try {
            val oAuthAccessToken = twitter.getOAuthAccessToken(requestToken, oauthVerifier)
            twitter.verifyCredentials()
            val twitterUser = twitter.showUser(oAuthAccessToken.userId)

            val userId = SnsLoginId(oAuthAccessToken.screenName)
            var user = userRepository.findBySnsLoginId(userId)
            val token = Token(oAuthAccessToken.token, oAuthAccessToken.tokenSecret)
            if(user == null){
                user = User(userId, twitterUser.name, token)
            } else {
                user.updateToken(token)
            }
            val savedUser = userRepository.saveAndFlush(user)
            return UserSession(savedUser)

        } catch (e: Exception) {
            throw AuthenticationException("")
        }
    }
}