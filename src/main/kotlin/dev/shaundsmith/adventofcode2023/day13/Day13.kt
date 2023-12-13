package dev.shaundsmith.adventofcode2023.day13

import dev.shaundsmith.adventofcode2023.core.PuzzleSolution
import io.github.oshai.kotlinlogging.KotlinLogging

class Day13 : PuzzleSolution {
    val logger = KotlinLogging.logger {}

    override fun part1(input: List<String>): String {

        val value = extractShapes(input)
            .map { PatternShape(it) }
            .map { it.findReflection() }
            .sumOf { if (it?.first == Reflection.VERTICAL) it.second else (it?.second!! * 100) }

        return value.toString()
    }

    override fun part2(input: List<String>): String {

        val value = extractShapes(input)
            .mapNotNull { findSmudgesFor(it) }
            .sumOf { if (it?.first == Reflection.VERTICAL) it.second else (it?.second!! * 100) }

        return value.toString()
    }

    private fun extractShapes(input: List<String>): List<List<String>> {

        val shapes = ArrayList<List<String>>()
        val currentLines = ArrayList<String>()
        for (line in input) {
            if (line.isBlank()) {
                shapes.add(currentLines.toList())
                currentLines.clear()
            } else {
                currentLines.add(line)
            }
        }
        shapes.add(currentLines.toList())

        return shapes
    }

    private fun findSmudgesFor(input: List<String>) : Pair<Reflection, Int>? {

        val originalReflection = PatternShape(input).findReflection()

        for (j in 0..<input[0].length) {
            for (i in input.indices) {
                val copy = input.toMutableList()
                val currentChar = input[i][j]
                copy[i] = copy[i].substring(0, j) + (if (currentChar == '#') "." else "#") + copy[i].substring(j + 1)

                val newReflection = PatternShape(copy).findReflection(not = originalReflection!!)
                if (newReflection != null && newReflection != originalReflection) {
                    return newReflection
                }
            }
        }

        logger.warn { "No pattern found for shape:\n\t${input.joinToString("\n\t")}"}
        return null
    }

}