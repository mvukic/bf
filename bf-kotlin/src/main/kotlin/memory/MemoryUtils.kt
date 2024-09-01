package com.github.mvukic.memory

import com.github.mvukic.readFileContent

fun readMemoryFromPath(path: String): ByteArray {
    val content = readFileContent(path)
    if (content.isEmpty()) {
        error("Empty memory")
    }
    return content.split("").map { it.toByte() }.toByteArray()
}
