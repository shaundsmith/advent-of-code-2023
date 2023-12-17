package dev.shaundsmith.adventofcode2023.core

class Path(start: Coordinate? = null, path: ArrayList<Coordinate>? = null) {

    private val steps = ArrayList<Coordinate>()
    val size get(): Long = steps.size.toLong()

    init {
        if (start != null) {
            steps.add(start)
        } else if (path != null) {
            steps.addAll(path)
        }
    }

    fun getInitialPosition(): Coordinate {

        return steps.first()
    }

    fun getCurrentPosition(): Coordinate {

        return steps.last()
    }

    fun previousPositions(): List<Coordinate> {

        return steps.subList(0, steps.size - 1)
    }

    fun containsLoop(): Boolean {
        return steps.size != steps.toSet().size
    }

    fun append(coordinate: Coordinate): Path {

        val newPath = ArrayList<Coordinate>()
        newPath.addAll(steps)
        newPath.add(coordinate)
        return Path(path = newPath)
    }

    fun contains(value: Coordinate): Boolean {
        return steps.any { it == value }
    }

    fun getAllPositions(): List<Coordinate> {

        return steps.map { it }
    }

    fun getLastNPositions(n: Int): List<Coordinate> {

        if (size <= n) {
            return steps.toList()
        }
        return steps.subList(size.toInt() - n, size.toInt())
    }

}