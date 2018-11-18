package dkurata38.afb.web.usecase.security

//import org.springframework.social.security.SocialUser
//import org.springframework.social.security.SocialUserDetailsService
//import org.springframework.social.security.SpringSocialConfigurer
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/webjars/**", "/favicon.ico", "/css/**", "/js/**", "/img/**", "/lib/**")
    }

//    @Bean
//    fun springSocialConfigurer(): SpringSocialConfigurer{
//        val springSocialConfigurer = SpringSocialConfigurer()
//        springSocialConfigurer.postLoginUrl("/")
//        springSocialConfigurer.alwaysUsePostLoginUrl(true)
//        springSocialConfigurer.defaultFailureUrl("/")
//        return springSocialConfigurer
//    }

    override fun configure(http: HttpSecurity) {
        http
//                .formLogin()
//                .loginPage("/")
//                .loginProcessingUrl("/connect/twitter")
//                .defaultSuccessUrl("/", true)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/", "/connect/*").permitAll()
//                .antMatchers("/api/session").permitAll()
//                .anyRequest().authenticated()
//                .and()
                .csrf()
//                .and()
//                .apply(springSocialConfigurer())
    }
//
//    @Bean
//    override fun userDetailsService(): UserDetailsService {
//        return UserDetailsService()
//    }
}