package com.github.mvukic.program

import com.github.mvukic.readFileContent

fun readProgramFromPath(path: String): String {
    val content = readFileContent(path)
    if (content.isEmpty()) {
        error("Empty program")
    }
    return content
}
