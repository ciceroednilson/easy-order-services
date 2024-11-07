package br.com.ciceroednilson

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@SpringBootApplication
class KotlinSpringMigrationApiApplication

fun main(args: Array<String>) {
	runApplication<KotlinSpringMigrationApiApplication>(*args)
}
