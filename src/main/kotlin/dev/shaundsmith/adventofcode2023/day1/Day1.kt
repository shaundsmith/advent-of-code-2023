package dev.shaundsmith.adventofcode2023.day1

import dev.shaundsmith.adventofcode2023.core.PuzzleSolution
import io.github.oshai.kotlinlogging.KotlinLogging

class Day1 : PuzzleSolution  {

    private val logger = KotlinLogging.logger {}

    override fun part1(input: List<String>): String {

        val sum = input.sumOf { line -> getDigits(line, true) }

        return sum.toString()
    }

    override fun part2(input: List<String>): String {

        val sum = input.sumOf { line -> getDigits(line, false) }

        return sum.toString()
    }

    private fun getDigits(line: String, ignoreWords: Boolean): Int {

        val allDigits = arrayListOf<String>()
        for ((index, character) in line.withIndex()) {
            if (character.isDigit()) {
                allDigits.add(character.toString())
            } else if (!ignoreWords && character.isLetter()) {
                val interpretedNumber = interpretNumberFromString(line.substring(index))
                if (interpretedNumber?.isNotBlank() == true) {
                    allDigits.add(interpretedNumber)
                }
            }
        }

        val digitsAsString = allDigits.first() + allDigits.last()

        logger.debug { "Found digits $digitsAsString" }
        return digitsAsString.toInt()
    }

    private fun interpretNumberFromString(line: String): String? {

        val numberMap = mapOf(
            "one" to "1",
            "two" to "2",
            "three" to "3",
            "four" to "4",
            "five" to "5",
            "six" to "6",
            "seven" to "7",
            "eight" to "8",
            "nine" to "9",
        )

        return numberMap.entries
            .filter { entry -> line.startsWith(entry.key) }
            .map { entry -> entry.value }
            .firstOrNull()
    }

}