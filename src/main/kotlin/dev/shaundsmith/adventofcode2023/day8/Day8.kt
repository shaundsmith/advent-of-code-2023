package dev.shaundsmith.adventofcode2023.day8

import dev.shaundsmith.adventofcode2023.core.LowestCommonMultiple
import dev.shaundsmith.adventofcode2023.core.PuzzleSolution
import io.github.oshai.kotlinlogging.KotlinLogging

class Day8 : PuzzleSolution {

    private val logger = KotlinLogging.logger {}

    override fun part1(input: List<String>): String {

        val directions: List<Direction> = parseDirections(input[0])
        val desertMap = DesertMap(input.subList(2, input.size).map { parseNode(it) }, "AAA")

        val steps = navigate(desertMap, directions)

        return steps.toString()
    }

    override fun part2(input: List<String>): String {

        val directions: List<Direction> = parseDirections(input[0])
        val nodes = input.subList(2, input.size).map { parseNode(it) }

        val startingNodes = nodes.filter { it.name.endsWith("A") }
        val desertMaps = startingNodes.map { DesertMap(nodes, it.name) }

        val steps = desertMaps.map { navigateAsGhost(it, directions) }
        val lcm = LowestCommonMultiple.lcm(steps)
        logger.debug { "Lowest common multiple of $steps is $lcm" }

        return lcm.toString()
    }

    fun parseDirections(directions: String): List<Direction> {

        return directions.trim().toCharArray()
            .map { Direction.fromSymbol(it.toString()) }
    }

    private val nodePattern = Regex("([A-Z0-9]{3}) = \\(([A-Z0-9]{3}), ([A-Z0-9]{3})\\)")
    fun parseNode(line: String): Node {

        val (name, left, right) = nodePattern.find(line)!!.destructured

        return Node(name, left, right)
    }

    fun navigate(desertMap: DesertMap, directions: List<Direction>): Long {

        var steps = 0L
        while (!desertMap.isEndNode()) {
            val direction = (steps % directions.size).toInt()
            desertMap.move(directions[direction])
            steps++
        }

        return steps
    }

    fun navigateAsGhost(desertMap: DesertMap, directions: List<Direction>): Long {

        var steps = 0L
        while (!desertMap.isGhostEndNode()) {
            val direction = (steps % directions.size).toInt()
            desertMap.move(directions[direction])
            steps++
        }

        return steps
    }

}