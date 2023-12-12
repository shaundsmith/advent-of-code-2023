package dev.shaundsmith.adventofcode2023

import dev.shaundsmith.adventofcode2023.core.FileLoader
import dev.shaundsmith.adventofcode2023.day12.Day12
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day12Test {

    private val puzzleSolution = Day12()
    private val fileLoader = FileLoader()

    @Test fun day12Part1() {

        val contents = fileLoader.loadFile("day12/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("21", result)
    }

    @Test fun day12Part2() {

        val contents = fileLoader.loadFile("day12/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("525152", result)
    }

}