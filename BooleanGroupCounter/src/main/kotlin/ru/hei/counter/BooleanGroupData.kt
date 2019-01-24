package ru.hei.counter

data class BooleanGroupData(
        val id: Long,
        val width: Int,
        val height: Int,
        val matrix: List<List<Int>> = emptyList(),
        val groupCount: Int = 0
) {
    override fun toString(): String {
        val result = StringBuilder()

        for (row: List<Int> in matrix) {
            row.forEach { result.append(it) }
            result.append(System.lineSeparator())
        }

        result.append(System.lineSeparator())
                .append("Count of groups: $groupCount")

        return result.toString()
    }
}
