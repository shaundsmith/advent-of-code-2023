package dev.shaundsmith.adventofcode2023.day6

import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.sqrt

class Race(private val time: Long, private val record: Long) {

    fun getTimesAboveRecord(): LongRange {

        /*
            The time for finishing a race is => D = c * (t - c)
            Where D = distance, c = charge, t = time
              - Expanded to => D = -c^2 + ct
            This forms a quadratic curve. If we subtract the record, R, then we can use the quadratic equation
            to find the values of c (charge) which will get a distance above R:
              - distances-above-record => -c^2 + ct - d > 0
         */

        val pointsAboveRecord = getPointsAboveRecord()

        // If the intersection is an integer, then we need to increment or decrement accordingly as we want
        // values higher than the record.
        val lowestPoint = if (isWholeNumber(pointsAboveRecord[0])) pointsAboveRecord[0] + 1 else pointsAboveRecord[0]
        val highestPoint = if (isWholeNumber(pointsAboveRecord[1])) pointsAboveRecord[1] - 1 else pointsAboveRecord[1]

        return LongRange(ceil(lowestPoint).toLong(), floor(highestPoint).toLong())
    }

    private fun getPointsAboveRecord(): List<Double> {

        // Quadratic equation (-b+/-(sqrt(b^2 - 4ac)) / 2a
        // Where t = time, a = -1, c = (1 - record)
        val timeSquared = time * time
        val squareRootPart = sqrt((timeSquared - (4 * record)).toDouble())

        return listOf(
            ((-time) + squareRootPart) / -2,
            ((-time) - squareRootPart) / -2
        )
            .sorted()
    }

    private fun isWholeNumber(input: Double): Boolean {

        return input.rem(1) == 0.0
    }

}