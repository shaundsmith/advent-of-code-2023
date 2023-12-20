package dev.shaundsmith.adventofcode2023.day18

import dev.shaundsmith.adventofcode2023.core.Coordinate
import dev.shaundsmith.adventofcode2023.core.Direction
import dev.shaundsmith.adventofcode2023.core.Grid
import dev.shaundsmith.adventofcode2023.core.Path
import io.github.oshai.kotlinlogging.KotlinLogging

class FoundationPlanner(private val instructions: List<Pair<Direction, Int>>) {
    private val logger = KotlinLogging.logger {  }

    private var path = Path(Coordinate(0, 0));

    init {

        for (instruction in instructions) {
            val direction = instruction.first
            IntRange(0, instruction.second - 1)
                .forEach { _ -> path = path.move(direction) }
        }

    }

    fun toGrid(): Grid<Char> {
        val maxX = path.getAllPositions().maxOf { it.x }
        val minX = path.getAllPositions().minOf { it.x }
        val maxY = path.getAllPositions().maxOf { it.y }
        val minY = path.getAllPositions().minOf { it.y }

        val width = maxX - minX
        val height = maxY - minY

        val grid = Grid.ofSize(width + 1, height + 1, '.')
        path.getAllPositions()
            .forEach { grid.set(it.transpose(minX * -1, minY * -1), '#') }

        logger.debug { "Grid: \n$grid" }

        return grid
    }

}