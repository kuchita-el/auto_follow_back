package dkurata38.afb.web.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.social.security.SocialUser
import org.springframework.social.security.SocialUserDetailsService
import org.springframework.social.security.SpringSocialConfigurer

@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {

    @Bean
    fun springSocialConfigurer(): SpringSocialConfigurer{
        val springSocialConfigurer = SpringSocialConfigurer()
        springSocialConfigurer.postLoginUrl("/")
        springSocialConfigurer.alwaysUsePostLoginUrl(true)
        springSocialConfigurer.defaultFailureUrl("/")
        return springSocialConfigurer
    }

    override fun configure(http: HttpSecurity) {
        http
                .csrf()//.ignoringAntMatchers("/connect/**")
                .and()
                .apply(springSocialConfigurer())
    }

    @Bean
    fun socialUserDetailsService(): SocialUserDetailsService {
        return SocialUserDetailsService {userId ->
            val userDetails:UserDetails = userDetailsService().loadUserByUsername(userId)
            SocialUser(userDetails.username, userDetails.password, userDetails.authorities)
        }
    }
}