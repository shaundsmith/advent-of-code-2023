package dev.shaundsmith.adventofcode2023.day24

import dev.shaundsmith.adventofcode2023.core.DoubleCoordinate
import io.github.oshai.kotlinlogging.KotlinLogging
import java.lang.Math.abs
import kotlin.math.round

class LinearEquation(private val slope: Double,
                     private val constant: Double,
                     val start: DoubleCoordinate,
                     val velocity: Pair<Double, Double>) {
    private val logger = KotlinLogging.logger {}

    companion object {

        fun fromCoordinateAndVelocity(coordinate: DoubleCoordinate, velocityX: Double, velocityY: Double): LinearEquation {

            val slope = if (velocityX == 0.0) 0.0 else velocityY / velocityX

            val constant = coordinate.y - (slope * coordinate.x)

            return LinearEquation(slope, constant, coordinate, Pair(velocityX, velocityY))
        }

    }

    fun getIntersection(linearEquation: LinearEquation): DoubleCoordinate? {

        if (slope == linearEquation.slope) {
            // Parallel
            logger.debug { "$this and $linearEquation do not intersect" }
            return null
        }

        return DoubleCoordinate(
            (linearEquation.constant - this.constant) / (this.slope - linearEquation.slope),
            (slope * (linearEquation.constant - this.constant)/(this.slope - linearEquation.slope)) + constant
        )
    }

    fun contains(coordinate: DoubleCoordinate): Boolean {

        return abs(coordinate.y - ((coordinate.x * slope) + constant)) < 1
    }

    override fun toString(): String {
        return "y = ${slope}x + $constant"
    }
}