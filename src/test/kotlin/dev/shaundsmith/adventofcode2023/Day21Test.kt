package dev.shaundsmith.adventofcode2023

import dev.shaundsmith.adventofcode2023.core.FileLoader
import dev.shaundsmith.adventofcode2023.day21.Day21
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day21Test {

    private val puzzleSolution = Day21(6)
    private val fileLoader = FileLoader()

    @Test fun day21Part1() {

        val contents = fileLoader.loadFile("day21/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("16", result)
    }

    @Test fun day21Part2() {

        val contents = fileLoader.loadFile("day21/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("", result)
    }

}