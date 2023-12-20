package dev.shaundsmith.adventofcode2023.day19

import dev.shaundsmith.adventofcode2023.day19.part2.PartCombination

class Workflow(private val conditions: List<Condition>,
    private val defaultResult: Result) {

    fun process(part: Part): Result {

        for (condition in conditions) {
            val output = condition.matches(part)
            if (output != null) {
                return output
            }
        }

        return defaultResult
    }

    fun process(partCombination: PartCombination): List<Pair<PartCombination, Result>> {

        val outcomes = ArrayList<Pair<PartCombination, Result>>()
        var nextPartCombination = partCombination
        for (condition in conditions) {
            outcomes.add(Pair(nextPartCombination.addRange(condition.property, condition.getMatchingRange()), condition.result))
            nextPartCombination = nextPartCombination.addRange(condition.property, condition.getNonMatchingRange())
        }
        outcomes.add(Pair(nextPartCombination, defaultResult))

        return outcomes
    }


}