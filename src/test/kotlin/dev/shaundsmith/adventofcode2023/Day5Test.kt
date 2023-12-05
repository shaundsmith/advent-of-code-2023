package dev.shaundsmith.adventofcode2023

import dev.shaundsmith.adventofcode2023.core.FileLoader
import dev.shaundsmith.adventofcode2023.day5.Day5
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day5Test {

    private val puzzleSolution = Day5()
    private val fileLoader = FileLoader()

    @Test fun day5Part1() {

        val contents = fileLoader.loadFile("day5/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("35", result)
    }

    @Test fun day5Part2() {

        val contents = fileLoader.loadFile("day5/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("46", result)
    }

}