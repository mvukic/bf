package com.github.mvukic

import java.nio.file.Paths
import kotlin.io.path.absolute

fun getMemoryPath(args: Array<String>) = args.getOrNull(1)

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

class Memory(private val memory: ByteArray) {

    fun get(pointer: Int) = memory.getOrNull(pointer) ?: error("Memory at pointer '$pointer' is null")

    fun set(pointer: Int, value: Byte) {
        memory[pointer] = value
    }

    override fun toString() = memory.joinToString("")

}