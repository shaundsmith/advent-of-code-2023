package dev.shaundsmith.adventofcode2023.day20

class ConjunctionPulser(override val name: String,
                        override val targets: List<String>): Pulser {

    var memory = HashMap<String, Boolean>()

    override fun process(signal: Signal): List<Signal> {

        memory[signal.source] = signal.high
        val highPulse = !memory.values.all { it }

        return targets.map { Signal(highPulse, name, it) }
    }

    override fun isInitialState(): Boolean {
        return memory.values.all { !it }
    }

    override fun registerSource(source: String) {
        memory[source] = false
    }
}