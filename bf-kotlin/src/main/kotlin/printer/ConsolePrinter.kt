package com.github.mvukic.printer

/**
 * A printer implementation that prints all messages to the console.
 */
class ConsolePrinter : Printer {

    private val stringBuilder = StringBuilder()

    override fun add(value: Byte) {
        stringBuilder.append(value)
    }

    override fun print() {
        println(stringBuilder.toString())
    }

    override fun get() = stringBuilder.toString()
}