package com.github.mvukic

import com.github.mvukic.program.Program
import java.nio.file.Paths
import kotlin.io.path.absolute

fun getBracketPairs(program: Program): Map<Int, Int> {

    val stack = mutableListOf<Int>()
    val pairs = mutableMapOf<Int, Int>()

    for (index in 0 until program.length) {
        when (program.getInstruction(index)) {
            '[' -> stack.add(index)
            ']' -> {
                if (stack.isEmpty()) error("Unmatched ']' at index $index")
                val openingIndex = stack.removeAt(stack.lastIndex)
                pairs[openingIndex] = index
                pairs[index] = openingIndex
            }
        }
    }

    return pairs
}

fun readFileContent(path: String): String {
    val currentPath = Paths.get(".").absolute()
    val memoryPath = currentPath.resolve(path).normalize()

    val memoryFile = memoryPath.toFile()
    if (!memoryFile.isFile) {
        error("File not found at path '$memoryPath'")
    }
    return memoryFile.readLines().joinToString("").trim()
}


fun getBytesFromString(value: String): ByteArray {
    val values = value.split(",")
    val numbers = values.map { it.toIntOrNull() ?: error("'$it' is not a number") }
    if (numbers.any { it < -128 || it > 127 }) {
        error("Numbers must be between -128 and 127")
    }
    val bytes = numbers.map { it.toByte() }
    return bytes.toByteArray()
}