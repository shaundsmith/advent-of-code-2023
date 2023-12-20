package dev.shaundsmith.adventofcode2023

import dev.shaundsmith.adventofcode2023.core.FileLoader
import dev.shaundsmith.adventofcode2023.day18.Day18
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day18Test {

    private val puzzleSolution = Day18()
    private val fileLoader = FileLoader()

    @Test fun day18Part1() {

        val contents = fileLoader.loadFile("day18/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("62", result)
    }

    @Test fun day18Part2() {

        val contents = fileLoader.loadFile("day18/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("952408144115", result)
    }

}