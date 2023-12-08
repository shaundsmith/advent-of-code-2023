package dev.shaundsmith.adventofcode2023

import dev.shaundsmith.adventofcode2023.core.FileLoader
import dev.shaundsmith.adventofcode2023.day8.Day8
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day8Test {

    private val puzzleSolution = Day8()
    private val fileLoader = FileLoader()

    @Test fun day8Part1() {

        val contents = fileLoader.loadFile("day8/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("6", result)
    }

    @Test fun day8Part2() {

        val contents = fileLoader.loadFile("day8/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("6", result)
    }

}