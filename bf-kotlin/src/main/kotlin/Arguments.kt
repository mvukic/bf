package com.github.mvukic

data class Arguments(
    val programPath: String,
    val memoryPath: String?
) {
    companion object {

        fun parse(args: Array<String>): Arguments {
            if (args.isEmpty()) error("Not enough arguments")

            // Get program path
            val programPathKeyIndex = args.indexOf("--program-path")
            if (programPathKeyIndex == -1) error("No program path provided")

            val programPath = args.getOrNull(programPathKeyIndex + 1) ?: error("No program path provided")

            // Get program path
            val memoryPathKeyIndex = args.indexOf("--memory-path")
            if (memoryPathKeyIndex == -1) {
                return Arguments(programPath, null)
            }
            val memoryPath = args.getOrNull(memoryPathKeyIndex + 1) ?: error("No memory path provided")
            return Arguments(programPath, memoryPath)
        }
    }
}
