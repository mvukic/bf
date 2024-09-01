package com.github.mvukic.interpreter

import com.github.mvukic.memory.Memory
import com.github.mvukic.program.Program
import com.github.mvukic.register.Registers

object InterpreterExamples {

    /**
     * memory[0] = memory[0] + 1
     */
    val ADD_1 = Interpreter(
        Program("+"),
        Memory(byteArrayOf(0)),
        Registers()
    )

    /**
     * memory[0] = memory[0] + 2
     */
    val ADD_2 = Interpreter(
        Program("++"),
        Memory(byteArrayOf(0)),
        Registers()
    )

    /**
     * memory[0] = memory[0] + 1
     * memory[1] = memory[1] + 2
     */
    val ADD_1_AND_2 = Interpreter(
        Program("+>++"),
        Memory(byteArrayOf(0, 0)),
        Registers()
    )

    /**
     * memory[0] = memory[0] + 1
     * memory[1] = memory[1] + 2
     * memory[2] = memory[2] + 1
     */
    val ADD_1_AND_2_AND_1 = Interpreter(
        Program("+>++>+"),
        Memory(byteArrayOf(0, 0, 0)),
        Registers()
    )

    /**
     * memory[0] = memory[0] - 1
     */
    val SUB_1 = Interpreter(
        Program("-"),
        Memory(byteArrayOf(0)),
        Registers()
    )

    /**
     * memory[0] = memory[0] + 4 - 2
     * memory[1] = memory[1] + 2 - 1
     * memory[2] = memory[2] + 2
     */
    val SUB_ADD_MULTI = Interpreter(
        Program("++++>++>++<<-->-"),
        Memory(byteArrayOf(0, 0, 0)),
        Registers()
    )

    /**
     * memory[1] = memory[1] + memory[0]
     * memory[0] = 0
     */
    val ADD_FIRST_TO_SECOND = Interpreter(
        Program("[->+<]"),
        Memory(byteArrayOf(1, 0)),
        Registers()
    )

}