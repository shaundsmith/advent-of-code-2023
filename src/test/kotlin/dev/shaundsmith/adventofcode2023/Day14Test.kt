package dev.shaundsmith.adventofcode2023

import dev.shaundsmith.adventofcode2023.core.FileLoader
import dev.shaundsmith.adventofcode2023.day14.Day14
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day14Test {

    private val puzzleSolution = Day14()
    private val fileLoader = FileLoader()

    @Test fun day14Part1() {

        val contents = fileLoader.loadFile("day14/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("136", result)
    }

    @Test fun day14Part2() {

        val contents = fileLoader.loadFile("day14/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("64", result)
    }

}