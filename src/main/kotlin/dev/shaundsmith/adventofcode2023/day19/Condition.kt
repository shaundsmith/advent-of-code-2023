package dev.shaundsmith.adventofcode2023.day19

class Condition(conditionString: String) {

    val sign: Char
    val number: Int
    val property: Char
    private val predicate: (Part) -> Boolean
    val result: Result

    init {
        val splitString = conditionString.split(":")
        val resultString = splitString[1]

        result = when (resultString) {
            "A" -> AcceptedResult()
            "R" -> RejectedResult()
            else -> WorkflowResult(resultString)
        }

        val predicateString = splitString[0]
        property = predicateString[0]
        val propertyAccessor = when(property) {
            'x' -> Part::x
            'm' -> Part::m
            'a' -> Part::a
            's' -> Part::s
            else -> throw IllegalArgumentException("Unknown condition ${predicateString[0]}")
        }

        number = predicateString.substring(2).toInt()
        sign = predicateString[1]
        predicate = if (sign == '<') {
            { part -> propertyAccessor.get(part) < number }
        } else {
            { part -> propertyAccessor.get(part) > number }
        }
    }


    fun matches(part: Part): Result? {
        return if (predicate(part)) {
            result
        } else {
            null
        }
    }

    fun getMatchingRange(): IntRange {

        return if (sign == '<') {
            IntRange(1, number - 1)
        } else {
            IntRange(number + 1, 4000)
        }
    }

    fun getNonMatchingRange(): IntRange {

        return if (sign == '>') {
            IntRange(1, number)
        } else {
            IntRange(number, 4000)
        }
    }
    
}