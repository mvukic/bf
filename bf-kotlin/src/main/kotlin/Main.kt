package com.github.mvukic

import com.github.mvukic.interpreter.Interpreter
import com.github.mvukic.memory.Memory
import com.github.mvukic.program.Program
import com.github.mvukic.program.getProgramPath
import com.github.mvukic.program.readProgramFromPath

fun main(args: Array<String>) {
    val program = Program(readProgramFromPath(getProgramPath(args)))
    val memory = Memory(readMemoryFromPathOrDefault(getMemoryPath(args), byteArrayOf(1, 0)))
    val interpreter = Interpreter(program, memory)
    interpreter.start()

    val consolePrinter = ConsolePrinter()
    interpreter.printMemory(consolePrinter)
}