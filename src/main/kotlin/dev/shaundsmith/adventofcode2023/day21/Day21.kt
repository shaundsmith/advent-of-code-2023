package dev.shaundsmith.adventofcode2023.day21

import dev.shaundsmith.adventofcode2023.core.PuzzleSolution
import dev.shaundsmith.adventofcode2023.core.toGrid

class Day21(private val movements: Int = 64) : PuzzleSolution {

    override fun part1(input: List<String>): String {

        val grid = input.toGrid({ c -> c }, '.')
        val gardenNavigator = GardenNavigator(grid)
        gardenNavigator.navigate(movements)

        return gardenNavigator.activePositions[0].size.toString()
    }

    override fun part2(input: List<String>): String {

        return ""
    }

}