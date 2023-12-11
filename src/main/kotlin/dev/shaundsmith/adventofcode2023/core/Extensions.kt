package dev.shaundsmith.adventofcode2023.core

inline fun <reified T : Any> List<String>.toGrid(mapper: (char: Char) -> T, defaultValue: T): Grid<T> {

    val grid = Grid(Array(this[0].length) { _ -> Array(this.size) { _ -> defaultValue } })

    for ((lineIndex, lineValue) in this.withIndex()) {
        for ((charIndex, charValue) in lineValue.toCharArray().withIndex()) {
            grid.set(Coordinate(charIndex, lineIndex), mapper(charValue))
        }
    }

    return grid
}