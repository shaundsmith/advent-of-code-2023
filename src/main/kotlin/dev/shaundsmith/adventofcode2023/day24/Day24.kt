package dev.shaundsmith.adventofcode2023.day24

import dev.shaundsmith.adventofcode2023.core.DoubleCoordinate
import dev.shaundsmith.adventofcode2023.core.PuzzleSolution
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlin.math.round

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

        val equations = input.map { parseAs2dLinearEquation(it) }
        val intersections = HailIntersections2d(equations)
        val intersectionPoint = intersections.findCommonIntersection()
        logger.info { "All hailstones intersect at $intersectionPoint relative to speed" }

        val throwLine = LinearEquation.fromCoordinateAndVelocity(intersectionPoint.first, intersectionPoint.second.first, intersectionPoint.second.second)
        logger.info { "Throw trajectory equation: $throwLine" }

        val firstIntersection = getCollisionPosition(input[0], equations[0], throwLine)
        val secondIntersection = getCollisionPosition(input[1], equations[1], throwLine)

        val zVelocity = (firstIntersection.first - secondIntersection.first) / (firstIntersection.second - secondIntersection.second)
        logger.info { "Z velocity is $zVelocity" }
        val zStart = firstIntersection.first - (firstIntersection.second * zVelocity)
        logger.info { "Z start is $zStart" }

        logger.info { "Throwing from ${intersectionPoint.first.x}, ${intersectionPoint.first.y}, $zStart"}
        return (intersectionPoint.first.x + intersectionPoint.first.y + zStart).toLong().toString()
    }

    private val linePattern = Regex("^\\s*(-?\\d+),\\s+(-?\\d+),\\s+(-?\\d+)\\s+@\\s+(-?\\d+),\\s+(-?\\d+),\\s+(-?\\d+)")
    private fun parseAs2dLinearEquation(line: String): LinearEquation {
        val (x, y, _, vx, vy, _) = linePattern.find(line)!!.destructured

        return LinearEquation.fromCoordinateAndVelocity(DoubleCoordinate(x.toDouble(), y.toDouble()),
            vx.toDouble(),
            vy.toDouble())
    }

    private fun getZCoordinateAndVelocity(line: String): Pair<Long, Long> {
        val (x, y, z, vx, vy, vz) = linePattern.find(line)!!.destructured

        return Pair(z.toLong(), vz.toLong())
    }

    private fun getCollisionPosition(input: String, equation: LinearEquation, throwLine: LinearEquation): Pair<Long, Long> {

        val x = throwLine.getIntersection(equation)!!
        val steps = round((x.x - equation.start.x) / equation.velocity.first).toLong()

        val firstEquationZParts = getZCoordinateAndVelocity(input)

        val zIntersection = firstEquationZParts.first + (firstEquationZParts.second * steps)
        logger.info { "$equation intersects trajectory at $zIntersection after $steps time" }

        return Pair(zIntersection, steps)
    }

}