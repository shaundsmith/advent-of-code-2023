package dev.shaundsmith.adventofcode2023.day15

import dev.shaundsmith.adventofcode2023.core.PuzzleSolution

class Day15 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        val value = input[0].split(",")
            .map { InitiationCode(it) }
            .sumOf { it.hash() }

        return value.toString()
    }

    override fun part2(input: List<String>): String {

        val reindeerHashmap = ReindeerHashmap()
        input[0].split(",")
            .forEach { reindeerHashmap.action(it) }

        return reindeerHashmap.calculateScores().toString()
    }

}