package dev.shaundsmith.adventofcode2023.day7.hand

import dev.shaundsmith.adventofcode2023.day7.Card
import io.github.oshai.kotlinlogging.KotlinLogging

internal class HandTypeCalculator {

    private val logger = KotlinLogging.logger {}

    fun getHandType(cards: List<Card>): HandType {

        check(cards.size == 5)

        val cardMap = HashMap<Card, Int>()
        for (card in cards) {
            cardMap[card] = if (cardMap.contains(card)) cardMap[card]!! + 1 else 1
        }

        val cardChecks = listOf(this::fiveOfAKind,
            this::fourOfAKind,
            this::fullHouse,
            this::threeOfAKind,
            this::twoPair,
            this::onePair)

        var handType = HandType.HIGH_CARD
        for (cardCheck in cardChecks) {
            val cardCheckValue = cardCheck(cardMap)
            if (cardCheckValue != null) {
                handType = cardCheckValue
                logger.debug { "Found hand type $handType for cards ${cards.joinToString(separator = "") { it.symbol }}" }
                break
            }
        }

        return handType
    }

    private fun fiveOfAKind(cardMap: Map<Card, Int>): HandType? {

        val fiveOfAKind = cardMap.size == 1 || (cardMap.size == 2 && cardMap.contains(Card.JOKER))

        return if (fiveOfAKind) HandType.FIVE_OF_A_KIND else null
    }

    private fun fourOfAKind(cardMap: Map<Card, Int>): HandType? {

        val jokerCount = if (cardMap.contains(Card.JOKER)) cardMap[Card.JOKER]!! else 0
        val fourOfAKind = jokerCount == 4 || cardMap.filterKeys { it != Card.JOKER }.filterValues { it + jokerCount == 4 }.isNotEmpty()

        return if (fourOfAKind) HandType.FOUR_OF_A_KIND else null
    }

    private fun fullHouse(cardMap: Map<Card, Int>): HandType? {

        // We can be lazy - the only other hand type that can contain 2 types of cards is four of a kind
        val lazyFullHouse = cardMap.size == 2 || (cardMap.size == 3 && cardMap.contains(Card.JOKER))

        return if (lazyFullHouse) HandType.FULL_HOUSE else null
    }

    private fun threeOfAKind(cardMap: Map<Card, Int>): HandType? {

        val jokerCount = if (cardMap.contains(Card.JOKER)) cardMap[Card.JOKER]!! else 0
        val threeOfAKind = jokerCount == 3 || cardMap.filterKeys { it != Card.JOKER }.filterValues { it + jokerCount == 3 }.isNotEmpty()

        return if (threeOfAKind) HandType.THREE_OF_A_KIND else null
    }

    private fun twoPair(cardMap: Map<Card, Int>): HandType? {

        var jokerCount = if (cardMap.contains(Card.JOKER)) cardMap[Card.JOKER]!! else 0

        val naturalTwoPair = cardMap.count { it.value == 2 } == 2 && jokerCount == 0
        var jokerTwoPair = false
        if (!naturalTwoPair) {
            var pairCount = 0
            for (card in cardMap.keys.filter { it != Card.JOKER }) {
                if (cardMap[card] == 2) {
                    pairCount++
                } else if (jokerCount > 0) {
                    pairCount++
                    jokerCount--
                }
            }
            jokerTwoPair = pairCount == 2
        }

        return if (naturalTwoPair || jokerTwoPair) HandType.TWO_PAIR else null
    }

    private fun onePair(cardMap: Map<Card, Int>): HandType? {

        val jokerCount = if (cardMap.contains(Card.JOKER)) cardMap[Card.JOKER]!! else 0
        val pair = jokerCount == 2 || cardMap.filterKeys { it != Card.JOKER }.filterValues { it + jokerCount == 2 }.isNotEmpty()

        return if (pair) HandType.ONE_PAIR else null
    }

}