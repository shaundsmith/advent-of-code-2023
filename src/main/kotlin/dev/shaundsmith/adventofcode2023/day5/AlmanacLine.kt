package dev.shaundsmith.adventofcode2023.day5

data class AlmanacLine(
    val destination: Long,
    val source: Long,
    val size: Long
) {

    fun sourceRange(): LongRange {

        return LongRange(source, source + size)
    }

    fun destinationForSource(source: Long): Long {

        return (source - this.source) + destination
    }

    fun containsSource(source: Long): Boolean {

        return sourceRange().contains(source)
    }
}