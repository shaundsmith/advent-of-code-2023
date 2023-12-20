package dev.shaundsmith.adventofcode2023.day19.part2

import kotlin.math.max
import kotlin.math.min

class PartCombination(var x: IntRange,
    var m: IntRange,
    var a: IntRange,
    var s: IntRange) {

    val total: Long get() = x.count().toLong() * m.count() * a.count() * s.count()


    fun addRange(char: Char, newRange: IntRange): PartCombination {

        return PartCombination(
            if (char == 'x') combineRanges(x, newRange) else copyRange(x),
            if (char == 'm') combineRanges(m, newRange) else copyRange(m),
            if (char == 'a') combineRanges(a, newRange) else copyRange(a),
            if (char == 's') combineRanges(s, newRange) else copyRange(s)
        )
    }

    private fun combineRanges(p1: IntRange, p2: IntRange): IntRange {

        return IntRange(max(p1.first, p2.first), min(p1.last, p2.last))
    }

    private fun copyRange(p1: IntRange): IntRange {

        return IntRange(p1.first, p1.last)
    }

}