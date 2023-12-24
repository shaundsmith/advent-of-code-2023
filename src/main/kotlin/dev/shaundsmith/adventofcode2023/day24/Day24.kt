package dev.shaundsmith.adventofcode2023.day24

import dev.shaundsmith.adventofcode2023.core.DoubleCoordinate
import dev.shaundsmith.adventofcode2023.core.PuzzleSolution
import io.github.oshai.kotlinlogging.KotlinLogging

class Day24(val testArea2D: LongRange = LongRange(200000000000000L, 400000000000000L)) : PuzzleSolution {
    private val logger = KotlinLogging.logger {}

    override fun part1(input: List<String>): String {

        val equations = input.map { parseAs2dLinearEquation(it) }
        val intersections = HailIntersections2d(equations)

        val intersectionPoints = intersections.calculateIntersections()

        val numberOfIntersections = intersectionPoints.count {
            (it.x >= testArea2D.first && it.x <= testArea2D.last) &&
            (it.y >= testArea2D.first && it.y <= testArea2D.last)
        }

        return numberOfIntersections.toString()
    }

    override fun part2(input: List<String>): String {

        return ""
    }

    private val linePattern = Regex("^\\s*(-?\\d+),\\s+(-?\\d+),\\s+(-?\\d+)\\s+@\\s+(-?\\d+),\\s+(-?\\d+),\\s+(-?\\d+)")
    private fun parseAs2dLinearEquation(line: String): LinearEquation {
        val (x, y, _, vx, vy, _) = linePattern.find(line)!!.destructured

        return LinearEquation.fromCoordinateAndVelocity(DoubleCoordinate(x.toDouble(), y.toDouble()),
            vx.toDouble(),
            vy.toDouble())
    }

}