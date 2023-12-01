package dev.shaundsmith.adventofcode2023

import dev.shaundsmith.adventofcode2023.core.FileLoader
import dev.shaundsmith.adventofcode2023.core.PuzzleSolution

fun main(args: Array<String>) {

    val day = args[0].toInt()
    val solutionClass = Class.forName("dev.shaundsmith.adventofcode2023.day$day.Day$day")

    val solutionInstance: PuzzleSolution = solutionClass.getDeclaredConstructor().newInstance() as PuzzleSolution

    val fileLoader = FileLoader()

    val part1Solution = solutionInstance.part1(fileLoader.loadFile("day$day/input.txt"))
    println("Day $day, Part 1 Solution: `$part1Solution`")

    val part2Solution = solutionInstance.part2(fileLoader.loadFile("day$day/input.txt"))
    println("Day $day, Part 2 Solution: `$part2Solution`")

}
