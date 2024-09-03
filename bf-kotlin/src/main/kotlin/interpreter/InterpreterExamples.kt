package com.github.mvukic.interpreter

import com.github.mvukic.logger.ConsoleLogger
import com.github.mvukic.logger.Logger
import com.github.mvukic.memory.Memory
import com.github.mvukic.printer.ConsolePrinter
import com.github.mvukic.printer.Printer
import com.github.mvukic.program.Program
import com.github.mvukic.reader.Reader
import com.github.mvukic.reader.StandardInputReader
import com.github.mvukic.registers.Registers

object InterpreterExamples {

    data class Example(
        val program: Program,
        val memory: Memory,
        val registers: Registers,
        val printer: Printer = ConsolePrinter(),
        val reader: Reader = StandardInputReader(),
        val logger: Logger = ConsoleLogger()
    ) {

        fun asInterpreter(printer: Printer? = null, logger: Logger? = null, reader: Reader? = null) =
            Interpreter(
                program,
                memory,
                registers,
                printer ?: this.printer,
                reader ?: this.reader,
                logger ?: this.logger
            )
    }

    /**
     * Empty program
     */
    val EXAMPLE_1 = Example(
        Program(""),
        Memory(byteArrayOf()),
        Registers(),
    )

    /**
     * ```
     * memory[0] = memory[0] + 1
     * ```
     */
    val EXAMPLE_2 = Example(
        Program("+"),
        Memory(byteArrayOf(0)),
        Registers(),
    )

    /**
     * ```
     * memory[0] = memory[0] + 2
     * ```
     */
    val EXAMPLE_3 = Example(
        Program("++"),
        Memory(byteArrayOf(0)),
        Registers(),
    )

    /**
     * ```
     * memory[0] = memory[0] + 1
     * memory[1] = memory[1] + 2
     * ```
     */
    val EXAMPLE_4 = Example(
        Program("+>++"),
        Memory(byteArrayOf(0, 0)),
        Registers(),
    )

    /**
     * ```
     * memory[0] = memory[0] + 1
     * memory[1] = memory[1] + 2
     * memory[2] = memory[2] + 1
     * ```
     */
    val EXAMPLE_5 = Example(
        Program("+>++>+"),
        Memory(byteArrayOf(0, 0, 0)),
        Registers(),
    )

    /**
     * ```
     * memory[0] = memory[0] - 1
     * ```
     */
    val EXAMPLE_6 = Example(
        Program("-"),
        Memory(byteArrayOf(0)),
        Registers(),
    )

    /**
     * ```
     * memory[0] = memory[0] + 4 - 2
     * memory[1] = memory[1] + 2 - 1
     * memory[2] = memory[2] + 2
     * ```
     */
    val EXAMPLE_7 = Example(
        Program("++++>++>++<<-->-"),
        Memory(byteArrayOf(0, 0, 0)),
        Registers(),
    )

    /**
     * ```
     * memory[1] = memory[1] + memory[0]
     * memory[0] = 0
     * ```
     */
    val EXAMPLE_8 = Example(
        Program("[->+<]"),
        Memory(byteArrayOf(1, 0)),
        Registers(),
    )

    /**
     * ```
     * Prints memory[0]
     * ```
     */
    val EXAMPLE_9 = Example(
        Program("+."),
        Memory(byteArrayOf(0)),
        Registers(),
    )

    /**
     * ```
     * memory[0] = memory[0] + 1
     * Print memory[0]
     * memory[1] = memory[1] + 2
     * Print memory[1]
     * ```
     */
    val EXAMPLE_10 = Example(
        Program("+.>++."),
        Memory(byteArrayOf(0, 0)),
        Registers(),
    )

    /**
     * ```
     * memory[0] = memory[0] + 1
     * memory[1] = memory[1] + 2
     * Print memory[1]
     * ```
     */
    val EXAMPLE_11 = Example(
        Program("+>++."),
        Memory(byteArrayOf(0, 0)),
        Registers(),
    )

    /**
     * ```
     * memory[0] = readByte()
     * Print memory[0]
     * ```
     */
    val EXAMPLE_12 = Example(
        Program(",."),
        Memory(byteArrayOf(0x0, 0x0)),
        Registers(),
    )
}