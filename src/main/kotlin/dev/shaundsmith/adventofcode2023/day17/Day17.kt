package dev.shaundsmith.adventofcode2023.day17

import dev.shaundsmith.adventofcode2023.core.PuzzleSolution
import dev.shaundsmith.adventofcode2023.core.toGrid
import io.github.oshai.kotlinlogging.KotlinLogging

class Day17 : PuzzleSolution {

    private val logger = KotlinLogging.logger {}

    override fun part1(input: List<String>): String {

        val grid = input.toGrid({ c -> c.digitToInt() }, 0)
        val city = CityBlock(grid)
        val bestPath = city.route()

        if (logger.isDebugEnabled()) {
            val charGrid = input.toGrid({ c -> c }, '0')
            bestPath.getAllPositions().forEach { charGrid.set(it, '-') }
            logger.debug { "Found route: \n$charGrid" }
        }

        val heatLoss = bestPath.getAllPositions()
            .sumOf { grid.get(it) }

        return heatLoss.toString()
    }

    override fun part2(input: List<String>): String {

        return ""
    }

}