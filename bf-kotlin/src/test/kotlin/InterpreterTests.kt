import com.github.mvukic.Instructions
import com.github.mvukic.Interpreter
import com.github.mvukic.Memory
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class InterpreterTests {

    @ParameterizedTest
    @MethodSource("simpleTestCases")
    fun `Simple test`(instructions: Instructions, memory: Memory, expected: String) {
        val interpreter = Interpreter(instructions, memory)
        interpreter.start()

        val actual = memory.toString()
        assertEquals(expected, actual)
    }

    companion object {

        @JvmStatic
        fun simpleTestCases() = listOf(
            // [0+1]
            Arguments.of(Instructions("+"), Memory(ByteArray(1)), "1"),
            // [0+2]
            Arguments.of(Instructions("++"), Memory(ByteArray(1)), "2"),
            // [0+1][0+2]
            Arguments.of(Instructions("+>++"), Memory(ByteArray(2)), "12"),
            // [0+1][0+2][0+1]
            Arguments.of(Instructions("+>++>+"), Memory(ByteArray(3)), "121"),
            // [0-1]
            Arguments.of(Instructions("-"), Memory(ByteArray(1)), "-1"),
            // [0+3-1]
            Arguments.of(Instructions("+++-"), Memory(ByteArray(1)), "2"),
            // [0+4-2][0+2-1][0+2]
            Arguments.of(Instructions("++++>++>++<<-->-"), Memory(ByteArray(3)), "212"),
        )
    }

}