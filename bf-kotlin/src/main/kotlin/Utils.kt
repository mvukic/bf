package com.github.mvukic

import java.nio.file.Paths
import kotlin.io.path.absolute

fun getBracketPairs(instructions: Instructions): Map<Int, Int> {

    val stack = mutableListOf<Int>()
    val pairs = mutableMapOf<Int, Int>()

    for (index in 0 until instructions.length) {
        when (instructions.get(index)) {
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


fun getMemoryPath(args: Array<String>) = args.getOrNull(1)

fun getProgramPath(args: Array<String>) = args.firstOrNull() ?: error("No program path specified")

fun readMemoryFromPathOrDefault(path: String?, default: ByteArray): ByteArray {
    if (path == null) {
        return default
    }
    val currentPath = Paths.get(".").absolute()
    val memoryPath = currentPath.resolve(path).normalize()

    val memoryFile = memoryPath.toFile()
    if (!memoryFile.isFile) {
        error("File not found at path '$memoryPath'")
    }
    val memoryContent = memoryFile.readLines().joinToString("").trim()
    if (memoryContent.isEmpty()) {
        error("Empty memory")
    }
    return memoryContent.split("").map { it.toByte() }.toByteArray()
}

fun readProgramFromPath(path: String): String {
    val currentPath = Paths.get(".").absolute()
    val programPath = currentPath.resolve(path).normalize()

    val programFile = programPath.toFile()
    if (!programFile.isFile) {
        error("File not found at path '$programPath'")
    }
    val programContent = programFile.readLines().joinToString("").trim()
    if (programContent.isEmpty()) {
        error("Empty program")
    }
    return programContent
}