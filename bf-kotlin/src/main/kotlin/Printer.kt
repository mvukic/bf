package com.github.mvukic

import java.io.File

interface Printer {
    fun print(memory: Memory)
}

class ConsolePrinter : Printer {
    override fun print(memory: Memory) {
        println(memory)
    }
}

class FilePrinter(path: String) : Printer {

    private val file = File(path)

    override fun print(memory: Memory) {
        file.writeText(memory.toString())
    }
}