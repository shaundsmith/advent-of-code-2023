package dev.shaundsmith.adventofcode2023

import dev.shaundsmith.adventofcode2023.core.FileLoader
import dev.shaundsmith.adventofcode2023.day19.Day19
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day19Test {

    private val puzzleSolution = Day19()
    private val fileLoader = FileLoader()

    @Test fun day19Part1() {

        val contents = fileLoader.loadFile("day19/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("19114", result)
    }

    @Test fun day19Part2() {

        val contents = fileLoader.loadFile("day19/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("167409079868000", result)
    }

}