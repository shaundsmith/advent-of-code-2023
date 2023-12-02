package dev.shaundsmith.adventofcode2023.day2

import dev.shaundsmith.adventofcode2023.core.PuzzleSolution

class Day2 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        val total = input.map { Game(it) }
            .filter { it.isPossible(red = 12, blue = 14, green = 13)}
            .sumOf { it.game }

        return total.toString()
    }

    override fun part2(input: List<String>): String {

        val total = input.map { Game(it) }
            .map { it.findMaxCount() }
            .sumOf { it["green"]!! * it["red"]!! * it["blue"]!! }

        return total.toString()
    }
}