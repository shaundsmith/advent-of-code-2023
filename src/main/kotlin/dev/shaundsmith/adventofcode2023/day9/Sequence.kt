package dev.shaundsmith.adventofcode2023.day9

import io.github.oshai.kotlinlogging.KotlinLogging

class Sequence(private val values: List<Long>, private val depth: Int = 0) {

    private val logger = KotlinLogging.logger {}

    fun interpolateNext(): Long {

        val interpolation = if (isZeroes()) values.last() else (values.last() + nextLevel().interpolateNext())

        logger.debug { "Next value in sequence ($values) at depth $depth = $interpolation"}

        return interpolation
    }

    fun interpolatePrevious(): Long {

        val interpolation = if (isZeroes()) values.last() else (values.first() - nextLevel().interpolatePrevious())

        logger.debug { "Previous value in sequence ($values) at depth $depth = $interpolation"}

        return interpolation
    }

    private fun nextLevel(): Sequence {

        val nextLevelValues = ArrayList<Long>()
        for (index in 1..<values.size) {
            nextLevelValues.add(values[index] - values[index - 1])
        }

        return Sequence(nextLevelValues, depth + 1)
    }

    private fun isZeroes(): Boolean {

        return values.all { it == 0L }
    }

}