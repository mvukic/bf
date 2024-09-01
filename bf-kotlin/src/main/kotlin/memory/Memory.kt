package com.github.mvukic.memory

class Memory(private val memory: ByteArray) {

    fun get(pointer: Int) = memory.getOrNull(pointer) ?: error("Memory at pointer '$pointer' is null")

    fun set(pointer: Int, value: Byte) {
        memory[pointer] = value
    }

    fun dumpAsString() = memory.joinToString("")

}