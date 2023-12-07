package dev.shaundsmith.adventofcode2023

import dev.shaundsmith.adventofcode2023.core.FileLoader
import dev.shaundsmith.adventofcode2023.day7.Day7
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day7Test {

    private val puzzleSolution = Day7()
    private val fileLoader = FileLoader()

    @Test fun day7Part1() {

        val contents = fileLoader.loadFile("day7/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("6440", result)
    }

    @Test fun day7Part2() {

        val contents = fileLoader.loadFile("day7/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("5905", result)
    }

}