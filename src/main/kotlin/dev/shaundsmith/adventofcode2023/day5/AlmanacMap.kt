package dev.shaundsmith.adventofcode2023.day5

class AlmanacMap(private val input: List<AlmanacLine>) {

    fun getDestination(source: Long): Long {

        val destinationLine = input.firstOrNull { it.containsSource(source) }

        return destinationLine?.destinationForSource(source) ?: source
    }

}