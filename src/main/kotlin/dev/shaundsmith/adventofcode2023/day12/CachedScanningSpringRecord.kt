package dev.shaundsmith.adventofcode2023.day12

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlin.math.min

// V3.5 - Character scanning, with cache
class CachedScanningSpringRecord(val rawLine: String, val counts: List<Int>) {
    private val logger = KotlinLogging.logger {}
    private val cache = HashMap<Pair<String, List<Int>>, Long>()

    fun findValidCombinations(): Long {

        val matches = findValidCombinations(rawLine, counts)
        logger.info { "Found $matches for $rawLine" }
        return matches
    }

    private fun findValidCombinations(remainingString: String, remainingCounts: List<Int>): Long {

        val args = Pair(remainingString, remainingCounts)
        if (cache.contains(args)) {
            return cache[args]!!
        }

        val processedRemainingString = remainingString.replace("^\\.*", "")
        when {
            remainingCounts.isEmpty() && processedRemainingString.contains("#") -> return 0L
            remainingCounts.isEmpty() -> return 1L
            processedRemainingString.isEmpty() -> return 0
        }

        var combinations = 0L
        var firstCharacter = processedRemainingString.first()
        if (firstCharacter == '#' || firstCharacter == '?') {
            val nextCount = remainingCounts.first()
            val targetPattern = processedRemainingString.substring(0, min(nextCount, processedRemainingString.length)).replace("?", "#")

            if (targetPattern == "#".repeat(nextCount)) {
                if (processedRemainingString.length == targetPattern.length && remainingCounts.size == 1) {
                    combinations += 1
                } else if (processedRemainingString.length != targetPattern.length && processedRemainingString[nextCount] != '#') {
                    combinations += findValidCombinations(
                        processedRemainingString.substring(nextCount + 1),
                        remainingCounts.subList(1, remainingCounts.size)
                    )
                }
            }
        }

        if (firstCharacter == '.' || firstCharacter == '?') {
            combinations += findValidCombinations(
                processedRemainingString.substring(1),
                remainingCounts
            )
        }

        cache[args] = combinations
        return combinations
    }

}