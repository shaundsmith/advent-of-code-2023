package dev.shaundsmith.adventofcode2023.day10

import dev.shaundsmith.adventofcode2023.core.Coordinate

class Path(start: Coordinate, direction: Direction) {

    private val steps = ArrayList<Pair<Coordinate, Direction>>()
    val size get(): Long = steps.size.toLong()

    init {
        steps.add(Pair(start, direction))
    }

    fun getInitialPosition(): Pair<Coordinate, Direction> {

        return steps.first()
    }

    fun getCurrentPosition(): Pair<Coordinate, Direction> {

        return steps.last()
    }

    fun moveTo(coordinate: Coordinate, direction: Direction) {

        steps.add(Pair(coordinate, direction))
    }

    fun contains(value: Coordinate): Boolean {
        return steps.any { it.first == value }
    }


}