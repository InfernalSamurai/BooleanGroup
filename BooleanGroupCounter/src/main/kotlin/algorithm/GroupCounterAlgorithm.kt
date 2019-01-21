package algorithm

import algorithm.entity.FourWay

class GroupCounterAlgorithm(var matrix: Array<Array<Int>>) {
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

    private fun checkAndMark(rowIndex: Int, columnIndex: Int, booleanGroupTable: Array<Array<Int>>) {
        visited[rowIndex][columnIndex] = true

        FourWay().getRowShiftIndexes().forEachIndexed { i, _ ->
            val checkingRowIndex = rowIndex + FourWay().getRowShiftIndexes()[i]
            val checkingColumnIndex = columnIndex + FourWay().getColumnShiftIndexes()[i]

            if (isNeedCount(checkingRowIndex, checkingColumnIndex)) {
                checkAndMark(checkingRowIndex, checkingColumnIndex, booleanGroupTable)
            }
        }
    }


    private fun isNeedCount(checkingRowIndex: Int, checkingColumnIndex: Int): Boolean {
        return isSafe(checkingRowIndex, checkingColumnIndex)
                && matrix[checkingRowIndex][checkingColumnIndex] == 1 && !visited[checkingRowIndex][checkingColumnIndex]
    }

    private fun isSafe(checkingRowIndex: Int, checkingColumnIndex: Int): Boolean {
        return isIndexSafe(checkingRowIndex, matrix.size)
                && isIndexSafe(checkingColumnIndex, matrix[0].size)
    }

    private fun isIndexSafe(checkingIndex: Int, arraySize: Int) = checkingIndex in 0..(arraySize - 1)
}
