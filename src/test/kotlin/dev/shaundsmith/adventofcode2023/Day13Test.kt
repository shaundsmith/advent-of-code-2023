package dev.shaundsmith.adventofcode2023

import dev.shaundsmith.adventofcode2023.core.FileLoader
import dev.shaundsmith.adventofcode2023.day13.Day13
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day13Test {

    private val puzzleSolution = Day13()
    private val fileLoader = FileLoader()

    @Test fun day13Part1() {

        val contents = fileLoader.loadFile("day13/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("405", result)
    }

    @Test fun day13Part2() {

        val contents = fileLoader.loadFile("day13/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("400", result)
    }

}