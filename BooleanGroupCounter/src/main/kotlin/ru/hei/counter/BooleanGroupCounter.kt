package ru.hei.counter

import ru.hei.counter.algorithm.GroupCounterAlgorithm
import ru.hei.counter.entity.FourWay
import kotlin.random.Random

class BooleanGroupCounter

fun main(args: Array<String>) {
    val columns = 13
    val rows = 3
    val booleanGroupMatrix: Array<Array<Int>> = Array(rows) {
        Array(columns) { Random.nextInt(2) }
    }


    for (row: Array<Int> in booleanGroupMatrix) {
        row.forEach { print(it) }
        println()
    }

    val count = count(booleanGroupMatrix)

    println(count)
}

private fun count(booleanGroupMatrix: Array<Array<Int>>): Int {
    return GroupCounterAlgorithm(booleanGroupMatrix, FourWay()).countAndGet()
}
