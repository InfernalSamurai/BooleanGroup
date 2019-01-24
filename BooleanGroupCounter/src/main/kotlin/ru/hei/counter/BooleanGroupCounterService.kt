package ru.hei.counter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BooleanGroupCounterService

fun main(args: Array<String>) {
    runApplication<BooleanGroupCounterService>(*args)
}
