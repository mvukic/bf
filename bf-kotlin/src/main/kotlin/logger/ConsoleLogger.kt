package com.github.mvukic.logger

class ConsoleLogger : Logger {

    override fun log(message: String) {
        println(message)
    }
}