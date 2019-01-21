import kotlin.random.Random

fun main(args: Array<String>) {
    val columns = 13
    val rows = 3
    val booleanGroupMatrix = generate(rows, columns)

    for (row in booleanGroupMatrix) {
        row.forEach { print(it) }
        println()
    }
}

fun generate(height: Int, width: Int): MutableList<MutableList<Int>> {
    return MutableList(height) {
        MutableList(width) { Random.nextInt(2) }
    }
}
