package dev.shaundsmith.adventofcode2023.day21

import dev.shaundsmith.adventofcode2023.core.Coordinate
import dev.shaundsmith.adventofcode2023.core.Direction
import dev.shaundsmith.adventofcode2023.core.Grid
import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.LinkedList

class GardenNavigator(private val grid: Grid<Char>) {
    private val logger = KotlinLogging.logger { }

    val activePositions = LinkedList<Set<Coordinate>>()

    init {

        activePositions.add(setOf(grid.findCoordinate { it == 'S' }!!))
    }

    fun navigate(steps: Int) {

        repeat(steps) { navigate() }
        activePositions.flatten().forEach { grid.set(it, 'O') }
        logger.debug { "Grid:\n$grid"}
    }

    fun navigate() {

        val currentPositions = activePositions.pop()

        val newPositions = HashSet<Coordinate>()
        for (currentPosition in currentPositions) {
            newPositions.addAll(Direction.entries
                .map { currentPosition.transpose(it.xModifier, it.yModifier) }
                .filter { canMoveTo(it) })
        }

        activePositions.add(newPositions)
    }

    private fun canMoveTo(coordinate: Coordinate): Boolean {

        return grid.isValid(coordinate) && grid.get(coordinate) != '#'
    }


}