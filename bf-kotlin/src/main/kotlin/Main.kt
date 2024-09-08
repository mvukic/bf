package com.github.mvukic

import com.github.mvukic.arguments.Arguments
import com.github.mvukic.interpreter.Interpreter
import com.github.mvukic.logger.ConsoleLogger
import com.github.mvukic.memory.Memory
import com.github.mvukic.printer.ConsolePrinter
import com.github.mvukic.program.Program
import com.github.mvukic.program.readProgramFromPath
import com.github.mvukic.reader.MockInputReader
import com.github.mvukic.reader.StandardInputReader

fun main(args: Array<String>) {
    val arguments = Arguments.parse(args)
    val program = Program(readProgramFromPath(arguments.programPath))
    val memory = arguments.memory?.let { Memory.fromString(it) } ?: Memory(ByteArray(10))
    val reader = arguments.inputs?.let { MockInputReader.fromString(it) } ?: StandardInputReader()

    val interpreter = Interpreter(
        program = program,
        memory = memory,
        printer = ConsolePrinter(),
        logger = ConsoleLogger(),
        reader = reader
    )

    interpreter.start()

    println("Memory: ${interpreter.dumpMemoryAsString()}")
    println("Output: ${interpreter.printer.get()}")
}