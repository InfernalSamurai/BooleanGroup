package ru.hei.counter

import ru.hei.counter.algorithm.GroupCounterAlgorithm
import ru.hei.counter.algorithm.entity.FourWay
import ru.hei.counter.entity.BooleanGroupData

class BooleanGroupCounter(private val booleanGroupData: BooleanGroupData) {
    fun countGroupsAndGetData() = BooleanGroupData(
            id = booleanGroupData.id,
            width = booleanGroupData.width,
            height = booleanGroupData.height,
            matrix = booleanGroupData.matrix,
            groupCount = count()
    )

    private fun count(): Int {
        return GroupCounterAlgorithm(booleanGroupData.matrix, FourWay()).countAndGet()
    }
}
