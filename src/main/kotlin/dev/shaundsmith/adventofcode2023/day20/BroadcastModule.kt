package dev.shaundsmith.adventofcode2023.day20

class BroadcastModule(override val targets: List<String>): Pulser {

    override val name = "broadcast"

    override fun process(signal: Signal): List<Signal> {

        return targets.map { Signal(signal.high, name, it) }
    }

    override fun isInitialState(): Boolean {
        return true
    }
}