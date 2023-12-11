package dev.shaundsmith.adventofcode2023.day10

import dev.shaundsmith.adventofcode2023.core.PuzzleSolution
import dev.shaundsmith.adventofcode2023.core.toGrid

class Day10 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        val navigator = buildPipeNavigator(input)
        navigator.navigatePath()

        return (navigator.path.size / 2).toString()
    }

    override fun part2(input: List<String>): String {

        val navigator = buildPipeNavigator(input)
        navigator.navigatePath()
        val enclosedPoints = navigator.findEnclosedCoordinates()

        return enclosedPoints.size.toString()
    }

    private fun buildPipeNavigator(input: List<String>): PipeNavigator {

        val grid = input.toGrid(this::parseTile, GroundTile())
        val start = grid.findCoordinate { it::class == StartTile()::class }!!

        return PipeNavigator(grid, start)
    }

    private fun parseTile(c: Char): Tile {

        return when (c) {
            '|' -> VerticalPipeTile()
            '-' -> HorizontalPipeTile()
            'L' -> LTile()
            'J' -> JTile()
            '7' -> SevenTile()
            'F' -> FTile()
            '.' -> GroundTile()
            'S' -> StartTile()
            else -> throw IllegalStateException("Invalid tile type")
        }
    }

}