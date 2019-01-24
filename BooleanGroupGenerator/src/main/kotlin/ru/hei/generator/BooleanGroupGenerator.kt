package ru.hei.generator

import kotlin.random.Random

class BooleanGroupGenerator(private val booleanGroupData: BooleanGroupData) {
    fun generateMatrixAndGetData(): BooleanGroupData {
        val matrix = generateMatrix()

        return BooleanGroupData(
                id = booleanGroupData.id,
                width = booleanGroupData.width,
                height = booleanGroupData.height,
                matrix = matrix)
    }

    private fun generateMatrix(): List<List<Int>> {
        return List(booleanGroupData.height) {
            List(booleanGroupData.width) {
                Random.nextInt(2)
            }
        }
    }
}
