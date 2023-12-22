package dev.shaundsmith.adventofcode2023.day22

import dev.shaundsmith.adventofcode2023.core.PuzzleSolution

class Day22 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        val bricks = input.mapIndexed { i, l -> parseLine(i, l) }
        val brickStack = BrickStack(bricks)

        return brickStack.getBricksAbleToBeRemoved().toString()
    }

    override fun part2(input: List<String>): String {

        val bricks = input.mapIndexed { i, l -> parseLine(i, l) }
        val brickStack = BrickStack(bricks)

        return brickStack.howManyDropped().toString()
    }

    private val linePattern = Regex("(\\d+),(\\d+),(\\d+)~(\\d+),(\\d+),(\\d+)")
    private fun parseLine(index: Int, line: String): Brick {
        val (x1, y1, z1, x2, y2, z2) = linePattern.find(line)!!.destructured

        return Brick(
            (index + 1).toString(),
            IntRange(x1.toInt(), x2.toInt()),
            IntRange(y1.toInt(), y2.toInt()),
            IntRange(z1.toInt(), z2.toInt()))
    }

}