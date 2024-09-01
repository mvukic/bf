package interpreter

import com.github.mvukic.interpreter.Interpreter
import com.github.mvukic.interpreter.InterpreterExamples
import com.github.mvukic.logger.NoOpLogger
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class InterpreterTests {

    @ParameterizedTest
    @MethodSource("simpleTestCases")
    fun `Simple test `(index: Int, interpreter: Interpreter, expected: String) {
        interpreter.start()

        val actual = interpreter.dumpMemory()
        assertEquals(expected, actual)
    }

    companion object {

        @JvmStatic
        fun simpleTestCases() = listOf(
            Arguments.of(0, InterpreterExamples.NO_OP.asInterpreter(logger = NoOpLogger()), ""),
            Arguments.of(1, InterpreterExamples.ADD_1.asInterpreter(logger = NoOpLogger()), "1"),
            Arguments.of(2, InterpreterExamples.ADD_2.asInterpreter(logger = NoOpLogger()), "2"),
            Arguments.of(3, InterpreterExamples.ADD_1_AND_2.asInterpreter(logger = NoOpLogger()), "12"),
            Arguments.of(4, InterpreterExamples.ADD_1_AND_2_AND_1.asInterpreter(logger = NoOpLogger()), "121"),
            Arguments.of(5, InterpreterExamples.SUB_1.asInterpreter(logger = NoOpLogger()), "-1"),
            Arguments.of(6, InterpreterExamples.SUB_ADD_MULTI.asInterpreter(logger = NoOpLogger()), "212"),
            Arguments.of(7, InterpreterExamples.ADD_FIRST_TO_SECOND.asInterpreter(logger = NoOpLogger()), "01"),
        )
    }

}