package com.github.mvukic.interpreter

import com.github.mvukic.Printer
import com.github.mvukic.getBracketPairs
import com.github.mvukic.memory.Memory
import com.github.mvukic.program.Program
import com.github.mvukic.register.Registers

class Interpreter(
    private val program: Program,
    private val memory: Memory,
    private val registers: Registers = Registers()
) {

    fun start() {
        val brackets = getBracketPairs(program)

        while (true) {
            dumpRegisters()
            // Read current instruction
            val instruction = program.getInstruction(this.registers.instruction)

            when (instruction) {
                '+' -> {
                    memory.set(this.registers.memory, memory.get(this.registers.memory).inc())
                    this.registers.instruction++
                }

                '-' -> {
                    memory.set(this.registers.memory, memory.get(this.registers.memory).dec())
                    this.registers.instruction++
                }

                '>' -> {
                    this.registers.memory++
                    this.registers.instruction++
                }

                '<' -> {
                    this.registers.memory--
                    this.registers.instruction++
                }

                '.' -> {
                    print(memory.get(this.registers.memory))
                    this.registers.instruction++
                }

                ',' -> {
                    print("Input an ASCII character: ")
                    memory.set(this.registers.memory, readln().toByte())
                    this.registers.instruction++
                }

                '[' -> {
                    if (memory.get(this.registers.memory) == 0.toByte()) {
                        this.registers.instruction = brackets[this.registers.instruction] ?: error("Unmatched ']'")
                    } else {
                        this.registers.instruction++
                    }
                }

                ']' -> {
                    if (memory.get(this.registers.memory) != 0.toByte()) {
                        this.registers.instruction = brackets[this.registers.instruction] ?: error("Unmatched '['")
                    } else {
                        this.registers.instruction++
                    }
                }

                else -> this.registers.instruction++
            }

            // Halt the program
            if (this.registers.instruction >= program.length) {
                break
            }
        }
    }

    fun dumpMemory() = memory.toString()

    fun printMemory(printer: Printer) = printer.print(memory)

    fun dumpRegisters() = println(registers)
}