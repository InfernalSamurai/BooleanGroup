package ru.hei.generator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BooleanGroupGeneratorService

fun main(args: Array<String>) {
    runApplication<BooleanGroupGeneratorService>(*args)
}

