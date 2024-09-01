package com.github.mvukic.program

import java.nio.file.Paths
import kotlin.io.path.absolute

fun getProgramPath(args: Array<String>) = args.firstOrNull() ?: error("No program path specified")

fun readProgramFromPath(path: String): String {
    val currentPath = Paths.get(".").absolute()
    val programPath = currentPath.resolve(path).normalize()

    val programFile = programPath.toFile()
    if (!programFile.isFile) {
        error("File not found at path '$programPath'")
    }
    val programContent = programFile.readLines().joinToString("").trim()
    if (programContent.isEmpty()) {
        error("Empty program")
    }
    return programContent
}