package com.github.mvukic.logger

/**
 * A logger interface to log messages from the interpreter.
 */
fun interface Logger {
    /**
     * Logs a message.
     *
     * @param message The message to be logged.
     */
    fun log(message: String)
}