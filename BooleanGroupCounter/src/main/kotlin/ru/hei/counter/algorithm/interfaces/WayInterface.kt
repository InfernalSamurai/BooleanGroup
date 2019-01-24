package ru.hei.counter.algorithm.interfaces

interface WayInterface {
    fun getWays(): Int
    fun getRowShiftIndexes(): MutableList<Int>
    fun getColumnShiftIndexes(): MutableList<Int>
}
