package dev.shaundsmith.adventofcode2023.day20

import dev.shaundsmith.adventofcode2023.core.LowestCommonMultiple
import dev.shaundsmith.adventofcode2023.core.PuzzleSolution

class Day20 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        val pulsers = input.map { parsePulser(it) }.associate { it.first to it.second }
        pulsers.values.forEach {
            it.targets
                .filter { target -> pulsers.contains(target) }
                .forEach { target -> pulsers[target]!!.registerSource(it.name) }
        }
        val network = Network(pulsers)

        network.pushButton(1000)

        return (network.signals.count { it.high }.toLong() * network.signals.count { !it.high }).toString()
    }

    override fun part2(input: List<String>): String {

        val pulsers = input.map { parsePulser(it) }.associate { it.first to it.second }
        pulsers.values.forEach {
            it.targets
                .filter { target -> pulsers.contains(target) }
                .forEach { target -> pulsers[target]!!.registerSource(it.name) }
        }
        val network = Network(pulsers)

        val count = network.pushButtonUntilHighOutputForAllSourcesOfBq()

        return LowestCommonMultiple.lcm(count.toList()).toString()
    }

    private fun parsePulser(line: String): Pair<String, Pulser> {

        val split = line.split(" -> ")

        val pulser: Pulser
        when {
            split[0] == "broadcaster" -> pulser = BroadcastModule(split[1].split(", "))
            split[0].startsWith("%") -> pulser = FlipFlopPulser(split[0].substring(1), split[1].split(", "))
            split[0].startsWith("&") -> pulser = ConjunctionPulser(split[0].substring(1), split[1].split(", "))
            else -> throw IllegalArgumentException("Unknown type $line")
        }

        return Pair(pulser.name, pulser)
    }

}