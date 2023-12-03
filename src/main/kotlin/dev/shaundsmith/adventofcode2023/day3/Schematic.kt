package dev.shaundsmith.adventofcode2023.day3

import dev.shaundsmith.adventofcode2023.core.Coordinate
import dev.shaundsmith.adventofcode2023.core.Grid
import io.github.oshai.kotlinlogging.KotlinLogging

class Schematic(lines: List<String>) {

    private val logger = KotlinLogging.logger {}

    private val grid: Grid<Char>
    private val partNumbers = ArrayList<PartNumber>()
    private val symbols = ArrayList<Coordinate>()
    private val gears = ArrayList<Coordinate>()

    init {

        grid = Grid(Array(lines.size) { _ -> Array(lines[0].length) { _ -> '.' } })

        for ((lineIndex, lineValue) in lines.withIndex()) {
            val number = ArrayList<Coordinate>()
            for ((charIndex, charValue) in lineValue.toCharArray().withIndex()) {
                grid.set(Coordinate(charIndex, lineIndex), charValue)
                if (charValue.isDigit()) {
                    number.add(Coordinate(charIndex, lineIndex))
                } else {
                    if (charValue != '.') {
                        symbols.add(Coordinate(charIndex, lineIndex))
                    }
                    if (charValue == '*') {
                        gears.add(Coordinate(charIndex, lineIndex))
                    }

                    if (number.isNotEmpty()) {
                        addPartNumber(number)
                        number.clear()
                    }
                }
            }

            if (number.isNotEmpty()) {
                addPartNumber(number)
            }
        }
    }

    fun validPartNumbers(): List<PartNumber> {

        return partNumbers.filter { it.boundary.any { partNumber -> symbols.contains(partNumber) } }
    }

    fun gearRatios(): List<Int> {

        val ratios = ArrayList<Int>()
        for (gear in gears) {
            val intersections = partNumbers.filter { it.boundary.contains(gear) }
            logger.debug { "Found gear at $gear with part numbers $intersections" }
            if (intersections.size == 2) {
                ratios.add(intersections[0].numberAsValue() * intersections[1].numberAsValue())
            }
        }

        return ratios
    }


    private fun addPartNumber(numberCoordinates: ArrayList<Coordinate>) {

        val number = numberCoordinates.map { grid.get(it) }
            .joinToString(separator = "")

        partNumbers.add(PartNumber.build(number, numberCoordinates, Coordinate(grid.width(), grid.height())))
    }

}