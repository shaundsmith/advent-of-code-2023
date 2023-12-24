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

    fun findCommonIntersection(): Pair<DoubleCoordinate, Pair<Double, Double>> {

        for (x in -1000..1000) {
            for (y in -1000..1000) {
                val adjusted = adjustVelocity(x, y)
                val intersection = hasSingleIntersection(adjusted)
                if (intersection != null) {
                    return Pair(intersection, Pair(x.toDouble(), y.toDouble()))
                }
            }
        }

        throw IllegalStateException("No common intersection found")
    }

    private fun hasSingleIntersection(hail: List<LinearEquation>): DoubleCoordinate? {

        val intersection = hail[0].getIntersection(hail[1]) ?: return null

        for (h in hail.subList(2, hail.size)) {
            if (h.contains(intersection)) {
                continue
            } else {
                return null
            }
        }

//
//        for (i in 0..<hail.size - 1) {
//            val source = hail[i]
//            for (j in (i + 1)..<hail.size) {
//                val target = hail[j]
//                if (target == source) {
//                    continue
//                }
//                val foundIntersection = source.getIntersection(target)
//                if (foundIntersection == null) {
////                    logger.debug { "Found no intersections for vx=$x, vy=$y" }
//                    return null
//                }
//                if (!isInTheFuture(source, foundIntersection) || !isInTheFuture(target, foundIntersection)) {
////                    logger.debug { "Found no intersections in the future for vx=$x, vy=$y" }
//                    return null
//                }
//                if (intersection != null && foundIntersection != intersection) {
////                    logger.debug { "Found multiple intersections for vx=$x, vy=$y - $source intersects $target at $foundIntersection" }
//                    return null
//                }
//                intersection = foundIntersection
//            }
//        }

        return intersection
    }

    private fun adjustVelocity(xVelocity: Int, yVelocity: Int): List<LinearEquation> {

        return hail.map { LinearEquation.fromCoordinateAndVelocity(it.start, it.velocity.first - xVelocity, it.velocity.second - yVelocity)}
    }

    private fun isInTheFuture(equation: LinearEquation, intersection: DoubleCoordinate): Boolean {

        return (equation.velocity.first < 0 && intersection.x < equation.start.x) || (equation.velocity.first > 0 && intersection.x > equation.start.x)
    }


}