package dev.shaundsmith.adventofcode2023.day14

import dev.shaundsmith.adventofcode2023.core.PuzzleSolution
import dev.shaundsmith.adventofcode2023.core.toGrid

class Day14 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        val reflectorDish = ReflectorDish(input.toGrid({ c -> c}, '.'))
        reflectorDish.tilt()
        val rowValues = reflectorDish.getRowValues()

        return rowValues.toString()
    }

    override fun part2(input: List<String>): String {

        val reflectorDish = ReflectorDish(input.toGrid({ c -> c}, '.'))
        reflectorDish.spin(1000000000)
        val rowValues = reflectorDish.getRowValues()

        return rowValues.toString()
    }

}