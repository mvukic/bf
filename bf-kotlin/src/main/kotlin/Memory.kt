package com.github.mvukic

import java.nio.file.Paths
import kotlin.io.path.absolute


class Memory(private val memory: ByteArray) {

    fun get(pointer: Int) = memory.getOrNull(pointer) ?: error("Memory at pointer '$pointer' is null")

    fun set(pointer: Int, value: Byte) {
        memory[pointer] = value
    }

    override fun toString() = memory.joinToString("")

}