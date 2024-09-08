package com.github.mvukic.arguments

data class Arguments(
    val programPath: String,
    val memory: String?,
    val inputs: String?
) {
    companion object {

        const val PROGRAM_PATH_KEY = "--program-path"
        const val INIT_MEMORY_KEY = "--init-memory"
        const val INIT_INPUT_KEY = "--init-input"

        fun parse(args: Array<String>): Arguments {
            if (args.isEmpty()) error("Not enough arguments")

            // Get program path
            val programPath = getProgramPath(args)

            // Get memory value
            val memory = getMemoryValues(args)

            // Get input values
            val inputs = getInputValues(args)

            return Arguments(programPath, memory, inputs)
        }

        private fun getProgramPath(args: Array<String>): String {
            val programPathKeyIndex = args.indexOf(PROGRAM_PATH_KEY)
            if (programPathKeyIndex == -1) error("No program path provided")

            return args.getOrNull(programPathKeyIndex + 1) ?: error("No program path provided")
        }

        private fun getMemoryValues(args: Array<String>): String? {
            val memoryKeyIndex = args.indexOf(INIT_MEMORY_KEY)
            if (memoryKeyIndex == -1) {
                return null
            }
            return args.getOrNull(memoryKeyIndex + 1) ?: error("No memory argument provided")
        }

        private fun getInputValues(args: Array<String>): String? {
            val inputKeyIndex = args.indexOf(INIT_INPUT_KEY)
            if (inputKeyIndex == -1) {
                return null
            }
            return args.getOrNull(inputKeyIndex + 1) ?: error("No input argument provided")
        }
    }
}
