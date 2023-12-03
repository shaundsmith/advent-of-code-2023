package dev.shaundsmith.adventofcode2023.day3

import dev.shaundsmith.adventofcode2023.core.PuzzleSolution

class Day3 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        val schematic = Schematic(input)

        val sum = schematic.validPartNumbers()
            .sumOf { it.numberAsValue() }

        return sum.toString()
    }

    override fun part2(input: List<String>): String {

        val schematic = Schematic(input)

        val sum = schematic.gearRatios().sum()

        return sum.toString()
    }
}