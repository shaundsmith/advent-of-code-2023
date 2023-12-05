package dev.shaundsmith.adventofcode2023

import dev.shaundsmith.adventofcode2023.core.FileLoader
import java.io.File
import java.nio.charset.StandardCharsets
import java.nio.file.Paths
import kotlin.io.path.listDirectoryEntries

val fileLoader = FileLoader()

fun main() {

    val nextDay = getLatestDay() + 1

    writeTemplate(
        fileLoader.loadFile("templates/Day.template"),
        "src/main/kotlin/dev/shaundsmith/adventofcode2023/day$nextDay",
        "Day$nextDay.kt",
        nextDay
    )

    writeTemplate(
        fileLoader.loadFile("templates/Test.template"),
        "src/test/kotlin/dev/shaundsmith/adventofcode2023",
        "Day${nextDay}Test.kt",
        nextDay
    )

    writeTemplate(
        arrayListOf(),
        "src/main/resources/day$nextDay",
        "input.txt",
        nextDay
    )

    writeTemplate(
        arrayListOf(),
        "src/test/resources/day$nextDay",
        "test-input-1.txt",
        nextDay
    )

    writeTemplate(
        arrayListOf(),
        "src/test/resources/day$nextDay",
        "test-input-2.txt",
        nextDay
    )
}

fun getLatestDay(): Int {

    return Paths.get("src/main/kotlin/dev/shaundsmith/adventofcode2023")
        .listDirectoryEntries("day*")
        .count()
}

fun writeTemplate(template: List<String>, destination: String, destinationName: String, day: Int) {

    val directory = File(destination)

    directory.mkdirs()
    val file = File("$destination/$destinationName")
    check(!file.exists()) {
        "File $file already exists"
    }
    file.createNewFile()

    val fileContents = template.joinToString(System.lineSeparator()) { it.replace("\$day\$", day.toString()) }

    file.writeText(fileContents, StandardCharsets.UTF_8)
}