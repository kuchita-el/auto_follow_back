package dkurata38.afb.batch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScans
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@ComponentScan(basePackages = ["dkurata38.afb.domain", "dkurata38.afb.infra"])
@EnableJpaRepositories(basePackages = ["dkurata38.afb.domain"])
@EntityScan(basePackages = ["dkurata38.afb.domain"])
class AfbBatchApplication

fun main(args: Array<String>) {
    SpringApplication.run(AfbBatchApplication::class.java, *args)
}