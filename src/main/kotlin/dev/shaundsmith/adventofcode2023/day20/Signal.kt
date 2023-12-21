package dev.shaundsmith.adventofcode2023.day20

data class Signal(
    val high: Boolean,
    val source: String,
    val target: String
) {

    override fun toString(): String {
        return "$source -${if (high) "high" else "low"}-> $target"
    }
}