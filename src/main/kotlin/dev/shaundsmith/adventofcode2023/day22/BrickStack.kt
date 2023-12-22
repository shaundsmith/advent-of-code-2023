package dev.shaundsmith.adventofcode2023.day22

import io.github.oshai.kotlinlogging.KotlinLogging

class BrickStack(bricks: List<Brick>) {
    private val logger = KotlinLogging.logger { }

    private val bricks: List<Brick> = bricks.sortedBy { it.z.first }

    init {
        drop()
    }

    private fun drop() {

        for (brick in bricks) {
            while (!brick.atRest()) {
                brick.drop()
            }
        }
        logger.debug { "---------------" }

        for (brick in bricks) {
            brick.supports.addAll(supports(brick))
            brick.supportedBy.addAll(supportedBy(brick))
        }
    }

    fun getBricksAbleToBeRemoved(): Int {

        return bricks.count { it.canRemove() }
    }

    fun howManyDropped(): Int {

        return bricks.sumOf { it.howManyDropped() }
    }

    fun supportedBy(brick: Brick): Set<Brick> {

        val supports = bricks.filter {
            brick.z.first == it.z.last + 1 &&
                    (it.x.intersect(brick.x).isNotEmpty() && it.y.intersect(brick.y).isNotEmpty())
        }

        logger.debug { "Brick $this is supported by ${supports.size} bricks."}
        return supports.toSet()
    }

    fun supports(brick: Brick): Set<Brick> {

        val supports = bricks.filter {
            brick.z.last + 1 == it.z.first &&
                    (it.x.intersect(brick.x).isNotEmpty() && it.y.intersect(brick.y).isNotEmpty())
        }

        logger.debug { "Brick $this supports ${supports.size} bricks."}
        return supports.toSet()
    }

    fun Brick.atRest(): Boolean {

        return this.z.first == 1 || hasBrickUnderneath(this)
    }

    fun hasBrickUnderneath(brick: Brick): Boolean {

        return bricks.any { brick.z.first - 1 == it.z.last &&
                (it.x.intersect(brick.x).isNotEmpty() && it.y.intersect(brick.y).isNotEmpty()) }
    }

}