package dev.shaundsmith.adventofcode2023.day19

import dev.shaundsmith.adventofcode2023.core.PuzzleSolution
import dev.shaundsmith.adventofcode2023.day19.part2.CombinationFinder

class Day19 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        val workflows = HashMap<String, Workflow>()
        val parts = ArrayList<Part>()
        var parsingWorkflows = true
        for (line in input) {
            if (line.isBlank()) {
                parsingWorkflows = false
            } else if (parsingWorkflows) {
                val workflow = parseWorkflow(line)
                workflows[workflow.first] = workflow.second
            } else {
                parts.add(parsePart(line))
            }
        }

        val processor = PartProcessor(workflows)
        parts.forEach { processor.process(it) }

        return processor.accepted.sumOf { it.total() }.toString()
    }

    override fun part2(input: List<String>): String {

        val workflows = HashMap<String, Workflow>()
        for (line in input) {
            if (line.isBlank()) {
                break
            }
            val workflow = parseWorkflow(line)
            workflows[workflow.first] = workflow.second
        }
        val finder = CombinationFinder(workflows)
        val accepted = finder.find()
        val total = accepted.sumOf { it.total }

        return total.toString()
    }

    private val workflowPattern = Regex("(.+)\\{(.*)\\}")
    private fun parseWorkflow(line: String): Pair<String, Workflow> {

        val (name, conditions) = workflowPattern.find(line)!!.destructured
        val conditionParts = conditions.split(",")

        val conditionsList = ArrayList<Condition>()
        for (i in 0..<(conditionParts.size - 1)) {
            conditionsList.add(Condition(conditionParts[i]))
        }

        val default = when (conditionParts.last()) {
            "A" -> AcceptedResult()
            "R" -> RejectedResult()
            else -> WorkflowResult(conditionParts.last())
        }

        return Pair(name, Workflow(conditionsList, default))
    }

    private val partPattern = Regex("\\{x=(\\d+),m=(\\d+),a=(\\d+),s=(\\d+)}")
    private fun parsePart(line: String): Part {

        val (x, m, a, s) = partPattern.find(line)!!.destructured

        return Part(x.toInt(), m.toInt(), a.toInt(), s.toInt())
    }

}