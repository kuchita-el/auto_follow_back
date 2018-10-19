package dkurata38.afb.batch

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.SpringApplication

@SpringBootApplication
class AfbBatchApplication

fun main(args: Array<String>) {
    SpringApplication.run(AfbBatchApplication::class.java, *args)
}