package dev.shaundsmith.adventofcode2023.day19

interface Result {
}

class AcceptedResult: Result {

}

class RejectedResult: Result {

}

class WorkflowResult(val label: String): Result {

}