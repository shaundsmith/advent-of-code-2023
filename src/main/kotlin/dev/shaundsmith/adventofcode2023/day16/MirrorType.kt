package dev.shaundsmith.adventofcode2023.day16

enum class MirrorType(private val character: Char) {

    VERTICAL_SPLITTER('|'),
    HORIZONTAL_SPLITTER('-'),
    RIGHT_MIRROR('\\'),
    LEFT_MIRROR('/'),
    AIR('.'),
    ENERGIZED('#');

    companion object {

        fun fromCharacter(character: Char): MirrorType {

            val mirrorType = MirrorType.entries.find { it.character == character }
            checkNotNull(mirrorType)

            return mirrorType
        }
    }

    override fun toString(): String {
        return character.toString()
    }
}