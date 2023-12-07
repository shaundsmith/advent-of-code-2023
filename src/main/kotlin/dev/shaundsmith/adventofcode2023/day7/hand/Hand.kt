package dev.shaundsmith.adventofcode2023.day7.hand

import dev.shaundsmith.adventofcode2023.day7.Card

class Hand(private val cards: List<Card>,
           val bid: Int): Comparable<Hand> {

    private val handType: HandType = HandTypeCalculator().getHandType(cards)


    override fun compareTo(other: Hand): Int {

        val handTypeComparison = handType.ordinal.compareTo(other.handType.ordinal)
        var comparison = handTypeComparison
        if (handTypeComparison == 0) {
            for (index in cards.indices) {
                val compareCards = cards[index].value.compareTo(other.cards[index].value)
                if (compareCards != 0) {
                    comparison = compareCards
                    break
                }
            }
        }

        return comparison
    }

    override fun toString(): String {
        val cardsToString = cards.joinToString(separator = "") { it.symbol }
        return "Hand $handType. Cards $cardsToString, Bid $bid"
    }
}