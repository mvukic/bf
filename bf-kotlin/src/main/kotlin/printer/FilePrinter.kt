package com.github.mvukic.printer

import java.io.File

/**
 * A printer implementation that prints all messages to a file.
 */
class FilePrinter(path: String) : Printer {

    private val file = File(path)
    private val stringBuilder = StringBuilder()

    override fun add(value: Byte) {
        stringBuilder.append(value)
    }

    override fun print() {
        file.appendText(stringBuilder.toString())
    }

    override fun get() = stringBuilder.toString()
}