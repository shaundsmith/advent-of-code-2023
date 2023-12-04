package dev.shaundsmith.adventofcode2023.day4

import dev.shaundsmith.adventofcode2023.core.PuzzleSolution
import io.github.oshai.kotlinlogging.KotlinLogging

class Day4 : PuzzleSolution {

    private val logger = KotlinLogging.logger {}


    private val cardRegex = Regex("Card +(\\d+): *((?:\\d+ +?)+)\\|((?: +?\\d+)+)")

    override fun part1(input: List<String>): String {

        val value = input.map { parseLine(it) }
            .sumOf { it.calculateValue() }

        return value.toString()
    }

    override fun part2(input: List<String>): String {

        val cardCounts = HashMap<Long, Long>()
        val originalCards = input.map { parseLine(it) }

        originalCards.forEach { cardCounts[it.number] = 1 }

        for (card in originalCards) {

            val winningCount = card.winningCount()
            if (winningCount == 0) {
                continue
            }

            val startingPoint = card.number + 1
            logger.debug { "Adding cards ${LongRange(startingPoint, winningCount.toLong())}" }
            for (i in 0 until winningCount) {
                if (!cardCounts.contains(startingPoint + i)) {
                    break
                }
                cardCounts[startingPoint + i] = cardCounts[startingPoint + i]!!.plus(cardCounts[card.number]!!)
            }

        }

        return cardCounts.values.sum().toString()
    }

    private fun parseLine(line: String): ScratchCard {

        val (cardNumber, winningNumbers, cardNumbers) = cardRegex.find(line)!!.destructured

        return ScratchCard(cardNumber.toLong(),
            winningNumbers.split(" ").filter { it.isNotBlank() }.map { Integer.parseInt(it.trim()) },
            cardNumbers.split(" ").filter { it.isNotBlank() }.map { Integer.parseInt(it.trim()) })
    }

}