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
import org.springframework.security.config.annotation.web.builders.WebSecurity



@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/webjars/**", "/favicon.ico", "/css/**", "/js/**", "/img/**", "lib/*")
    }

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
                .authorizeRequests()
                .antMatchers("/", "/connect/*", "/auth/*").permitAll()
                .antMatchers("/api/session").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf()//.ignoringAntMatchers("/connect/**")
                .and()
                .apply(springSocialConfigurer())
    }
}