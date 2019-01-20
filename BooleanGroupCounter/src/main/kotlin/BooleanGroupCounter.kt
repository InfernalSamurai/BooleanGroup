import kotlin.random.Random

class BooleanGroupCounter

fun main(args: Array<String>) {
    val columns = 13
    val rows = 3
    val booleanGroupTable: Array<Array<Int>> = Array(rows) {
        Array(columns) { Random.nextInt(2) }
    }

    var count = 0
    val visited: Array<BooleanArray> = Array(booleanGroupTable.size) { BooleanArray(booleanGroupTable[0].size) }

    for (row: Array<Int> in booleanGroupTable) {
        row.forEach { print(it) }
        println()
    }

    booleanGroupTable.forEachIndexed { rowIndex, column ->
        column.forEachIndexed { columnIndex, value ->
            if (value == 1 && !visited[rowIndex][columnIndex]) {
                dsf(visited, rowIndex, columnIndex, booleanGroupTable)
                count++
            }
        }
    }

    println(count)
}

private fun dsf(visited: Array<BooleanArray>, rowIndex: Int, columnIndex: Int, booleanGroupTable: Array<Array<Int>>) {
    val rowNbr = intArrayOf(-1, 0, 0, 1)
    val colNbr = intArrayOf(0, -1, 1, 0)

    visited[rowIndex][columnIndex] = true

    rowNbr.forEachIndexed { i, _ ->
        val checkingRowIndex = rowIndex + rowNbr[i]
        val checkingColumnIndex = columnIndex + colNbr[i]
        if (isSafe(checkingRowIndex, booleanGroupTable, checkingColumnIndex, visited)) {
            dsf(visited, checkingRowIndex, checkingColumnIndex, booleanGroupTable)
        }
    }
}

private fun isSafe(checkingRowIndex: Int, booleanGroupTable: Array<Array<Int>>, checkingColumnIndex: Int, visited: Array<BooleanArray>): Boolean {
    return checkingRowIndex >= 0 && checkingRowIndex < booleanGroupTable.size
            && checkingColumnIndex >= 0 && checkingColumnIndex < booleanGroupTable[0].size
            && booleanGroupTable[checkingRowIndex][checkingColumnIndex] == 1 && !visited[checkingRowIndex][checkingColumnIndex]
}