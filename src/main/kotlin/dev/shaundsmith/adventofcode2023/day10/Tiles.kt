package dev.shaundsmith.adventofcode2023.day10

import dev.shaundsmith.adventofcode2023.core.Direction

interface Tile {

    fun changeDirection(currentDirection: Direction): Direction

    fun isShape(from: Direction, to: Direction): Boolean

}

class VerticalPipeTile : Tile {

    override fun changeDirection(currentDirection: Direction): Direction {

        return currentDirection
    }

    override fun isShape(from: Direction, to: Direction): Boolean {

        return listOf(Direction.NORTH, Direction.SOUTH).contains(from) && from == to
    }
}

class HorizontalPipeTile : Tile {

    override fun changeDirection(currentDirection: Direction): Direction {

        return currentDirection
    }

    override fun isShape(from: Direction, to: Direction): Boolean {

        return listOf(Direction.EAST, Direction.WEST).contains(from) && from == to
    }
}

class LTile : Tile {

    override fun changeDirection(currentDirection: Direction): Direction {

        return if (currentDirection == Direction.WEST) Direction.NORTH else Direction.EAST
    }

    override fun isShape(from: Direction, to: Direction): Boolean {

        return (from == Direction.WEST && to == Direction.NORTH) ||
                (from == Direction.SOUTH && to == Direction.EAST)
    }
}

class JTile : Tile {

    override fun changeDirection(currentDirection: Direction): Direction {

        return if (currentDirection == Direction.EAST) Direction.NORTH else Direction.WEST
    }

    override fun isShape(from: Direction, to: Direction): Boolean {

        return (from == Direction.EAST && to == Direction.NORTH) ||
                (from == Direction.SOUTH && to == Direction.WEST)
    }
}

class SevenTile : Tile {

    override fun changeDirection(currentDirection: Direction): Direction {

        return if (currentDirection == Direction.EAST) Direction.SOUTH else Direction.WEST
    }

    override fun isShape(from: Direction, to: Direction): Boolean {

        return (from == Direction.EAST && to == Direction.SOUTH) ||
                (from == Direction.NORTH && to == Direction.WEST)
    }
}

class FTile : Tile {

    override fun changeDirection(currentDirection: Direction): Direction {

        return if (currentDirection == Direction.WEST) Direction.SOUTH else Direction.EAST
    }

    override fun isShape(from: Direction, to: Direction): Boolean {

        return (from == Direction.WEST && to == Direction.SOUTH) ||
                (from == Direction.NORTH && to == Direction.EAST)
    }
}

class GroundTile : Tile {

    override fun changeDirection(currentDirection: Direction): Direction {

        throw IllegalStateException("Moving from Ground")
    }

    override fun isShape(from: Direction, to: Direction): Boolean {

        throw IllegalStateException()
    }
}

class StartTile : Tile {

    override fun changeDirection(currentDirection: Direction): Direction {

        throw IllegalStateException("Moving from Start")
    }

    override fun isShape(from: Direction, to: Direction): Boolean {

        throw IllegalStateException()
    }
}

