package algorithm.entity

import algorithm.interfaces.WayInterface

class FourWay : WayInterface {
    override fun getWays() = 4

    override fun getRowShiftIndexes(): MutableList<Int> = arrayListOf(-1, 0, 0, 1)

    override fun getColumnShiftIndexes(): MutableList<Int> = arrayListOf(0, -1, 1, 0)

}
