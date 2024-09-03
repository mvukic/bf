package com.github.mvukic

import com.github.mvukic.arguments.Arguments
import com.github.mvukic.interpreter.Interpreter
import com.github.mvukic.interpreter.InterpreterExamples
import com.github.mvukic.logger.ConsoleLogger
import com.github.mvukic.memory.Memory
import com.github.mvukic.printer.ConsolePrinter
import com.github.mvukic.program.Program
import com.github.mvukic.program.readProgramFromPath
import com.github.mvukic.reader.MockReader

fun main(args: Array<String>) {
//    val arguments = Arguments.parse(args)
//    val program = Program(readProgramFromPath(arguments.programPath))
//    val memory = arguments.memory?.let { Memory.fromInput(it) } ?: Memory(ByteArray(10))

//    val interpreter = Interpreter(
//        program = program,
//        memory = memory,
//        printer = ConsolePrinter(),
//        logger = ConsoleLogger(),
//        reader = MockReader(byteArrayOf(0x0))
//    )

    val interpreter = InterpreterExamples.EXAMPLE_12.asInterpreter(printer = ConsolePrinter(), logger = ConsoleLogger(), reader = MockReader(byteArrayOf(0x0)))
    interpreter.start()

    println("Memory: ${interpreter.dumpMemoryAsString()}")
    println("Output: ${interpreter.printer.get()}")
}