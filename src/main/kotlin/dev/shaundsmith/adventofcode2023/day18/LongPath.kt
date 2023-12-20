package dev.shaundsmith.adventofcode2023.day18

class LongPath(start: LongCoordinate? = null, path: ArrayList<LongCoordinate>? = null) {

    private val steps = ArrayList<LongCoordinate>()
    val size get(): Long = steps.size.toLong()

    init {
        if (start != null) {
            steps.add(start)
        } else if (path != null) {
            steps.addAll(path)
        }
    }

    fun getCurrentPosition(): LongCoordinate {

        return steps.last()
    }

    fun containsLoop(): Boolean {
        return steps.size != steps.toSet().size
    }

    fun append(coordinate: LongCoordinate): LongPath {

        val newPath = ArrayList<LongCoordinate>()
        newPath.addAll(steps)
        newPath.add(coordinate)
        return LongPath(path = newPath)
    }

    fun contains(value: LongCoordinate): Boolean {
        return steps.any { it == value }
    }

    fun getAllPositions(): List<LongCoordinate> {

        return steps.map { it }
    }

    fun getLastNPositions(n: Int): List<LongCoordinate> {

        if (size <= n) {
            return steps.toList()
        }
        return steps.subList(size.toInt() - n, size.toInt())
    }

}