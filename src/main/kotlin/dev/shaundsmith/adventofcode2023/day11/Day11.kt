package dev.shaundsmith.adventofcode2023.day11

import dev.shaundsmith.adventofcode2023.core.Grid
import dev.shaundsmith.adventofcode2023.core.PuzzleSolution
import dev.shaundsmith.adventofcode2023.core.toGrid

class Day11(private val expansionSize: Int = 1000000) : PuzzleSolution {

    override fun part1(input: List<String>): String {

        val grid: Grid<Char> = input.toGrid({ c -> c }, '.')
        val galaxyMap = GalaxyMap(grid, 2)

        return galaxyMap.findShortestDistancesBetweenGalaxies().sum().toString()
    }

    override fun part2(input: List<String>): String {

        val grid: Grid<Char> = input.toGrid({ c -> c }, '.')
        val galaxyMap = GalaxyMap(grid, expansionSize)

        return galaxyMap.findShortestDistancesBetweenGalaxies().sum().toString()
    }

}