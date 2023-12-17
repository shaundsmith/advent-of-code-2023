package dev.shaundsmith.adventofcode2023.day17

import dev.shaundsmith.adventofcode2023.core.Coordinate
import dev.shaundsmith.adventofcode2023.core.Grid
import dev.shaundsmith.adventofcode2023.core.Path
import io.github.oshai.kotlinlogging.KotlinLogging

class CityBlock(private val grid: Grid<Int>) {

    private val logger = KotlinLogging.logger {}

    // A* Algorithm
    fun route(): Path {

        val goal = Coordinate(grid.width() - 1, grid.height() - 1)
        val openPaths = mutableListOf(Path(Coordinate(0,0)))
        val bestPath = HashMap<Coordinate, Path>()

        val gScore = HashMap<Coordinate, Int>()
        gScore[Coordinate(0, 0)] = 0

        val fScore = HashMap<Coordinate, Int>()
        fScore[Coordinate(0, 0)] = 0

        while (openPaths.isNotEmpty()) {

            var current = openPaths.minBy { fScore[it.getCurrentPosition()]!! }
            if (current.getCurrentPosition() == goal) {
                logger.debug { "Found route to goal" }
                break
            }
            openPaths.remove(current)
            if (current.containsLoop()) {
                continue
            }
            for (neighbour in calculateNeighbours(current)) {

                val score = gScore[current.getCurrentPosition()]!! + grid.get(neighbour)
                if (!gScore.contains(neighbour) || score < gScore[neighbour]!!) {
                    val nextPath = current.append(neighbour)
                    bestPath[neighbour] = nextPath
                    gScore[neighbour] = score
                    fScore[neighbour] = score + (grid.width() - neighbour.x) + (grid.height() - neighbour.y)
                    if (openPaths.none {it.getCurrentPosition() == neighbour }) {
                        openPaths.add(nextPath)
                        logger.debug { "Adding neighbour $neighbour" }
                    }
                }
            }
        }

        return bestPath[goal]!!
    }

    private fun calculateNeighbours(path: Path): List<Coordinate> {
        val neighbours = ArrayList<Coordinate>()
        val lastPosition = path.getCurrentPosition()
        var canMoveStraight = true
        if (path.size >= 3) {
            val mostRecentPositions = path.getLastNPositions(3)
            canMoveStraight =
                mostRecentPositions.map { it.x }.toSet().size > 1 || mostRecentPositions.map { it.y }.toSet().size > 1
        }

        val direction = path.getLastNPositions(2)
        if (direction.size > 1) {
            if (direction[0].x == direction[1].x) {
                neighbours.addIfValid(lastPosition.transpose(-1, 0))
                neighbours.addIfValid(lastPosition.transpose(1, 0))
                if (canMoveStraight) {
                    neighbours.addIfValid(lastPosition.transpose(direction[1].x - direction[0].x, 0))
                }
            } else {
                neighbours.addIfValid(lastPosition.transpose(0, 1))
                neighbours.addIfValid(lastPosition.transpose(0, -1))
                if (canMoveStraight) {
                    neighbours.addIfValid(lastPosition.transpose(0, direction[1].y - direction[0].y))
                }
            }
        } else {
            neighbours.addIfValid(lastPosition.transpose(0, 1))
            neighbours.addIfValid(lastPosition.transpose(1, 0))
        }

        return neighbours
    }

    private fun ArrayList<Coordinate>.addIfValid(value: Coordinate) {

        if (grid.isValid(value)) {
            this.add(value)
        }
    }

}