package dev.shaundsmith.adventofcode2023.day24

import dev.shaundsmith.adventofcode2023.core.DoubleCoordinate
import io.github.oshai.kotlinlogging.KotlinLogging

class HailIntersections2d(private val hail: List<LinearEquation>) {
    private val logger = KotlinLogging.logger {}

    fun calculateIntersections(): List<DoubleCoordinate> {

        val intersections = ArrayList<DoubleCoordinate>()

        for (i in 0..<hail.size - 1) {
            val source = hail[i]
            for (j in (i + 1)..<hail.size) {
                val target = hail[j]
                val intersection = source.getIntersection(target)
                if (intersection != null) {
                    if (isInTheFuture(source, intersection) && isInTheFuture(target, intersection)) {
                        logger.debug { "$source intersects $target at $intersection" }
                        intersections.add(intersection)
                    } else {
                        logger.debug { "$source intersects $target in the past" }
                    }
                }
            }
        }

        return intersections
    }

    private fun isInTheFuture(equation: LinearEquation, intersection: DoubleCoordinate): Boolean {

        return (equation.velocity.first < 0 && intersection.x < equation.start.x) || (equation.velocity.first > 0 && intersection.x > equation.start.x)
    }


}