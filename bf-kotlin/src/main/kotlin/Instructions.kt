package com.github.mvukic

import java.nio.file.Paths
import kotlin.io.path.absolute


class Instructions(private val program: String) {

    val length = program.length

    fun get(pointer: Int) = program.getOrNull(pointer) ?: error("Instruction at pointer '$pointer' is null")
}