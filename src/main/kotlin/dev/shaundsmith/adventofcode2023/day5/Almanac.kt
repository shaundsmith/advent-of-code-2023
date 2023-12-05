package dev.shaundsmith.adventofcode2023.day5

import io.github.oshai.kotlinlogging.KotlinLogging

class Almanac(
    private val seedToSoil: AlmanacMap,
    private val soilToFertilizer: AlmanacMap,
    private val fertilizerToWater: AlmanacMap,
    private val waterToLight: AlmanacMap,
    private val lightToTemperature: AlmanacMap,
    private val temperatureToHumidity: AlmanacMap,
    private val humidityToLocation: AlmanacMap
) {

    private val logger = KotlinLogging.logger {}

    fun getSeedLocation(seed: Long): Long {

        val soil = seedToSoil.getDestination(seed)
        val fertilizer = soilToFertilizer.getDestination(soil)
        val water = fertilizerToWater.getDestination(fertilizer)
        val light = waterToLight.getDestination(water)
        val temperature = lightToTemperature.getDestination(light)
        val humidity = temperatureToHumidity.getDestination(temperature)
        val location = humidityToLocation.getDestination(humidity)

        logger.debug { "Seed $seed, soil $soil, fertilizer $fertilizer, water $water, light $light, temperature $temperature, humidity $humidity, location $location" }

        return location
    }


}