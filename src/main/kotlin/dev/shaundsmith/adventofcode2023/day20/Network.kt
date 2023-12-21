package dev.shaundsmith.adventofcode2023.day20

import io.github.oshai.kotlinlogging.KotlinLogging
import java.util.LinkedList

class Network(private val pulsers: Map<String, Pulser>) {
    private val logger = KotlinLogging.logger {  }

    val signalsToProcess = LinkedList<Signal>()
    val signals = ArrayList<Signal>()

    fun pushButton(times: Int) {

        repeat(times) {
            pushButton()
            logger.debug { "-----------------------"}
            if (pulsers.all { it.value.isInitialState() }) {
                logger.debug { "Returned to initial state after ${it+1} iterations" }
            }
        }
    }


    fun pushButton() {

        signalsToProcess.add(Signal(false, "button", "broadcast"))
        signals.add(signalsToProcess[0])

        while (signalsToProcess.isNotEmpty()) {
            val signal = signalsToProcess.pop()
            logger.debug { signal }
            val pulser = pulsers[signal.target]
            if (pulser != null) {
                val output = pulser.process(signal)

                signalsToProcess.addAll(output)
                signals.addAll(output)
            }
        }
    }

    // A bit of manual intervention to find out BQ interval to compute LCM
    // &BQ -> RX
    fun pushButtonUntilHighOutputForAllSourcesOfBq(): Collection<Long> {

        val rxTargets = pulsers.values.filter { it.targets.contains("bq") }
            .map { it.name }

        val frequencies = rxTargets.associateWith { 0L } as HashMap
        var count = 0L
        while (frequencies.values.any { it == 0L }) {
            pushButton()
            count++
            frequencies.filter { it.value == 0L }
                .filter { signals.any { s -> s.high && s.source == it.key } }
                .forEach { (t, _) -> frequencies[t] = count }
        }

        logger.info { "Found values $frequencies" }
        return frequencies.values
    }


}