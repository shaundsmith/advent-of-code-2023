package dev.shaundsmith.adventofcode2023.day8

class Node(val name: String, private val left: String, private val right: String) {

    fun move(direction: Direction): String {

        return if (direction == Direction.LEFT) left else right
    }

    override fun toString(): String {

        return name
    }
}