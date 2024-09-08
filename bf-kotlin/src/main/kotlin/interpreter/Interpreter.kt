package com.github.mvukic.interpreter

import com.github.mvukic.InstructionType
import com.github.mvukic.getBracketPairs
import com.github.mvukic.logger.ConsoleLogger
import com.github.mvukic.logger.Logger
import com.github.mvukic.memory.Memory
import com.github.mvukic.printer.NoOpPrinter
import com.github.mvukic.printer.Printer
import com.github.mvukic.program.Program
import com.github.mvukic.reader.Reader
import com.github.mvukic.reader.StandardInputReader
import com.github.mvukic.registers.Registers

class Interpreter(
    private val program: Program,
    private val memory: Memory,
    private val registers: Registers = Registers(),
    val printer: Printer = NoOpPrinter(),
    private val reader: Reader = StandardInputReader(),
    private val logger: Logger = ConsoleLogger()
) {

    fun start() {
        val brackets = getBracketPairs(program)

        if (program.length == 0) {
            return
        }

        while (true) {
            logger.log(dumpRegisters())
            // Read current instruction
            val instruction = program.getInstruction(this.registers.instruction)
            logger.log("Instruction: $instruction")

            when (instruction) {
                InstructionType.ADD.value -> {
                    memory.set(this.registers.memory, memory.get(this.registers.memory).inc())
                }

                InstructionType.SUB.value -> {
                    memory.set(this.registers.memory, memory.get(this.registers.memory).dec())
                }

                InstructionType.RIGHT.value -> {
                    this.registers.memory++
                }

                InstructionType.LEFT.value -> {
                    this.registers.memory--
                }

                InstructionType.OUTPUT.value -> {
                    printer.add(memory.get(this.registers.memory))
                }

                InstructionType.INPUT.value -> {
                    logger.log("Input an ASCII character: ")
                    val value = reader.read()
                    logger.log("Input: $value")
                    memory.set(this.registers.memory, value)
                }

                InstructionType.LOOP_START.value -> {
                    if (memory.get(this.registers.memory) == 0.toByte()) {
                        this.registers.instruction = brackets[this.registers.instruction] ?: error("Unmatched ']'")
                    }
                }

                InstructionType.LOOP_END.value -> {
                    if (memory.get(this.registers.memory) != 0.toByte()) {
                        this.registers.instruction = brackets[this.registers.instruction] ?: error("Unmatched '['")
                    }
                }
            }

            // Increase instruction pointer
            this.registers.instruction++

            // Halt the program
            if (this.registers.instruction >= program.length) {
                break
            }
        }
    }

    fun dumpMemoryAsString() = memory.dumpAsString()

    fun dumpRegisters() = registers.toString()

    override fun toString() = "Interpreter ${hashCode()}"
}