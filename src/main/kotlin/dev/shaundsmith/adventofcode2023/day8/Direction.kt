package dev.shaundsmith.adventofcode2023.day8

enum class Direction(private val symbol: String, private val binaryValue: Int) {

    LEFT("L", 0),
    RIGHT("R", 1);

    companion object {

        fun fromSymbol(symbol: String): Direction {

            val direction = entries.find { it.symbol == symbol }
            checkNotNull(direction)

            return direction
        }

    }

}