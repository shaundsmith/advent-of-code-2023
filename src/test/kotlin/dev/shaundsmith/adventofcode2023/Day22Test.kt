package dev.shaundsmith.adventofcode2023

import dev.shaundsmith.adventofcode2023.core.FileLoader
import dev.shaundsmith.adventofcode2023.day22.Day22
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day22Test {

    private val puzzleSolution = Day22()
    private val fileLoader = FileLoader()

    @Test fun day22Part1() {

        val contents = fileLoader.loadFile("day22/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("5", result)
    }

    @Test fun day22Part2() {

        val contents = fileLoader.loadFile("day22/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("7", result)
    }

}