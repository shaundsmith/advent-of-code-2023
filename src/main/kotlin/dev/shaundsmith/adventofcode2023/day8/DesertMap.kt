package dev.shaundsmith.adventofcode2023.day8

import io.github.oshai.kotlinlogging.KotlinLogging

class DesertMap(nodes: List<Node>, initialNodeName: String) {

    private val logger = KotlinLogging.logger {}

    private val nodesAsMap: Map<String, Node>
    private var currentNode: Node = nodes.find { it.name == initialNodeName }!!

    init {
        nodesAsMap = nodes.associateBy { it.name }
    }

    fun move(direction: Direction) {

        val nextNode = nodesAsMap[currentNode.move(direction)]!!
        logger.debug { "Moving $direction from $currentNode to $nextNode" }

        currentNode = nextNode
    }

    fun isEndNode(): Boolean {

        return currentNode.name == "ZZZ"
    }

    fun isGhostEndNode(): Boolean {

        return currentNode.name.endsWith("Z")
    }

}