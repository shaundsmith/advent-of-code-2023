package dev.shaundsmith.adventofcode2023.day16

import dev.shaundsmith.adventofcode2023.core.Coordinate
import dev.shaundsmith.adventofcode2023.core.Direction
import dev.shaundsmith.adventofcode2023.core.Grid
import dev.shaundsmith.adventofcode2023.core.Path
import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.*

class MirrorGrid(private val grid: Grid<MirrorType>, start: Pair<Coordinate, Direction>) {
    private val logger = KotlinLogging.logger {}

    private val activePaths = LinkedList<Path>()
    private val finishedPaths = ArrayList<Path>()

    val energized get() = calculateEnergized()

    init {
        activePaths.add(Path(start.first, start.second))
    }

    fun mapBeams() {

        do {
            val activePath = activePaths.pop()
            mapBeam(activePath)
            finishedPaths.add(activePath)
        } while (activePaths.isNotEmpty())

    }

    private val verticalDirections = listOf(Direction.NORTH, Direction.SOUTH)
    private val horizontalDirections = listOf(Direction.EAST, Direction.WEST)
    private fun mapBeam(path: Path) {

        do {
            val currentPosition = path.getCurrentPosition()
            val currentMirrorType = grid.get(currentPosition.first)
            logger.debug { "Currently on $currentPosition" }
            when {
                currentMirrorType == MirrorType.VERTICAL_SPLITTER
                        && horizontalDirections.contains(currentPosition.second) -> {
                    addNewPath(currentPosition.first.transpose(0, 1), Direction.SOUTH)
                    addNewPath(currentPosition.first.transpose(0, -1), Direction.NORTH)
                    break
                }
                currentMirrorType == MirrorType.HORIZONTAL_SPLITTER
                        && verticalDirections.contains(currentPosition.second) -> {
                    addNewPath(currentPosition.first.transpose(1, 0), Direction.EAST)
                    addNewPath(currentPosition.first.transpose(-1, 0), Direction.WEST)
                    break
                }
                currentMirrorType == MirrorType.RIGHT_MIRROR -> {
                    mapBeamToRightMirror(path, currentPosition)
                }
                currentMirrorType == MirrorType.LEFT_MIRROR -> {
                    mapBeamToLeftMirror(path, currentPosition)
                }
                else -> {
                    val nextPosition = currentPosition.first.transpose(
                        currentPosition.second.xModifier,
                        currentPosition.second.yModifier)
                    logger.debug { "Moving to $nextPosition" }
                    path.moveTo(nextPosition, currentPosition.second)
                }
            }
        } while (!path.hasTerminated())
    }

    private fun addNewPath(coordinate: Coordinate, direction: Direction) {

        if (grid.isValid(coordinate)) {
            logger.debug { "Splitting to $coordinate to the $direction" }
            activePaths.add(Path(coordinate, direction))
        }
    }

    private fun mapBeamToRightMirror(path: Path, currentPosition: Pair<Coordinate, Direction>) {

        val nextDirection = when (currentPosition.second) {
            Direction.NORTH -> Direction.WEST
            Direction.SOUTH -> Direction.EAST
            Direction.EAST -> Direction.SOUTH
            Direction.WEST -> Direction.NORTH
        }

        val nextPosition = currentPosition.first.transpose(nextDirection.xModifier, nextDirection.yModifier)
        logger.debug { "Moving from mirror '/' to $nextPosition" }
        path.moveTo(nextPosition, nextDirection)
    }

    private fun mapBeamToLeftMirror(path: Path, currentPosition: Pair<Coordinate, Direction>) {

        val nextDirection = when (currentPosition.second) {
            Direction.NORTH -> Direction.EAST
            Direction.SOUTH -> Direction.WEST
            Direction.EAST -> Direction.NORTH
            Direction.WEST -> Direction.SOUTH
        }

        val nextPosition = currentPosition.first.transpose(nextDirection.xModifier, nextDirection.yModifier)
        logger.debug { "Moving from mirror '\\' to $nextPosition" }
        path.moveTo(nextPosition, nextDirection)
    }

    private fun Path.hasTerminated(): Boolean {

        val currentPosition = this.getCurrentPosition()
        val outsideGrid = !grid.isValid(currentPosition.first)
        val alreadySeen = finishedPaths.any { it.contains(currentPosition.first, currentPosition.second) }
        if (outsideGrid) logger.debug { "${currentPosition.first} is outside the grid" }
        if (alreadySeen) logger.debug { "Already seen $currentPosition" }

        val terminated = outsideGrid || alreadySeen
        return terminated
    }

    private fun calculateEnergized(): Int {

        return finishedPaths.flatMap { it.getAllPositions() }
            .toSet()
            .count { grid.isValid(it) }
    }

}