package dev.shaundsmith.adventofcode2023.day2

import io.github.oshai.kotlinlogging.KotlinLogging

class Game(line: String) {

    private val logger = KotlinLogging.logger {}

    val game : Int
    private val red = arrayListOf<Int>()
    private val blue = arrayListOf<Int>()
    private val green = arrayListOf<Int>()

    init {
        val parts = line.split(":", ";")

        game = Integer.parseInt(parts[0].replace("Game ", ""));

        val redRegex = Regex("(\\d+) red")
        val blueRegex = Regex("(\\d+) blue")
        val greenRegex = Regex("(\\d+) green")
        for (round in parts.subList(1, parts.size)) {
            red.add(parseCount(redRegex, round))
            blue.add(parseCount(blueRegex, round))
            green.add(parseCount(greenRegex, round))

        }
        logger.debug { "Added Game $game: Red $red, Blue $blue, Green $green" }
    }

    fun isPossible(red: Int, green: Int, blue: Int): Boolean {

        val exceeds = this.red.any { it > red } || this.green.any { it > green } || this.blue.any { it > blue }
        if (exceeds) logger.debug { "Game $game exceeds red = $red, green = $green, or blue = $blue" }

        return !exceeds
    }

    fun findMaxCount(): Map<String, Int> {

        val maxCounts = mapOf(
            "red" to red.max(),
            "green" to green.max(),
            "blue" to blue.max()
        )

        logger.debug { "Max counts: $maxCounts" }
        return maxCounts
    }

    private fun parseCount(regex: Regex, round: String): Int {

        val match = regex.find(round)

        return if (match == null) {
            0
        } else {
            val (countMatch) = match.destructured
            Integer.parseInt(countMatch)
        }
    }


}