package com.github.mvukic

fun main(args: Array<String>) {
    val program = Program(readProgramFromPath(getProgramPath(args)))
    val memory = Memory(readMemoryFromPathOrDefault(getMemoryPath(args), ByteArray(50)))
    val interpreter = Interpreter(program, memory)
    interpreter.start()
}




