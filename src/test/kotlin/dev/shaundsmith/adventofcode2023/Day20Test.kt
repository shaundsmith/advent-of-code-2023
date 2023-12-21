package dev.shaundsmith.adventofcode2023

import dev.shaundsmith.adventofcode2023.core.FileLoader
import dev.shaundsmith.adventofcode2023.day20.Day20
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day20Test {

    private val puzzleSolution = Day20()
    private val fileLoader = FileLoader()

    @Test fun day20Part1() {

        val contents = fileLoader.loadFile("day20/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("11687500", result)
    }

    @Test fun day20Part2() {

        val contents = fileLoader.loadFile("day20/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("", result)
    }

}