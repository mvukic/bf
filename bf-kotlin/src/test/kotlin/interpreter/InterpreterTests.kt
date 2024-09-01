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
            Arguments.of(InterpreterExamples.NO_OP.asInterpreter(), ""),
            Arguments.of(InterpreterExamples.ADD_1.asInterpreter(), "1"),
            Arguments.of(InterpreterExamples.ADD_2.asInterpreter(), "2"),
            Arguments.of(InterpreterExamples.ADD_1_AND_2.asInterpreter(), "12"),
            Arguments.of(InterpreterExamples.ADD_1_AND_2_AND_1.asInterpreter(), "121"),
            Arguments.of(InterpreterExamples.SUB_1.asInterpreter(), "-1"),
            Arguments.of(InterpreterExamples.SUB_ADD_MULTI.asInterpreter(), "212"),
            Arguments.of(InterpreterExamples.ADD_FIRST_TO_SECOND.asInterpreter(), "01"),
        )
    }

}