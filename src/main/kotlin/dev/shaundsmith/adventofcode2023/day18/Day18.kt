package dev.shaundsmith.adventofcode2023.day18

import dev.shaundsmith.adventofcode2023.core.Coordinate
import dev.shaundsmith.adventofcode2023.core.Direction
import dev.shaundsmith.adventofcode2023.core.Path
import dev.shaundsmith.adventofcode2023.core.PuzzleSolution

class Day18 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        val instructions = input.map { s -> s.split(" ") }
            .map { Pair(mapToDirection(it[0]), it[1].toInt())}

        val grid = FoundationPlanner(instructions).toGrid()
        val origin = IntRange(1, grid.width() - 1)
            .map { Coordinate(it, 1)}
            .find { grid.get(it) == '.' && grid.get(Coordinate(it.x - 1, 1)) == '#' }
        FloodFiller(grid).fill(origin!!)

        val count = grid.contents.flatten()
            .count { it == '#' }

        return count.toString()
    }

    override fun part2(input: List<String>): String {

        val instructions = input.map { s -> s.split(" ") }
            .map { parseHex(it[2]) }

        val shoeLacer = ShoeLacer(instructions)
        val areaOfPoints = shoeLacer.shoelace()

        // Perimeter / 2 to counter the fact that we're using coordinates
        // instead of 1m^3 blocks for the area
        // +1 to offset the extra corner not covered by perimeter/2
        val area = areaOfPoints + (shoeLacer.perimeter / 2) + 1

        return area.toString()
    }

    private fun mapToDirection(string: String): Direction {
        return when(string) {
            "U" -> Direction.NORTH
            "D" -> Direction.SOUTH
            "L" -> Direction.WEST
            else -> Direction.EAST
        }
    }

    private fun parseHex(hex: String): Pair<Direction, Int> {

        val number = hex.removePrefix("(#").removeSuffix(")")
        val direction = mapToDirection(number.substring(5).toInt())
        val value = number.substring(0, 5).toInt(16)

        return Pair(direction, value)
    }

    private fun mapToDirection(int: Int): Direction {
        return when(int) {
            3 -> Direction.NORTH
            1 -> Direction.SOUTH
            0 -> Direction.WEST
            else -> Direction.EAST
        }
    }

}