package dev.shaunsmith.adventofcode2023

import dev.shaundsmith.adventofcode2023.core.FileLoader
import dev.shaundsmith.adventofcode2023.day2.Day2
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day2Test {

    private val puzzleSolution = Day2()
    private val fileLoader = FileLoader()

    @Test fun day2Part1() {

        val contents = fileLoader.loadFile("day2/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("8", result)
    }

    @Test fun day2Part2() {

        val contents = fileLoader.loadFile("day2/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("2286", result)
    }

}