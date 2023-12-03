package dev.shaunsmith.adventofcode2023

import dev.shaundsmith.adventofcode2023.core.FileLoader
import dev.shaundsmith.adventofcode2023.day1.Day1
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day1Test {

    private val puzzleSolution = Day1()
    private val fileLoader = FileLoader()

    @Test fun day1Part1() {

        val contents = fileLoader.loadFile("day1/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("142", result)
    }

    @Test fun day1Part2() {

        val contents = fileLoader.loadFile("day1/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("281", result)
    }

}