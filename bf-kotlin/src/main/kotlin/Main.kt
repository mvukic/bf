package com.github.mvukic

fun main(args: Array<String>) {
//    val instructions = Instructions(readProgramFromPath(getProgramPath(args)))
    val instructions = Instructions("++>++->+")
    val memory = Memory(readMemoryFromPathOrDefault(getMemoryPath(args), ByteArray(4)))
    val interpreter = Interpreter(instructions, memory)
    interpreter.start()

    val consolePrinter = ConsolePrinter()
    interpreter.printMemory(consolePrinter)
}