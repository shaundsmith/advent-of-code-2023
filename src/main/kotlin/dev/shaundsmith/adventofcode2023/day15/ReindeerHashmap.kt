package dev.shaundsmith.adventofcode2023.day15

import io.github.oshai.kotlinlogging.KotlinLogging

class ReindeerHashmap {
    val logger = KotlinLogging.logger {}

    private val map = IntRange(0, 255).map { LinkedHashMap<String, Int>() }

    fun action(value: String) {

        if (value.contains("-")) {
            remove(value)
        } else {
            add(value)
        }
    }

    fun calculateScores(): Int {

        return map.indices
            .filter { map[it].isNotEmpty() }
            .sumOf { calculateBoxTotalScore(it) }
    }

    private fun remove(value: String) {

        val label = value.replace("-", "")
        val hash = InitiationCode(label).hash()
        map[hash].remove(label)

        logger.debug { "Box $hash is now ${map[hash]}" }
    }

    private fun add(value: String) {

        val splitCode = value.split("=")
        val label = splitCode[0]
        val hash = InitiationCode(label).hash()
        val focus = splitCode[1].toInt()

        map[hash][label] = focus

        logger.debug { "Box $hash is now ${map[hash]}" }
    }

    private fun calculateBoxTotalScore(box: Int): Int {

        val boxScore = box + 1
        val boxMap = map[box]

        val score = boxMap.keys.mapIndexed { index, key -> boxScore * (index + 1) * boxMap[key]!! }
            .sum()
        logger.debug { "Box $box has a score of $score for $boxMap" }
        return score
    }

}