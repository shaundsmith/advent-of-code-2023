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

    fun column(x: Int): List<T> {
        return IntRange(0, height() - 1)
            .map { contents[x][it] }
    }

    fun row(y: Int): List<T> {
        return IntRange(0, width() - 1)
            .map { contents[it][y] }
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

    fun findCoordinates(predicate: (T) -> Boolean): List<Coordinate> {

        val coordinates = ArrayList<Coordinate>()
        for (x in 0..<width()) {
            for (y in 0..<height()) {
                if (predicate.invoke(get(Coordinate(x, y)))) {
                    coordinates.add(Coordinate(x, y))
                }
            }
        }

        return coordinates
    }

    fun isValid(coordinate: Coordinate): Boolean {

        val isNegative = coordinate.x < 0 || coordinate.y < 0
        val isLarger = coordinate.x >= width() || coordinate.y >= height()

        return !isNegative && !isLarger
    }

}