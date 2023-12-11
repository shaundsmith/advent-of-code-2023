package dev.shaundsmith.adventofcode2023

import dev.shaundsmith.adventofcode2023.core.FileLoader
import dev.shaundsmith.adventofcode2023.day11.Day11
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day11Test {

    private val puzzleSolution = Day11(100)
    private val fileLoader = FileLoader()

    @Test fun day11Part1() {

        val contents = fileLoader.loadFile("day11/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("374", result)
    }

    @Test fun day11Part2() {

        val contents = fileLoader.loadFile("day11/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("8410", result)
    }

}