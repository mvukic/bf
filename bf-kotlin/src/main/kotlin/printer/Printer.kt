package com.github.mvukic.printer

/**
 * A printer interface to print messages from the interpreter.
 */
interface Printer {
    /**
     * Adds a byte value to the printer.
     *
     * @param value The byte value to be added.
     */
    fun add(value: Byte)

    /**
     * Prints the contents of the printer.
     */
    fun print()


    /**
     * Retrieves the contents of the printer as a string.
     *
     * @return The contents of the printer.
     */
    fun get(): String
}

