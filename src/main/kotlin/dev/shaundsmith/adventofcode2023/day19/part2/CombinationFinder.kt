package dev.shaundsmith.adventofcode2023.day19.part2

import dev.shaundsmith.adventofcode2023.day19.AcceptedResult
import dev.shaundsmith.adventofcode2023.day19.Workflow
import dev.shaundsmith.adventofcode2023.day19.WorkflowResult
import java.util.*

class CombinationFinder(private val workflows: Map<String, Workflow>) {

    private val toSearch = LinkedList<Pair<PartCombination, String>>()
    private val accepted = ArrayList<PartCombination>()

    fun find(): List<PartCombination> {

        val combination = PartCombination(IntRange(1, 4000), IntRange(1, 4000), IntRange(1, 4000), IntRange(1, 4000))
        toSearch.add(Pair(combination, "in"))

        while (toSearch.isNotEmpty()) {
            val current = toSearch.pop()
            val combinations = workflows[current.second]!!.process(current.first)
            accepted.addAll(combinations
                .filter { it.second::class == AcceptedResult::class }
                .map { it.first })
            toSearch.addAll(combinations
                .filter { it.second::class == WorkflowResult::class }
                .map { Pair(it.first, (it.second as WorkflowResult).label) })
        }

        return accepted
    }


}