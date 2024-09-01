package com.github.mvukic.logger

/**
 * A logger implementation that prints all messages to the console.
 */
class ConsoleLogger : Logger {

    override fun log(message: String) {
        println(message)
    }
}