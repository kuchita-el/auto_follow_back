package dkurata38.afb.web

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScans
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@ComponentScans(ComponentScan("dkurata38.afb.domain"), ComponentScan("dkurata38.afb.infra"))
@EnableJpaRepositories(basePackages = ["dkurata38.afb.domain"])
@EntityScan(basePackages = ["dkurata38.afb.domain"])
@SpringBootApplication
class AfbWebApplication

fun main(args: Array<String>) {
    SpringApplication.run(AfbWebApplication::class.java, * args)
}
