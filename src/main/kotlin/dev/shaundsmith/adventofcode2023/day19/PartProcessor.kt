package dev.shaundsmith.adventofcode2023.day19

import io.github.oshai.kotlinlogging.KotlinLogging

class PartProcessor(private val workflows: Map<String, Workflow>) {
    val logger = KotlinLogging.logger {  }

    val accepted = ArrayList<Part>()

    fun process(part: Part) {

        var workflow = workflows["in"]
        while (workflow != null) {
            val result = workflow.process(part)
            workflow = null
            if (result::class == AcceptedResult::class) {
                logger.debug { "Accepted $part" }
                accepted.add(part)
            } else if (result::class == WorkflowResult::class) {
                val nextWorkflowLabel = (result as WorkflowResult).label
                workflow = workflows[nextWorkflowLabel]
                logger.debug { "Moving $part to $nextWorkflowLabel" }
            }
        }
    }

}