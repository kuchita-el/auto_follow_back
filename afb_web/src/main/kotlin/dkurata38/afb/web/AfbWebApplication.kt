package dkurata38.afb.web

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScans
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

//import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession

@ComponentScans(ComponentScan("dkurata38.afb.domain"), ComponentScan("dkurata38.afb.infra"))
@EnableJpaRepositories(basePackages = ["dkurata38.afb.domain"])
@EntityScan(basePackages = ["dkurata38.afb.domain"])
//@EnableJdbcHttpSession
@SpringBootApplication
class AfbWebApplication

fun main(args: Array<String>) {
    SpringApplication.run(AfbWebApplication::class.java, * args)
}
