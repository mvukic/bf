package com.github.mvukic.printer

class NoOpPrinter : Printer {
    override fun add(value: Byte) {}

    override fun print() {}

    override fun get() = ""
}