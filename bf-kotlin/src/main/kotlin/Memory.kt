package com.github.mvukic

import java.nio.file.Paths
import kotlin.io.path.absolute

fun getMemoryPath(args: Array<String>) = args.firstOrNull()

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
    /// TODO: Read content of the array from the file
    return ByteArray(memoryContent.length)
}

data class Memory(
    private val memory: ByteArray
)