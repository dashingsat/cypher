package com.satya.cypher

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CypherApplication

fun main(args: Array<String>) {
    runApplication<CypherApplication>(*args)
}
