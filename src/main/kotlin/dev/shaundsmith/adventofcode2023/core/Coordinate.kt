package dev.shaundsmith.adventofcode2023.core

data class Coordinate(val x: Int, val y: Int) {

    fun transpose(x: Int, y: Int): Coordinate {

        return Coordinate(this.x + x, this.y + y)
    }

    fun adjacent(): Set<Coordinate> {
        return hashSetOf(
            transpose(-1, -1),
            transpose(-1, 0),
            transpose(-1, 1),
            transpose(0, -1),
            transpose(0, 1),
            transpose(1, -1),
            transpose(1, 0),
            transpose(1, 1))
    }

    fun isInside(a: Coordinate, b: Coordinate): Boolean {

        return IntRange(a.x, b.x).contains(this.x) && IntRange(a.y, b.y).contains(this.y)
    }

    override fun toString(): String {
        return "($x, $y)"
    }

}
