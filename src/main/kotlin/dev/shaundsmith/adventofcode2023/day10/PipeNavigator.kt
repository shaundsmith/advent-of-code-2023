package dev.shaundsmith.adventofcode2023.day10

import dev.shaundsmith.adventofcode2023.core.Coordinate
import dev.shaundsmith.adventofcode2023.core.Grid
import io.github.oshai.kotlinlogging.KotlinLogging

class PipeNavigator(
    private val grid: Grid<Tile>,
    private val start: Coordinate
) {

    private val logger = KotlinLogging.logger {}
    val path: Path

    init {
        path = findPathStart()
    }

    fun navigatePath() {

        while (grid.get(path.getCurrentPosition().first)::class != StartTile::class) {
            move(path)
        }
    }

    fun findEnclosedCoordinates(): List<Coordinate> {

        // Ensure we read the start shape accurately
        interpretStartShape()
        val enclosed = ArrayList<Coordinate>()
        for (x in 0..<grid.width()) {
            for (y in 0..<grid.height()) {
                val coordinate = Coordinate(x, y)
                if (!path.contains(coordinate) && isCoordinateEnclosed(coordinate)) {
                    logger.debug { "Found enclosed coordinate ($coordinate)" }
                    enclosed.add(coordinate)
                }
            }
        }

        logger.debug { "Found ${enclosed.size} coordinates" }
        return enclosed
    }

    private fun move(path: Path) {

        val position = path.getCurrentPosition()
        val tile = grid.get(position.first)

        val newDirection = tile.changeDirection(position.second)
        val newPosition = position.first.transpose(newDirection.xModifier, newDirection.yModifier)

        logger.debug { "Moving from ${position.first} (${position.second}) to $newPosition ($newDirection)" }
        path.moveTo(newPosition, newDirection)
    }

    private fun findPathStart(): Path {

        val north = start.transpose(0, -1)
        if (grid.isValid(north) && listOf(VerticalPipeTile::class, SevenTile::class, FTile::class).contains(grid.get(north)::class)) {
            return Path(north, Direction.NORTH)
        }

        val east = start.transpose(1, 0)
        if (grid.isValid(east) && listOf(HorizontalPipeTile::class, JTile::class, SevenTile::class).contains(grid.get(east)::class)) {
            return Path(east, Direction.EAST)
        }

        val south = start.transpose(0, 1)
        if (grid.isValid(south) && listOf(VerticalPipeTile::class, JTile::class, LTile::class).contains(grid.get(south)::class)) {
            return Path(south, Direction.SOUTH)
        }

        val west = start.transpose(-1, 0)
        if (grid.isValid(west) && listOf(HorizontalPipeTile::class, LTile::class, FTile::class).contains(grid.get(west)::class)) {
            return Path(west, Direction.WEST)
        }

        throw IllegalStateException("No start position found")
    }

    private fun interpretStartShape() {

        val initialDirection = path.getInitialPosition().second
        val direction = path.getCurrentPosition().second

        val startShape = listOf(VerticalPipeTile(), HorizontalPipeTile(), FTile(), LTile(), SevenTile(), JTile())
            .find { it.isShape(direction, initialDirection) }!!
        logger.debug { "Start position must be of type ${startShape::class.simpleName}" }

        grid.set(path.getCurrentPosition().first, startShape)
    }

    private fun isCoordinateEnclosed(coordinate: Coordinate): Boolean {

        val above = IntRange(0, coordinate.y - 1)
            .map { Coordinate(coordinate.x, it) }
            .filter { path.contains(it) && grid.get(it)::class != VerticalPipeTile::class }
            .map { grid.get(it)::class }
            .reversed()

        var intersections = 0
        for (index in above.indices) {
            if (above[index] == HorizontalPipeTile::class) {
                // Intersect an edge
                intersections++
            } else if (above[index] == FTile::class && above[index - 1] == JTile::class) {
                // S-Shape - We must be inside
                intersections++
            } else if (above[index] == SevenTile::class && above[index - 1] == LTile::class) {
                // Z-Shape - We must be inside
                intersections++
            }
        }

        return intersections % 2 == 1
    }


}