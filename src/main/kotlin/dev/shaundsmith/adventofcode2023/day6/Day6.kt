package dev.shaundsmith.adventofcode2023.day6

import dev.shaundsmith.adventofcode2023.core.PuzzleSolution

class Day6 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        val times = parseNumbersAsList(input[0])
        val records = parseNumbersAsList(input[1])

        check(times.size == records.size)
        val races = ArrayList<Race>()
        for (index in times.indices) {
            races.add(Race(times[index], records[index]))
        }

        val output = races.map { it.getTimesAboveRecord().count() }
            .reduce { acc, i -> acc * i }

        return output.toString()
    }

    override fun part2(input: List<String>): String {

        val race = Race(parseNumbersAsSingleNumber(input[0]),
            parseNumbersAsSingleNumber(input[1]))

        val timesAboveRecord = race.getTimesAboveRecord()

        return timesAboveRecord.count().toString()
    }

    private fun parseNumbersAsList(line: String): List<Long> {

        val colonIndex = line.indexOf(":")
        val numberPart = line.substring(colonIndex + 1)

        return numberPart.split(Regex("\\s+"))
            .filter { it.isNotBlank() }
            .map { it.trim().toLong() }
    }

    private fun parseNumbersAsSingleNumber(line: String): Long {

        val colonIndex = line.indexOf(":")
        val numberPart = line.substring(colonIndex + 1)

        return numberPart.replace(" ", "").toLong()
    }

}