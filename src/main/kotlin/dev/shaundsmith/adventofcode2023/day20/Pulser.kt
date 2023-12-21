package dev.shaundsmith.adventofcode2023.day20

interface Pulser {

    val targets: List<String>
    val name: String

    fun process(signal: Signal): List<Signal>
    fun isInitialState(): Boolean
    fun registerSource(source: String) {}

}