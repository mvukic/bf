package com.github.mvukic.program


class Program(private val source: String) {

    val length = source.length

    fun getInstruction(pointer: Int) = source.getOrNull(pointer) ?: error("Instruction at pointer '$pointer' is null")
}