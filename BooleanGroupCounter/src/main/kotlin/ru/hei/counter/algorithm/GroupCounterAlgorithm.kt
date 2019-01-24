package ru.hei.counter.algorithm

import ru.hei.counter.interfaces.WayInterface

class GroupCounterAlgorithm(var matrix: List<List<Int>>, private val ways: WayInterface) {
    var visited: MutableList<BooleanArray> = MutableList(matrix.size) { BooleanArray(matrix[0].size) }

    fun countAndGet(): Int {
        var count = 0
        matrix.forEachIndexed { rowIndex, column ->
            column.forEachIndexed { columnIndex, _ ->
                count += countAndGet(rowIndex, columnIndex)
            }
        }
        return count
    }

    private fun countAndGet(rowIndex: Int, columnIndex: Int): Int {
        var count = 0

        if (isNeedCount(rowIndex, columnIndex)) {
            checkAndMark(rowIndex, columnIndex, matrix)
            count++
        }

        return count
    }

    private fun checkAndMark(rowIndex: Int, columnIndex: Int, booleanGroupTable: List<List<Int>>) {
        visited[rowIndex][columnIndex] = true

        for (i: Int in 0 until ways.getWays()) {
            val checkingRowIndex = rowIndex + ways.getRowShiftIndexes()[i]
            val checkingColumnIndex = columnIndex + ways.getColumnShiftIndexes()[i]

            if (isNeedCount(checkingRowIndex, checkingColumnIndex)) {
                checkAndMark(checkingRowIndex, checkingColumnIndex, booleanGroupTable)
            }
        }
    }

    private fun isNeedCount(checkingRowIndex: Int, checkingColumnIndex: Int): Boolean {
        return isSafe(checkingRowIndex, checkingColumnIndex)
                && isCountingValue(checkingRowIndex, checkingColumnIndex)
                && isNotVisited(checkingRowIndex, checkingColumnIndex)
    }

    private fun isNotVisited(checkingRowIndex: Int, checkingColumnIndex: Int) =
            !visited[checkingRowIndex][checkingColumnIndex]

    private fun isCountingValue(checkingRowIndex: Int, checkingColumnIndex: Int) =
            matrix[checkingRowIndex][checkingColumnIndex] == 1

    private fun isSafe(checkingRowIndex: Int, checkingColumnIndex: Int): Boolean {
        return isIndexSafe(checkingRowIndex, matrix.size)
                && isIndexSafe(checkingColumnIndex, matrix[0].size)
    }

    private fun isIndexSafe(checkingIndex: Int, arraySize: Int) = checkingIndex in 0..(arraySize - 1)
}
