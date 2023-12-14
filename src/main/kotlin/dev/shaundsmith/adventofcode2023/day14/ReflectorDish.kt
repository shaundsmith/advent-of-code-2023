package dev.shaundsmith.adventofcode2023.day14

import dev.shaundsmith.adventofcode2023.core.Coordinate
import dev.shaundsmith.adventofcode2023.core.Grid
import io.github.oshai.kotlinlogging.KotlinLogging

class ReflectorDish(private var grid: Grid<Char>) {

    private val logger = KotlinLogging.logger {}
    private val cache = LinkedHashMap<String, Grid<Char>>()

    fun getRowValues(): Long {

        val gridHeight = grid.height()

        return LongRange(0, gridHeight - 1L)
            .sumOf { grid.row(it.toInt()).count { c -> c == 'O' } * (gridHeight - it) }
    }

    fun tilt() {

        for (i in 0..<grid.width()) {
            rollNorth()
        }
        logger.debug { "Tilted Grid: \n$grid" }
    }

    fun spin(spinTimes: Int): Long {

        var modulus = 0
        var loopStart = 0
        for (counter in 0..<spinTimes) {
            val flattenedGrid = flattenGrid()
            if (!cache.contains(flattenedGrid)) {
                cache[flattenedGrid] = Grid.clone(grid)
                rollNorth()
                rollWest()
                rollSouth()
                rollEast()
                logger.info {"Count $counter: ${getRowValues()}"}
            } else {
                loopStart = cache.keys.indexOf(flattenedGrid)
                logger.info { "Looping back to $loopStart ($counter)" }
                modulus = counter - loopStart
                break
            }
        }
        grid = cache[ArrayList(cache.keys)[loopStart + 1 + (spinTimes % modulus)]]!!

        return getRowValues()
    }

    private fun rollNorth() {

        for (column in 0..<grid.width()) {
            val columnValues = grid.column(column)

            val newPositions = roll(columnValues)

            for (index in columnValues.indices) {
                val character = columnValues[index]
                if (character != '#') {
                    grid.set(Coordinate(column, index), if (newPositions.contains(index)) 'O' else '.')
                }
            }
        }
    }

    private fun rollSouth() {

        for (column in 0..<grid.width()) {

            val columnValues = grid.column(column)
            val reversedColumnValues = columnValues.reversed()

            val newPositions = roll(reversedColumnValues).map { grid.height() - 1 - it }

            for (index in columnValues.indices) {
                val character = columnValues[index]
                if (character != '#') {
                    grid.set(Coordinate(column, index), if (newPositions.contains(index)) 'O' else '.')
                }
            }
        }
    }

    private fun rollWest() {

        for (row in 0..<grid.width()) {

            val rowValues = grid.row(row)

            val newPositions = roll(rowValues)

            for (index in rowValues.indices) {
                val character = rowValues[index]
                if (character != '#') {
                    grid.set(Coordinate(index, row), if (newPositions.contains(index)) 'O' else '.')
                }
            }
        }
    }

    private fun rollEast() {

        for (row in 0..<grid.width()) {

            val rowValues = grid.row(row)
            val reversedRowValues = rowValues.reversed()

            val newPositions = roll(reversedRowValues).map { grid.height() - 1 - it }

            for (index in rowValues.indices) {
                val character = rowValues[index]
                if (character != '#') {
                    grid.set(Coordinate(index, row), if (newPositions.contains(index)) 'O' else '.')
                }
            }
        }
    }

    private fun roll(values: List<Char>): List<Int> {

        val rolledPositions = values.toMutableList()
        rolledPositions.removeAll { it == '.' }

        val newPositions = ArrayList<Int>()
        var offset = 0
        var lastRockPosition: Int
        var positionIndex = 0
        var lastRockCount = 0
        for (index in rolledPositions.indices) {
            val character = rolledPositions[index]
            if (character == 'O') {
                newPositions.add(offset + positionIndex)
                positionIndex++
            } else if (character == '#') {
                lastRockPosition = values.indexOf('#', lastRockCount)
                lastRockCount++
                offset = lastRockPosition + 1
                positionIndex = 0
            }
        }

        return newPositions
    }


    private fun <T> List<T>.indexOf(element: T, occurrence: Int): Int {

        val indices = this.withIndex()
            .filter { (_, value) -> value == element }
            .map { it.index }

        return indices[occurrence]
    }

    private fun flattenGrid(): String {

        return grid.contents.joinToString("") { it.joinToString("") }
    }

}