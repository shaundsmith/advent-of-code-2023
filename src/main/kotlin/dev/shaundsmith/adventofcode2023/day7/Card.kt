package dev.shaundsmith.adventofcode2023.day7

enum class Card(val symbol: String, val value: Int) {

    JOKER("j", 1),
    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("T", 10),
    JACK("J", 11),
    QUEEN("Q", 12),
    KING("K", 13),
    ACE("A", 14);

    companion object {

        fun fromSymbol(symbol: String): Card {
            val card = entries.find { it.symbol == symbol }
            checkNotNull(card)

            return card
        }

        fun fromSymbolWithJokers(symbol: String): Card {

            return if (symbol == "J") JOKER else fromSymbol(symbol)
        }

    }

}