package dev.shaundsmith.adventofcode2023.day12

import dev.shaundsmith.adventofcode2023.core.PuzzleSolution

class Day12 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        val combinations = input.map { parseLine(it) }
            .sumOf { it.findValidCombinations() }

        return combinations.toString()
    }

    override fun part2(input: List<String>): String {

        val combinations = input.map { parseLineAndUnfold(it) }
            .sumOf { it.findValidCombinations() }

        return combinations.toString()
    }

    private fun parseLine(line: String): CachedScanningSpringRecord {

        val splitLine = line.split(" ")

        val numbers = splitLine[1].split(",")
            .map { it.toInt() }
            .toMutableList()

        return CachedScanningSpringRecord(splitLine[0], numbers)
    }

    private fun parseLineAndUnfold(line: String): CachedScanningSpringRecord {

        val splitLine = line.split(" ")

        val numbers = splitLine[1].split(",")
            .map { it.toInt() }

        val lineUnfolded = IntRange(0, 4).joinToString(separator = "?") { splitLine[0] }
        val numbersUnfolded = IntRange(0, 4).flatMap { numbers }

        return CachedScanningSpringRecord(lineUnfolded, numbersUnfolded)
    }

}