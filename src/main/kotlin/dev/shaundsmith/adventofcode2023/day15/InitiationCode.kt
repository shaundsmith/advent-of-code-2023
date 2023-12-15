package dev.shaundsmith.adventofcode2023.day15

import io.github.oshai.kotlinlogging.KotlinLogging

class InitiationCode(private val code: String) {
    val logger = KotlinLogging.logger {}

    fun hash(): Int {

        var currentValue = 0
        for (character in code) {
            currentValue = ((currentValue + character.code) * 17) % 256
        }

        logger.debug { "Initiation code for $code is $currentValue" }
        return currentValue
    }

}