package dev.shaundsmith.adventofcode2023.day4

import io.github.oshai.kotlinlogging.KotlinLogging

class ScratchCard(val number: Long,
    private val winningNumbers: List<Int>,
    private val cardNumbers: List<Int>) {

    private val logger = KotlinLogging.logger {}

    fun calculateValue(): Int {

        val winningCount = winningCount()
        val score = if (winningCount == 0) 0 else 1 shl (winningCount - 1)

        logger.debug { "Found $winningCount wins for card $number. Score = $score" }

        return score
    }

    fun winningCount(): Int {

        return cardNumbers.count { winningNumbers.contains(it) }
    }

    override fun toString(): String {
        return "Card $number"
    }
}