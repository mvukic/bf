package com.github.mvukic

import com.github.mvukic.interpreter.Interpreter
import com.github.mvukic.memory.Memory
import com.github.mvukic.memory.readMemoryFromPath
import com.github.mvukic.printer.ConsolePrinter
import com.github.mvukic.program.Program

fun main(args: Array<String>) {
    val arguments = Arguments.parse(args)
    val program = Program(arguments.programPath)
    val memory = Memory(arguments.memoryPath?.let { readMemoryFromPath(it) } ?: ByteArray(10))
    val interpreter = Interpreter(program, memory, printer = ConsolePrinter())
    interpreter.start()
    println("Memory: ${interpreter.dumpMemory()}")
    println("Output: ${interpreter.printer.get()}")
}