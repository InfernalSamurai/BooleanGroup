package ru.hei.counter

import java.io.File

class BooleanGroupFile(private val data: BooleanGroupData) {

    fun writeToDirectory(directoryPath: String) {
        val directory = File(directoryPath).apply { if (!exists()) mkdirs() }
        File("${directory.absolutePath}/booleanGroup${data.id}.txt").apply {
            writeText(data.toString())
        }
    }
}
