package dev.shaundsmith.adventofcode2023.day10

enum class Direction(val xModifier: Int, val yModifier: Int) {

    NORTH(0, -1),
    SOUTH(0, 1),
    WEST(-1, 0),
    EAST(1, 0)

}