package com.github.mvukic.printer

/**
 * A printer implementation that does not print messages anywhere.
 */
class NoOpPrinter : Printer {
    override fun add(value: Byte) {}

    override fun print() {}

    override fun get() = ""
}