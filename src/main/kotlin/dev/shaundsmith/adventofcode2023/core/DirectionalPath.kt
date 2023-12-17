package dev.shaundsmith.adventofcode2023.core

class DirectionalPath(start: Coordinate, direction: Direction) {

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

    fun previousPositions(): List<Pair<Coordinate, Direction>> {

        return steps.subList(0, steps.size - 1)
    }

    fun moveTo(coordinate: Coordinate, direction: Direction) {

        steps.add(Pair(coordinate, direction))
    }

    fun contains(value: Coordinate): Boolean {
        return steps.any { it.first == value }
    }

    fun contains(value: Coordinate, direction: Direction): Boolean {
        return steps.any { it.first == value && it.second == direction }
    }

    fun getAllPositions(): List<Coordinate> {

        return steps.map { it.first }
    }

}