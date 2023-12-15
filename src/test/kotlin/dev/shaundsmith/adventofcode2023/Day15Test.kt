package dev.shaundsmith.adventofcode2023

import dev.shaundsmith.adventofcode2023.core.FileLoader
import dev.shaundsmith.adventofcode2023.day15.Day15
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day15Test {

    private val puzzleSolution = Day15()
    private val fileLoader = FileLoader()

    @Test fun day15Part1() {

        val contents = fileLoader.loadFile("day15/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("1320", result)
    }

    @Test fun day15Part2() {

        val contents = fileLoader.loadFile("day15/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("145", result)
    }

}