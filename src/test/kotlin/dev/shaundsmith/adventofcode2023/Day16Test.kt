package dev.shaundsmith.adventofcode2023

import dev.shaundsmith.adventofcode2023.core.FileLoader
import dev.shaundsmith.adventofcode2023.day16.Day16
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day16Test {

    private val puzzleSolution = Day16()
    private val fileLoader = FileLoader()

    @Test fun day16Part1() {

        val contents = fileLoader.loadFile("day16/test-input-1.txt")

        val result = puzzleSolution.part1(contents)

        assertEquals("46", result)
    }

    @Test fun day16Part2() {

        val contents = fileLoader.loadFile("day16/test-input-2.txt")

        val result = puzzleSolution.part2(contents)

        assertEquals("51", result)
    }

}