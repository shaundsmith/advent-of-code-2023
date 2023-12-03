package dev.shaunsmith.adventofcode2023

import dev.shaundsmith.adventofcode2023.core.FileLoader
import dev.shaundsmith.adventofcode2023.day3.Day3
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day3Test {

    private val puzzleSolution = Day3()
    private val fileLoader = FileLoader()

    @Test fun day3Part1() {

        val contents = fileLoader.loadFile("day3/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("4361", result)
    }

    @Disabled
    @Test fun day3Part2() {

        val contents = fileLoader.loadFile("day3/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("467835", result)
    }

}