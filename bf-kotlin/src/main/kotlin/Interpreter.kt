package com.github.mvukic

class Interpreter(
    private val instructions: Instructions,
    private val memory: Memory
) {

    private var instructionPointer = 0
    private var memoryPointer = 0

    fun start() {
        val brackets = getBracketPairs(instructions)

        while (true) {
            printStatus()
            // Read current instruction
            val instruction = instructions.get(instructionPointer)
            println("Instruction: $instruction")

            when (instruction) {
                '+' -> {
                    memory.set(memoryPointer, memory.get(memoryPointer).inc())
                    instructionPointer++
                }

                '-' -> {
                    memory.set(memoryPointer, memory.get(memoryPointer).dec())
                    instructionPointer++
                }

                '>' -> {
                    memoryPointer++
                    instructionPointer++
                }

                '<' -> {
                    memoryPointer--
                    instructionPointer++
                }

                '.' -> {
                    print(memory.get(memoryPointer))
                    instructionPointer++
                }

                ',' -> {
                    println("Input an ASCII character: ")
                    memory.set(memoryPointer, readln().toByte())
                    instructionPointer++
                }

                '[' -> {
                    if (memory.get(memoryPointer) == 0.toByte())
                        instructionPointer = brackets[instructionPointer]!!
                }

                ']' -> {
                    if (memory.get(memoryPointer) != 0.toByte())
                        instructionPointer = brackets[instructionPointer]!!
                }
            }

            // Halt the program
            if (instructionPointer >= instructions.length) {
                break
            }
        }
    }

    fun printMemory(printer: Printer) = printer.print(memory)

    fun printStatus() = println("Instruction pointer: $instructionPointer, Memory pointer: $memoryPointer")
}