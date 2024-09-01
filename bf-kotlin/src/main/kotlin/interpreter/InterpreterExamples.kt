package com.github.mvukic.interpreter

import com.github.mvukic.logger.ConsoleLogger
import com.github.mvukic.logger.Logger
import com.github.mvukic.memory.Memory
import com.github.mvukic.printer.ConsolePrinter
import com.github.mvukic.printer.Printer
import com.github.mvukic.program.Program
import com.github.mvukic.registers.Registers

object InterpreterExamples {

    data class Example(
        val program: Program,
        val memory: Memory,
        val registers: Registers,
        val printer: Printer = ConsolePrinter(),
        val logger: Logger = ConsoleLogger()
    ) {

        fun asInterpreter(printer: Printer? = null, logger: Logger? = null) =
            Interpreter(program, memory, registers, printer ?: this.printer, logger ?: this.logger)
    }

    /**
     * Empty program
     */
    val NO_OP = Example(
        Program(""),
        Memory(byteArrayOf()),
        Registers(),
    )

    /**
     * memory[0] = memory[0] + 1
     */
    val ADD_1 = Example(
        Program("+"),
        Memory(byteArrayOf(0)),
        Registers(),
    )

    /**
     * memory[0] = memory[0] + 2
     */
    val ADD_2 = Example(
        Program("++"),
        Memory(byteArrayOf(0)),
        Registers(),
        ConsolePrinter()
    )

    /**
     * memory[0] = memory[0] + 1
     *
     * memory[1] = memory[1] + 2
     */
    val ADD_1_AND_2 = Example(
        Program("+>++"),
        Memory(byteArrayOf(0, 0)),
        Registers(),
    )

    /**
     * memory[0] = memory[0] + 1
     *
     * memory[1] = memory[1] + 2
     *
     * memory[2] = memory[2] + 1
     */
    val ADD_1_AND_2_AND_1 = Example(
        Program("+>++>+"),
        Memory(byteArrayOf(0, 0, 0)),
        Registers(),
    )

    /**
     * memory[0] = memory[0] - 1
     */
    val SUB_1 = Example(
        Program("-"),
        Memory(byteArrayOf(0)),
        Registers(),
    )

    /**
     * memory[0] = memory[0] + 4 - 2
     *
     * memory[1] = memory[1] + 2 - 1
     *
     * memory[2] = memory[2] + 2
     */
    val SUB_ADD_MULTI = Example(
        Program("++++>++>++<<-->-"),
        Memory(byteArrayOf(0, 0, 0)),
        Registers(),
    )

    /**
     * memory[1] = memory[1] + memory[0]
     *
     * memory[0] = 0
     */
    val ADD_FIRST_TO_SECOND = Example(
        Program("[->+<]"),
        Memory(byteArrayOf(1, 0)),
        Registers(),
    )

    /**
     * Prints memory[0]
     */
    val PRINT_1 = Example(
        Program("."),
        Memory(byteArrayOf(0)),
        Registers(),
    )

    /**
     * memory[0] = memory[0] + 1
     * Prints memory[0]
     * memory[1] = memory[1] + 2
     * Prints memory[1]
     */
    val ADD_1_AND_2_AND_PRINT_ALL = Example(
        Program("+.>++."),
        Memory(byteArrayOf(0, 0)),
        Registers(),
    )

    /**
     * memory[0] = memory[0] + 1
     * memory[1] = memory[1] + 2
     * Prints memory[1]
     */
    val ADD_1_AND_2_AND_PRINT_SECOND = Example(
        Program("+>++."),
        Memory(byteArrayOf(0, 0)),
        Registers(),
    )

}