package dev.shaundsmith.adventofcode2023.day7

import dev.shaundsmith.adventofcode2023.core.PuzzleSolution
import dev.shaundsmith.adventofcode2023.day7.hand.Hand

class Day7 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        val hands = input.map { parseHand(it, withJokers = false) }
            .sorted()

        val score = hands.indices.sumOf { hands[it].bid * (it + 1) }

        return score.toString()
    }

    override fun part2(input: List<String>): String {

        val hands = input.map { parseHand(it, withJokers = true) }
            .sorted()

        val score = hands.indices.sumOf { hands[it].bid * (it + 1) }

        return score.toString()
    }

    private fun parseHand(line: String, withJokers: Boolean): Hand {

        val parts = line.split(" ")
        val cards = parts[0].toCharArray()
            .map { if (withJokers) Card.fromSymbolWithJokers(it.toString()) else Card.fromSymbol(it.toString()) }
        val bid = parts[1].toInt()

        return Hand(cards, bid)
    }

}