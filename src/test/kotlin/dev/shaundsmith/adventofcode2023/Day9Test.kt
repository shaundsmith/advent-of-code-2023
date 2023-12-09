package dev.shaundsmith.adventofcode2023

import dev.shaundsmith.adventofcode2023.core.FileLoader
import dev.shaundsmith.adventofcode2023.day9.Day9
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day9Test {

    private val puzzleSolution = Day9()
    private val fileLoader = FileLoader()

    @Test fun day9Part1() {

        val contents = fileLoader.loadFile("day9/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("114", result)
    }

    @Test fun day9Part2() {

        val contents = fileLoader.loadFile("day9/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("2", result)
    }

}