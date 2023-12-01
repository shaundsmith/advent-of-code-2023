package dev.shaundsmith.adventofcode2023.core

class FileLoader {

    fun loadFile(fileName: String): List<String> = Thread.currentThread().contextClassLoader.getResourceAsStream(fileName)?.bufferedReader()?.readLines()!!

}