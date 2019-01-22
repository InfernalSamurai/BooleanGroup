package ru.hei.counter.interfaces

interface WayInterface {
    fun getWays(): Int
    fun getRowShiftIndexes(): MutableList<Int>
    fun getColumnShiftIndexes(): MutableList<Int>
}
