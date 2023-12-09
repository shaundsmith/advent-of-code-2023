package dev.shaundsmith.adventofcode2023.day9

import dev.shaundsmith.adventofcode2023.core.PuzzleSolution

class Day9 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        val interpolationResults = input.map { parseToSequence(it) }
            .sumOf { it.interpolateNext() }

        return interpolationResults.toString()
    }

    override fun part2(input: List<String>): String {

        val interpolationResults = input.map { parseToSequence(it) }
            .sumOf { it.interpolatePrevious() }

        return interpolationResults.toString()
    }

    private fun parseToSequence(line: String): Sequence {

        return Sequence(line.split(" ").map { it.toLong() })
    }

}