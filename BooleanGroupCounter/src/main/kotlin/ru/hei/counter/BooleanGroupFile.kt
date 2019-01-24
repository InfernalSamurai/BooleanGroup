package ru.hei.counter

import ru.hei.counter.entity.BooleanGroupData
import java.io.File

class BooleanGroupFile(private val data: BooleanGroupData) {

    fun writeToDirectory(directoryPath: String) {
        val directory = File(directoryPath).apply { if (!exists()) mkdirs() }
        File("${directory.absolutePath}/booleanGroup${data.id}.txt").apply {
            writeText(data.toString())
        }
    }
}
