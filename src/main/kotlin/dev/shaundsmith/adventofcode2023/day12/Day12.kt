package dev.shaundsmith.adventofcode2023.day12

import dev.shaundsmith.adventofcode2023.core.PuzzleSolution

class Day12 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        val combinations = input.map { parseLine(it) }
            .sumOf { it.findValidCombinations().size }

        return combinations.toString()
    }

    override fun part2(input: List<String>): String {

        val combinations = input.map { parseLineAndUnfold(it) }
            .sumOf { it.findValidCombinations().size }

        return combinations.toString()
    }

    private fun parseLine(line: String): SpringRecord {

        val splitLine = line.split(" ")

        val numbers = splitLine[1].split(",")
            .map { it.toInt() }

        return SpringRecord(splitLine[0], numbers)
    }

    private fun parseLineAndUnfold(line: String): SpringRecord {

        val splitLine = line.split(" ")

        val numbers = splitLine[1].split(",")
            .map { it.toInt() }

        val lineUnfolded = IntRange(0, 5).joinToString(separator = "?") { splitLine[0] }
        val numbersUnfolded = IntRange(0, 5).flatMap { numbers }

        return SpringRecord(lineUnfolded, numbersUnfolded)
    }

}