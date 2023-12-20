package dev.shaundsmith.adventofcode2023.day18

import dev.shaundsmith.adventofcode2023.core.Coordinate
import dev.shaundsmith.adventofcode2023.core.Grid
import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.LinkedList

class FloodFiller(private val grid: Grid<Char>) {
    private val logger = KotlinLogging.logger {  }

    fun fill(origin: Coordinate) {

        val queue = LinkedList<Coordinate>()
        queue.add(origin)
        while (queue.isNotEmpty()) {
            val n = queue.pop()
            if (grid.get(n) != '#') {
                grid.set(n, '#')
                queue.add(n.transpose(0, 1))
                queue.add(n.transpose(0, -1))
                queue.add(n.transpose(1, 0))
                queue.add(n.transpose(-1, 0))
            }
        }

        logger.debug { "Filled Grid: \n$grid" }
    }

}