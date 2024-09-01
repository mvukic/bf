package com.github.mvukic

import com.github.mvukic.arguments.Arguments
import com.github.mvukic.interpreter.Interpreter
import com.github.mvukic.logger.ConsoleLogger
import com.github.mvukic.memory.Memory
import com.github.mvukic.printer.ConsolePrinter
import com.github.mvukic.program.Program
import com.github.mvukic.reader.MockReader

fun main(args: Array<String>) {
    val arguments = Arguments.parse(args)
    val program = Program(arguments.programPath)
    // TODO: Split by , then validate that each number fits into byte and tehn convert each number to byte
    val memory = Memory(arguments.memory?.toByteArray() ?: ByteArray(10))

    val interpreter = Interpreter(
        program = program,
        memory = memory,
        printer = ConsolePrinter(),
        logger = ConsoleLogger(),
        reader = MockReader(byteArrayOf(0x0))
    )
    interpreter.start()

    println("Memory: ${interpreter.dumpMemoryAsString()}")
    println("Output: ${interpreter.printer.get()}")
}