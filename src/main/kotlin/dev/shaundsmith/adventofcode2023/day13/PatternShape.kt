package dev.shaundsmith.adventofcode2023.day13

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlin.math.min

class PatternShape(val shape: List<String>) {
    val logger = KotlinLogging.logger {}

    fun findReflection(): Pair<Reflection, Int>? {

        val horizontalReflection = IntRange(1, shape.size - 1)
            .find { checkHorizontalReflectionAt(it) }

        val reflection: Pair<Reflection, Int>
        if (horizontalReflection == null) {
            val verticalReflection = IntRange(1, shape[0].length - 1)
                .find { checkVerticalReflectionAt(it) }
            if (verticalReflection == null) {
                return null
            }
            logger.debug { "Found vertical reflection at $verticalReflection" }
            reflection = Pair(Reflection.VERTICAL, verticalReflection!!)
        } else {
            logger.debug { "Found horizontal reflection at $horizontalReflection" }
            reflection = Pair(Reflection.HORIZONTAL, horizontalReflection)
        }

        return reflection
    }

    fun findReflection(not: Pair<Reflection, Int>): Pair<Reflection, Int>? {

        val horizontalReflection = IntRange(1, shape.size - 1)
            .find { checkHorizontalReflectionAt(it) && not != Pair(Reflection.HORIZONTAL, it) }

        val reflection: Pair<Reflection, Int>
        if (horizontalReflection == null) {
            val verticalReflection = IntRange(1, shape[0].length - 1)
                .find { checkVerticalReflectionAt(it) && not != Pair(Reflection.VERTICAL, it) }
            if (verticalReflection == null) {
                return null
            }
            logger.debug { "Found vertical reflection at $verticalReflection" }
            reflection = Pair(Reflection.VERTICAL, verticalReflection!!)
        } else {
            logger.debug { "Found horizontal reflection at $horizontalReflection" }
            reflection = Pair(Reflection.HORIZONTAL, horizontalReflection)
        }

        return reflection
    }

    private fun checkHorizontalReflectionAt(line: Int): Boolean {

        val distance = min(line, shape.size - line)

        return shape.subList(line - distance, line) == shape.subList(line, line + distance).reversed()
    }

    private fun checkVerticalReflectionAt(column: Int): Boolean {

        val distance = min(column, shape[0].length - column)

        return shape
            .all { it.substring(column - distance, column) == it.substring(column, column + distance).reversed() }
    }

}