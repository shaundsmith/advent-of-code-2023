package dev.shaundsmith.adventofcode2023.day1

import io.github.oshai.kotlinlogging.KotlinLogging


data class CalibrationLine(val line: String, val calibrationValues: Map<String, String>) {

    private val logger = KotlinLogging.logger {}

    fun calculationCalibrationValue(): Int {

        val value = Integer.parseInt(getFirstCalibrationValue() + getLastCalibrationValue())
        logger.debug { "Found digits $value" }
        return value
    }

    private fun getFirstCalibrationValue(): String {

        val firstCalibration = calibrationValues.entries
            .minBy { entry -> if (line.contains(entry.key)) line.indexOf(entry.key) else 99 }

        return firstCalibration.value
    }

    private fun getLastCalibrationValue(): String {

        val lastCalibration = calibrationValues.entries
            .maxBy { entry -> line.lastIndexOf(entry.key) }

        return lastCalibration.value
    }

}