package dev.shaundsmith.adventofcode2023.day5

import dev.shaundsmith.adventofcode2023.core.PuzzleSolution

class Day5 : PuzzleSolution {

    override fun part1(input: List<String>): String {

        val seeds = parseSeeds(input[0])
        val almanac = buildAlmanac(input.subList(2, input.size).joinToString("\n").split("\n\n"))

        val lowestSeedLocation = seeds.minOfOrNull { almanac.getSeedLocation(it) }

        return lowestSeedLocation.toString()
    }

    override fun part2(input: List<String>): String {

        val seeds = parseSeedsAsRanges(input[0])
        val almanac = buildAlmanac(input.subList(2, input.size).joinToString("\n").split("\n\n"))

        val lowestSeedLocation = seeds.minOfOrNull { seedRange -> seedRange.minOf { almanac.getSeedLocation(it)} }

        return lowestSeedLocation.toString()
    }

    private fun parseSeeds(input: String): List<Long> {

        val numbers = input.replace("seeds: ", "").split(" ")

        return numbers.map { it.toLong() }
    }

    private fun parseSeedsAsRanges(input: String): List<LongRange> {

        val numbers = input.replace("seeds: ", "").split(" ")

        val seeds = ArrayList<LongRange>()
        for (i in 0..<numbers.size/2) {
            val start = numbers[i * 2].toLong()
            val end = start + numbers[(i * 2) + 1].toLong() - 1
            seeds.add(LongRange(start, end))
        }

        return seeds
    }

    private fun buildAlmanac(input: List<String>): Almanac {

        val maps = HashMap<String, AlmanacMap>()

        for (block in input) {
            val lines = block.split("\n")
            val title = lines[0].replace(" map:", "")
            val almanacLines = lines.subList(1, lines.size)
                .map { it.split(" ") }
                .map { AlmanacLine(
                    it[0].toLong(),
                    it[1].toLong(),
                    it[2].toLong()
                )}

            maps[title] = AlmanacMap(almanacLines)
        }

        return Almanac(
            maps["seed-to-soil"]!!,
            maps["soil-to-fertilizer"]!!,
            maps["fertilizer-to-water"]!!,
            maps["water-to-light"]!!,
            maps["light-to-temperature"]!!,
            maps["temperature-to-humidity"]!!,
            maps["humidity-to-location"]!!
        )
    }
}