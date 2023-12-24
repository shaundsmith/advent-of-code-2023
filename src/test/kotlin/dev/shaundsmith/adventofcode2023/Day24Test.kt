package dev.shaundsmith.adventofcode2023

import dev.shaundsmith.adventofcode2023.core.FileLoader
import dev.shaundsmith.adventofcode2023.day24.Day24
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day24Test {

    private val puzzleSolution = Day24(testArea2D = LongRange(7, 27))
    private val fileLoader = FileLoader()

    @Test fun day24Part1() {

        val contents = fileLoader.loadFile("day24/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("2", result)
    }

    @Test fun day24Part2() {

        val contents = fileLoader.loadFile("day24/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("", result)
    }

}