package dev.shaundsmith.adventofcode2023.day12

import io.github.oshai.kotlinlogging.KotlinLogging

// V2 - Brute force, recursive
class BruteForceSpringRecord(val rawLine: String, val counts: List<Int>) {
    private val logger = KotlinLogging.logger {}

    private val regex: Regex

    init {
        var regexString = ""
        for (index in counts.indices) {
            if (index != 0) {
                regexString += "[.?]+"
            }
            regexString += "[#?]{${counts[index]}}"
        }

        regex = Regex("^[.?]*${regexString}[.?]*$")
    }

    fun findValidCombinations(): Long {

        val matches = findValidCombinations(rawLine)
        logger.info { "Found $matches for $rawLine" }
        return matches
    }

    private fun findValidCombinations(currentString: String): Long {

        if (!partiallyMatches(currentString)) {
            return 0L
        }
        val nextQuestionMark = currentString.indexOf("?")
        if (nextQuestionMark == -1) {
            logger.debug { "Found $currentString" }
            return 1L
        }

        return findValidCombinations(currentString.replaceFirst("?", "#")) +
                findValidCombinations(currentString.replaceFirst("?", "."))
    }

    private fun partiallyMatches(processedLine: String): Boolean {

        return processedLine.matches(regex)
    }

}