package dev.shaundsmith.adventofcode2023.day16

import dev.shaundsmith.adventofcode2023.core.*

class Day16 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        val grid = input.toGrid({ c -> MirrorType.fromCharacter(c) }, MirrorType.AIR)
        val mirrorGrid = MirrorGrid(grid, Pair(Coordinate(0, 0), Direction.EAST))
        mirrorGrid.mapBeams()

        return findEnergisedFor(grid, Pair(Coordinate(0,0), Direction.EAST)).toString()
    }

    override fun part2(input: List<String>): String {

        val grid = input.toGrid({ c -> MirrorType.fromCharacter(c) }, MirrorType.AIR)
        val part1 = IntRange(0, grid.height() - 1)
            .flatMap { listOf(
                Pair(Coordinate(0, it), Direction.EAST),
                Pair(Coordinate(grid.width() - 1, it), Direction.WEST)) }
            .toMutableList()
        val part2 = IntRange(0, grid.width() - 1)
            .flatMap { listOf(
                Pair(Coordinate(it, 0), Direction.SOUTH),
                Pair(Coordinate(it, grid.height() - 1), Direction.NORTH)) }
            .toMutableList()

        val highestEnergised = (part1 + part2).maxOfOrNull { findEnergisedFor(grid, it) }

        return highestEnergised.toString()
    }

    private fun findEnergisedFor(grid: Grid<MirrorType>, start: Pair<Coordinate, Direction>): Int {

        val mirrorGrid = MirrorGrid(grid, start)
        mirrorGrid.mapBeams()

        return mirrorGrid.energized
    }

}