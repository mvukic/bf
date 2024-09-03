package com.github.mvukic.arguments

// TODO: Ability to define input values
data class Arguments(
    val programPath: String,
    val memory: String?
) {
    companion object {

        const val PROGRAM_PATH_KEY = "--program-path"
        const val INIT_MEMORY_KEY = "--init-memory"

        fun parse(args: Array<String>): Arguments {
            if (args.isEmpty()) error("Not enough arguments")

            // Get program path
            val programPathKeyIndex = args.indexOf(PROGRAM_PATH_KEY)
            if (programPathKeyIndex == -1) error("No program path provided")

            val programPath = args.getOrNull(programPathKeyIndex + 1) ?: error("No program path provided")

            // Get memory
            val memoryKeyIndex = args.indexOf(INIT_MEMORY_KEY)
            if (memoryKeyIndex == -1) {
                return Arguments(programPath, null)
            }
            val memory = args.getOrNull(memoryKeyIndex + 1) ?: error("No memory provided")
            return Arguments(programPath, memory)
        }
    }
}
