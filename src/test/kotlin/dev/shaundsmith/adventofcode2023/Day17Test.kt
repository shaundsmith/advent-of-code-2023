package dev.shaundsmith.adventofcode2023

import dev.shaundsmith.adventofcode2023.core.FileLoader
import dev.shaundsmith.adventofcode2023.day17.Day17
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day17Test {

    private val puzzleSolution = Day17()
    private val fileLoader = FileLoader()

    @Test fun day17Part1() {

        val contents = fileLoader.loadFile("day17/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("102", result)
    }

    @Test fun day17Part2() {

        val contents = fileLoader.loadFile("day17/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("94", result)
    }

}