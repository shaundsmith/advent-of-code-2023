package dev.shaundsmith.adventofcode2023.day22

import io.github.oshai.kotlinlogging.KotlinLogging

class Brick(val label: String,
            val x: IntRange,
            val y: IntRange,
            var z: IntRange) {
    private val logger = KotlinLogging.logger { }

    val supports = HashSet<Brick>()
    val supportedBy = HashSet<Brick>()

    fun drop() {
        z = IntRange(z.first - 1, z.last - 1)
    }

    fun canRemove(): Boolean {

        var canRemove = true
        if (supports.isNotEmpty()) {
            val onlySupportFor = supports.filter { it.supportedBy.size == 1 }
            if (onlySupportFor.isNotEmpty()) {
                logger.debug { "Brick $label is sole support for ${onlySupportFor.map {it.label }}" }
                canRemove = false
            }
        } else {
            logger.debug { "Brick $label is not supporting any bricks" }
        }

        return canRemove
    }

    fun howManyDropped(): Int {

        if (canRemove()) {
            return 0
        }

        val toRemove = supports.filter { it.supportedBy.size == 1 }.toSet()

        val simulation = FallingSimulation()
        simulation.removed.addAll(toRemove)
        this.supports.forEach() { it.howManyDropped(simulation) }
        val count = simulation.removed.count()
        logger.info { "Removing Brick $label will drop $count bricks" }

        return count
    }

    private fun howManyDropped(simulation: FallingSimulation) {

        val newRemoved = supports.filter { it.supportedBy.allSupportsRemoved(simulation) }.toSet()
        simulation.removed.addAll(newRemoved)

        newRemoved.forEach() { it.howManyDropped(simulation) }
    }

    override fun toString(): String {

        return "($label) - ${x.first},${y.first},${z.first}~${x.last},${y.last},${z.last}"
    }

    private fun Set<Brick>.allSupportsRemoved(simulation: FallingSimulation): Boolean {

        val check = this.toMutableSet()
        check.removeAll(simulation.removed)

        return check.isEmpty()
    }
}