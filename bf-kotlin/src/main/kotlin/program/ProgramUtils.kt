package com.github.mvukic.program

import com.github.mvukic.readFileContent

fun getProgramPath(args: Array<String>) = args.firstOrNull() ?: error("No program path specified")

fun readProgramFromPath(path: String): String {
    val content = readFileContent(path)
    if (content.isEmpty()) {
        error("Empty program")
    }
    return content
}
