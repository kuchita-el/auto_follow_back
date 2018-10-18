package dkurata38.afb.web

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScans

@ComponentScans(ComponentScan("dkurata38.afb.domain"), ComponentScan("dkurata38.afb.infra"))
@SpringBootApplication
class AfbWebApplication

fun main(args: Array<String>) {
    SpringApplication.run(AfbWebApplication::class.java, * args)
}
