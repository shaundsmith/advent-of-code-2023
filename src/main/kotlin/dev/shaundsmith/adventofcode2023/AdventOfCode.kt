package dev.shaundsmith.adventofcode2023

import dev.shaundsmith.adventofcode2023.core.FileLoader
import dev.shaundsmith.adventofcode2023.core.PuzzleSolution
import kotlin.system.exitProcess

fun main() {

    println("Please enter day to run:")
    val day = readln()
    if (!day.matches(Regex("\\d+"))) {
        quitWithInvalidDay(day)
        return
    }

    val solutionClass: Class<*>
    try {
        solutionClass = Class.forName("dev.shaundsmith.adventofcode2023.day$day.Day$day")
    } catch (e: ClassNotFoundException) {
        quitWithInvalidDay(day)
        return
    }

    val solutionInstance: PuzzleSolution = solutionClass.getDeclaredConstructor().newInstance() as PuzzleSolution

    val fileLoader = FileLoader()

    val part1Solution = solutionInstance.part1(fileLoader.loadFile("day$day/input.txt"))
    println("Day $day, Part 1 Solution: `$part1Solution`")

    val part2Solution = solutionInstance.part2(fileLoader.loadFile("day$day/input.txt"))
    println("Day $day, Part 2 Solution: `$part2Solution`")

}

fun quitWithInvalidDay(day: String) {
    println("Invalid day $day")
    exitProcess(1)
}
