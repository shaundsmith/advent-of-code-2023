package dev.shaundsmith.adventofcode2023.day12

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlin.math.pow

class SpringRecord(val rawLine: String, val counts: List<Int>) {

    val logger = KotlinLogging.logger {}

    fun findValidCombinations(): List<String> {

        val totalCount = counts.sum()
        val unknowns = rawLine.count { it == '?' }
        val existingSprings = rawLine.count { it == '#' }
        val combinationCount = 2.0.pow(unknowns.toDouble()).toInt()

        logger.debug { "Combination count: $combinationCount" }
        val matches = IntRange(0, combinationCount)
            .map { Integer.toBinaryString(it).padStart(unknowns, '0')  }
            .filter { it.count { c -> c == '1' } + existingSprings == totalCount }
            .map { replace(it) }
            .associateWith { matches(it) }

        logger.debug { "Found: \n\t${matches.keys.joinToString { "\n\t$it <- ${matches[it]}" }}" }
            
        return matches.keys
            .filter { matches[it] == true }
    }

    private fun replace(sequence: String): String {

        var processedLine = rawLine
        for (value in sequence) {
            processedLine = processedLine.replaceFirst("?", if (value == '1') "#" else ".")
        }

        return processedLine
    }

    private fun matches(processedLine: String): Boolean {

        var regexString = ""
        for (index in counts.indices) {
            if (index != 0) {
                regexString += "\\.+"
            }
            regexString += "\\#{${counts[index]}}"
        }

        return processedLine.matches(Regex("\\.*${regexString}\\.*"))
    }

}