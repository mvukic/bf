package com.github.mvukic.interpreter

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

    // TODO: Skip non instruction characters
    fun start() {
        if (program.length == 0) return
        val brackets = getBracketPairs(program)

        while (true) {
            logger.log(dumpRegisters())
            // Read current instruction
            val instruction = program.getInstruction(this.registers.instruction)

            when (instruction) {
                '+' -> {
                    memory.set(this.registers.memory, memory.get(this.registers.memory).inc())
                }

                '-' -> {
                    memory.set(this.registers.memory, memory.get(this.registers.memory).dec())
                }

                '>' -> {
                    this.registers.memory++
                }

                '<' -> {
                    this.registers.memory--
                }

                '.' -> {
                    printer.add(memory.get(this.registers.memory))
                }

                ',' -> {
                    logger.log("Input an ASCII character: ")
                    memory.set(this.registers.memory, reader.read())
                }

                '[' -> {
                    if (memory.get(this.registers.memory) == 0.toByte()) {
                        this.registers.instruction = brackets[this.registers.instruction] ?: error("Unmatched ']'")
                    }
                }

                ']' -> {
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