package dev.shaundsmith.adventofcode2023.day18

import dev.shaundsmith.adventofcode2023.core.Coordinate
import dev.shaundsmith.adventofcode2023.core.Direction
import dev.shaundsmith.adventofcode2023.core.Path
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlin.math.abs

class ShoeLacer(instructions: List<Pair<Direction, Int>>) {
    private val logger = KotlinLogging.logger {  }

    private var path = LongPath(LongCoordinate(0, 0))
    var perimeter = 0L

    init {

        for (instruction in instructions) {
            val lastPosition = path.getCurrentPosition()
            val direction = instruction.first
            val nextPosition = lastPosition.transpose(
                direction.xModifier.toLong() * instruction.second,
                direction.yModifier.toLong() * instruction.second)
            path = path.append(nextPosition)
            logger.debug { "Next position = $nextPosition" }
            perimeter += instruction.second
        }

    }

    fun shoelace(): Long {

        val pathPositions = path.getAllPositions()
        val indices = pathPositions.indices
        var part1 = 0L
        var part2 = 0L
        for (index in indices) {
            if (index == indices.last) {
                part1 += pathPositions[index].x * pathPositions[0].y
                part2 += pathPositions[index].y * pathPositions[0].x
            } else {
                part1 += pathPositions[index].x * pathPositions[index + 1].y
                part2 += pathPositions[index].y * pathPositions[index + 1].x
            }
        }

        return abs(part1 - part2)
    }

}