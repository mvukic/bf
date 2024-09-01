package interpreter

import com.github.mvukic.interpreter.Interpreter
import com.github.mvukic.interpreter.InterpreterExamples
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class InterpreterTests {

    @ParameterizedTest
    @MethodSource("simpleTestCases")
    fun `Simple test`(interpreter: Interpreter, expected: String) {
        interpreter.start()

        val actual = interpreter.dumpMemory()
        assertEquals(expected, actual)
    }

    companion object {

        @JvmStatic
        fun simpleTestCases() = listOf(
            Arguments.of(InterpreterExamples.ADD_1, "1"),
            Arguments.of(InterpreterExamples.ADD_2, "2"),
            Arguments.of(InterpreterExamples.ADD_1_AND_2, "12"),
            Arguments.of(InterpreterExamples.ADD_1_AND_2_AND_1, "121"),
            Arguments.of(InterpreterExamples.SUB_1, "-1"),
            Arguments.of(InterpreterExamples.SUB_ADD_MULTI, "212"),
            Arguments.of(InterpreterExamples.ADD_FIRST_TO_SECOND, "01"),
        )
    }

}