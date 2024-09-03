package com.github.mvukic.memory

class Memory(private val memory: ByteArray) {

    fun get(pointer: Int) = memory.getOrNull(pointer) ?: error("Memory at pointer '$pointer' is null")

    fun set(pointer: Int, value: Byte) {
        memory[pointer] = value
    }

    fun dumpAsString() = memory.joinToString("")

    companion object {
        fun fromInput(input: String): Memory {
            val values = input.split(",")
            val numbers = values.map { it.toIntOrNull() ?: error("'$it' is not a number") }
            if (numbers.any { it < -128 || it > 127 }) {
                error("Numbers must be between -128 and 127")
            }
            val bytes = numbers.map { it.toByte() }
            return Memory(bytes.toByteArray())
        }
    }

}