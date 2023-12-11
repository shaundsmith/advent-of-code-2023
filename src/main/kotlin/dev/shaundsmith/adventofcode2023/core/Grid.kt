package dev.shaundsmith.adventofcode2023.core

class Grid<T>(private val contents: Array<Array<T>>) {

    fun get(position: Coordinate): T {
        return contents[position.x][position.y]
    }

    fun set(position: Coordinate, value: T) {
        contents[position.x][position.y] = value
    }

    fun width(): Int {
        return contents.size
    }

    fun height(): Int {
        return contents[0].size
    }

    fun findCoordinate(predicate: (T) -> Boolean): Coordinate? {

        for (x in 0..<width()) {
            for (y in 0..<height()) {
                if (predicate.invoke(get(Coordinate(x, y)))) {
                    return Coordinate(x, y)
                }
            }
        }

        return null
    }

    fun isValid(coordinate: Coordinate): Boolean {

        val isNegative = coordinate.x < 0 || coordinate.y < 0
        val isLarger = coordinate.x >= width() || coordinate.y >= height()

        return !isNegative && !isLarger
    }

}