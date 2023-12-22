package dev.shaundsmith.adventofcode2023.day17

import dev.shaundsmith.adventofcode2023.core.Coordinate
import dev.shaundsmith.adventofcode2023.core.Direction
import dev.shaundsmith.adventofcode2023.core.Grid
import dev.shaundsmith.adventofcode2023.core.Path
import io.github.oshai.kotlinlogging.KotlinLogging

class CityBlock(private val grid: Grid<Int>, private val ultraCrucibles: Boolean = false) {

    private val logger = KotlinLogging.logger {}
    private val openPaths = mutableListOf(CartPlace(Coordinate(0, 0), Direction.EAST, 0))
    private val goal = Coordinate(grid.width() - 1, grid.height() - 1)
    private val parents = HashMap<CartPlace, CartPlace>()

    // A* Algorithm
    fun route(): Path {

        val gScore = HashMap<CartPlace, Int>()
        gScore[CartPlace(Coordinate(0, 0), Direction.EAST, 0)] = 0

        val fScore = HashMap<CartPlace, Int>()
        fScore[CartPlace(Coordinate(0, 0), Direction.EAST, 0)] = h(Coordinate(0, 0))

        while (openPaths.isNotEmpty()) {

            var current = openPaths.minBy { fScore[it]!! }
            if (current.coordinate == goal && (!ultraCrucibles || current.speed >= 4)) {
                logger.debug { "Found route to goal" }
                break
            }
            openPaths.remove(current)
            for (neighbour in calculateNeighbours(current)) {

                val score = gScore[current]!! + grid.get(neighbour.coordinate)
                if (!gScore.contains(neighbour) || score < gScore[neighbour]!!) {
                    gScore[neighbour] = score
                    fScore[neighbour] = score + h(neighbour.coordinate)
                    openPaths.add(neighbour)
                    logger.debug { "Adding neighbour $neighbour (Score = $score)" }
                    parents[neighbour] = current
                    if (!openPaths.contains(neighbour)) {
                        openPaths.add(neighbour)
                    }
                }
            }
        }

        return Path(path = getOptimalPath().toList())
    }

    private fun calculateNeighbours(position: CartPlace): List<CartPlace> {
        val neighbours = ArrayList<CartPlace>()

        val direction = position.direction
        if (direction == Direction.NORTH || direction == Direction.SOUTH) {
            neighbours.addIfValid(CartPlace(position.coordinate.transpose(-1, 0), Direction.WEST, 1), position.speed)
            neighbours.addIfValid(CartPlace(position.coordinate.transpose(1, 0), Direction.EAST, 1), position.speed)
        } else {
            neighbours.addIfValid(CartPlace(position.coordinate.transpose(0, -1), Direction.NORTH, 1), position.speed)
            neighbours.addIfValid(CartPlace(position.coordinate.transpose(0, 1), Direction.SOUTH, 1), position.speed)
        }
        neighbours.addIfValid(CartPlace(position.coordinate.transpose(direction.xModifier, direction.yModifier), direction, position.speed + 1), position.speed)

        return neighbours
    }

    private fun getOptimalPath(): List<Coordinate> {

        val parents = ArrayList<CartPlace>()
        var target = openPaths.first { it.coordinate == goal }
        parents.add(target)
        while (target.coordinate != Coordinate(0, 0)) {
            val parent = this.parents[target]
            if (parent == null || parent.coordinate == Coordinate(0, 0)) {
                break
            } else {
                parents.add(parent)
                target = parent
            }
        }

        return parents.reversed().map { it.coordinate }
    }

    private fun h(coordinate: Coordinate): Int {

        return ((grid.width() - 1) - coordinate.x) + ((grid.height() - 1) - coordinate.y)
    }

    private fun ArrayList<CartPlace>.addIfValid(value: CartPlace, previousSpeed: Int) {

        if (!grid.isValid(value.coordinate)) {
            return
        }

        if (!ultraCrucibles) {
            if (value.speed <= 3) {
                this.add(value)
            }
            return
        }

        if (previousSpeed >= 4 && value.speed == 1) {
            // Changed direction
           this.add(value)
        } else if (value.speed == previousSpeed + 1 && value.speed <= 10) {
            this.add(value)
        }
    }

}