package com.github.mvukic.logger

/**
 * A logger implementation that does not store any messages
 */
class NoOpLogger : Logger {

    /**
     * Logs a message without storing or displaying it
     */
    override fun log(message: String) {}
}