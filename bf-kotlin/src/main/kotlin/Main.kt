package com.github.mvukic

import com.github.mvukic.interpreter.Interpreter
import com.github.mvukic.memory.Memory
import com.github.mvukic.memory.getMemoryPath
import com.github.mvukic.memory.readMemoryFromPath
import com.github.mvukic.printer.ConsolePrinter
import com.github.mvukic.program.Program
import com.github.mvukic.program.getProgramPath
import com.github.mvukic.program.readProgramFromPath

fun main(args: Array<String>) {
    val program = Program(readProgramFromPath(getProgramPath(args)))
    val memory = Memory(getMemoryPath(args)?.let { readMemoryFromPath(it) } ?: byteArrayOf(1, 0))
    val interpreter = Interpreter(program, memory, printer = ConsolePrinter())
//    val interpreter = InterpreterExamples.EXAMPLE_10.asInterpreter()
    interpreter.start()
    println("Memory: ${interpreter.dumpMemory()}")
    println("Output: ${interpreter.printer.get()}")
}