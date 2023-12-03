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

}