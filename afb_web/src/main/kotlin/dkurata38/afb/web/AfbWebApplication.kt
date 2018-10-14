package dkurata38.afb.web

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class AfbWebApplication

fun main(args: Array<String>) {
    SpringApplication.run(AfbWebApplication::class.java, * args)
}
