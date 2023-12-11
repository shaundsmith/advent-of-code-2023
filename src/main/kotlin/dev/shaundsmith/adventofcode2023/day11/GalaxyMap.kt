package dev.shaundsmith.adventofcode2023.day11

import dev.shaundsmith.adventofcode2023.core.Coordinate
import dev.shaundsmith.adventofcode2023.core.Grid
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class GalaxyMap(private val universe: Grid<Char>,
private val expansionSize: Int) {

    private val logger = KotlinLogging.logger {}

    private val emptyColumns: List<Int>
    private val emptyRows: List<Int>

    init {
        emptyColumns = IntRange(0, universe.width() - 1)
            .map { Pair(it, universe.column(it)) }
            .filter { isEmpty(it.second) }
            .map { it.first }

        emptyRows = IntRange(0, universe.height() - 1)
            .map { Pair(it, universe.row(it)) }
            .filter { isEmpty(it.second) }
            .map { it.first }

        logger.debug { "Empty space columns ($emptyColumns)" }
        logger.debug { "Empty space rows ($emptyRows)" }
    }

    fun findShortestDistancesBetweenGalaxies(): List<Int> {

        val galaxies = findGalaxies()

        return IntRange(0, galaxies.size - 2)
            .flatMap { findDistances(galaxies[it], galaxies.subList(it + 1, galaxies.size)) }
    }

    private fun findGalaxies(): List<Coordinate> {

        return universe.findCoordinates { it == '#' }
    }

    private fun findDistances(origin: Coordinate, otherGalaxies: List<Coordinate>): List<Int> {

        logger.debug { "Finding distances from $origin for ${otherGalaxies.size} galaxies" }
        val distances = ArrayList<Int>()
        for (otherGalaxy in otherGalaxies) {
            var distance = abs(origin.x - otherGalaxy.x) + abs(origin.y - otherGalaxy.y)
            distance += expand(origin.x, otherGalaxy.x, emptyColumns)
            distance += expand(origin.y, otherGalaxy.y, emptyRows)
            logger.debug { "Distance from $origin to $otherGalaxy is $distance" }
            distances.add(distance)
        }

        return distances
    }

    private fun isEmpty(elements: List<Char>): Boolean {

        return elements.toSet().size == 1 && elements[0] == '.'
    }

    private fun expand(start: Int, end: Int, empty: List<Int>): Int {

        val numberOfExpansions = IntRange(min(start, end), max(start, end))
            .count { empty.contains(it) }

        return numberOfExpansions * (expansionSize - 1)
    }


}