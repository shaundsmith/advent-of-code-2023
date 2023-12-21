package dev.shaundsmith.adventofcode2023.day20

class FlipFlopPulser(override val name: String,
                     override val targets: List<String>): Pulser {

    private var on = false

    override fun process(signal: Signal): List<Signal> {

        val output = ArrayList<Signal>()
        if (!signal.high) {
            on = !on
            output.addAll(targets.map { Signal(on, name, it) })
        }

        return output
    }

    override fun isInitialState(): Boolean {
        return !on
    }
}