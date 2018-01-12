package se.nackademin.simplekotlinrestapi

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class SimpleKotlinRestApiApplication

fun main(args: Array<String>) {
    SpringApplication.run(SimpleKotlinRestApiApplication::class.java, *args)
}
