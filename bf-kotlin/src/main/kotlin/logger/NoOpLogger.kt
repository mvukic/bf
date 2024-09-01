package com.github.mvukic.logger

class NoOpLogger : Logger {

    override fun log(message: String) {}
}