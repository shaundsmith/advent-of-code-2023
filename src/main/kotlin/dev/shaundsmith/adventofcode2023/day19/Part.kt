package dev.shaundsmith.adventofcode2023.day19

data class Part(
    val x: Int,
    val m: Int,
    val a: Int,
    val s: Int) {

    fun total(): Int {
        return x + m + a + s
    }


}