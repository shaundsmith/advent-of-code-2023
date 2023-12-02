package dev.shaundsmith.adventofcode2023.day1

import dev.shaundsmith.adventofcode2023.core.PuzzleSolution

class Day1 : PuzzleSolution  {

    private val numberMap = IntRange(0, 9).associate { it.toString() to it.toString() }

    val wordMap = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9",
    )

    override fun part1(input: List<String>): String {

        val sum = input.map { CalibrationLine(it, numberMap) }
            .sumOf { it.calculationCalibrationValue() }

        return sum.toString()
    }

    override fun part2(input: List<String>): String {

        val sum = input.map { CalibrationLine(it, numberMap + wordMap) }
            .sumOf { it.calculationCalibrationValue() }

        return sum.toString()
    }

}