package dev.shaundsmith.adventofcode2023.day18

data class LongCoordinate(val x: Long, val y: Long) {

    fun transpose(x: Long, y: Long): LongCoordinate {

        return LongCoordinate(this.x + x, this.y + y)
    }

    override fun toString(): String {
        return "($x, $y)"
    }

}
