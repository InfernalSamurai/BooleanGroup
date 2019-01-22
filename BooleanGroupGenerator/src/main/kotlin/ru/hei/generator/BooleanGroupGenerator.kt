package ru.hei.generator

import kotlin.random.Random

class BooleanGroupGenerator {
    fun generate(height: Int, width: Int): MutableList<MutableList<Int>> {
        return MutableList(height) {
            MutableList(width) { Random.nextInt(2) }
        }
    }
}
