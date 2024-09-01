package com.github.mvukic.printer

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