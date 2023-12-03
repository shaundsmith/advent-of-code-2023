package dev.shaundsmith.adventofcode2023.day3

import dev.shaundsmith.adventofcode2023.core.Coordinate
import io.github.oshai.kotlinlogging.KotlinLogging

class PartNumber(private val number: String,
                 private val coordinates: List<Coordinate>,
                 val boundary: HashSet<Coordinate>) {

    companion object {

        private val logger = KotlinLogging.logger {}

        fun build(number: String, coordinates: List<Coordinate>, gridMax: Coordinate): PartNumber {

            val boundary = HashSet<Coordinate>()

            for (coordinate in coordinates) {
                val surrounding = ArrayList(coordinate.adjacent())

                surrounding.removeIf { coordinates.contains(it) || !it.isInside(Coordinate(0, 0), gridMax)  }
                boundary.addAll(surrounding)
            }

            logger.debug { "Found number $number with boundary:\n\t$boundary" }
            return PartNumber(number, ArrayList(coordinates), boundary)
        }
    }

    fun numberAsValue(): Int {

        return Integer.parseInt(number)
    }

    override fun toString(): String {
        return "Part $number at $coordinates"
    }
}