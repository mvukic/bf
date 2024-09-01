package com.github.mvukic

import com.github.mvukic.interpreter.InterpreterExamples

fun main(args: Array<String>) {
//    val program = Program(readProgramFromPath(getProgramPath(args)))
//    val memory = Memory(readMemoryFromPathOrDefault(getMemoryPath(args), byteArrayOf(1, 0)))
//    val interpreter = Interpreter(program, memory, printer = ConsolePrinter())
    val interpreter = InterpreterExamples.ADD_1_AND_2_AND_PRINT_ALL.asInterpreter()
    interpreter.start()
    println("Memory: ${interpreter.dumpMemory()}")
    println("Output: ${interpreter.printer.get()}")
}