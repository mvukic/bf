package interpreter

import com.github.mvukic.interpreter.Interpreter
import com.github.mvukic.interpreter.InterpreterExamples
import com.github.mvukic.logger.NoOpLogger
import com.github.mvukic.reader.MockInputReader
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class InterpreterTests {

    @ParameterizedTest
    @MethodSource("simpleTestCases")
    fun `Simple test `(interpreter: Interpreter, expectedMemory: String, expectedOutput: String) {
        interpreter.start()

        val actualMemory = interpreter.dumpMemoryAsString()
        assertEquals(expectedMemory, actualMemory)

        val actualOutput = interpreter.printer.get()
        assertEquals(expectedOutput, actualOutput)
    }

    companion object {

        @JvmStatic
        fun simpleTestCases() = listOf(
            Arguments.of(InterpreterExamples.EXAMPLE_1.asInterpreter(logger = NoOpLogger()), "", ""),
            Arguments.of(InterpreterExamples.EXAMPLE_2.asInterpreter(logger = NoOpLogger()), "1", ""),
            Arguments.of(InterpreterExamples.EXAMPLE_3.asInterpreter(logger = NoOpLogger()), "2", ""),
            Arguments.of(InterpreterExamples.EXAMPLE_4.asInterpreter(logger = NoOpLogger()), "12", ""),
            Arguments.of(InterpreterExamples.EXAMPLE_5.asInterpreter(logger = NoOpLogger()), "121", ""),
            Arguments.of(InterpreterExamples.EXAMPLE_6.asInterpreter(logger = NoOpLogger()), "-1", ""),
            Arguments.of(InterpreterExamples.EXAMPLE_7.asInterpreter(logger = NoOpLogger()), "212", ""),
            Arguments.of(InterpreterExamples.EXAMPLE_8.asInterpreter(logger = NoOpLogger()), "01", ""),
            Arguments.of(InterpreterExamples.EXAMPLE_9.asInterpreter(logger = NoOpLogger()), "1", "1"),
            Arguments.of(InterpreterExamples.EXAMPLE_10.asInterpreter(logger = NoOpLogger()), "12", "12"),
            Arguments.of(InterpreterExamples.EXAMPLE_11.asInterpreter(logger = NoOpLogger()), "12", "2"),
            Arguments.of(
                InterpreterExamples.EXAMPLE_12.asInterpreter(
                    logger = NoOpLogger(), reader = MockInputReader(byteArrayOf(0x1))
                ), "1", "1"
            ),
        )
    }

}