package memory

import com.github.mvukic.memory.getMemoryPath
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class MemoryUtilsTests {

    @ParameterizedTest
    @MethodSource("getMemoryPathParams")
    fun `get memory path`(args: Array<String>, expected: String?) {
        assertEquals(getMemoryPath(args), expected)
    }

    companion object {

        @JvmStatic
        fun getMemoryPathParams() = listOf(
            Arguments.of(arrayOf("arg_1"), null),
            Arguments.of(arrayOf("arg_1", "arg_2"), "arg_2"),
            Arguments.of(arrayOf("arg_1", "arg_2", "arg_3"), "arg_2")
        )
    }
}