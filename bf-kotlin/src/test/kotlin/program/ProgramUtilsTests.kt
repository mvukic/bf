package program

import com.github.mvukic.program.getProgramPath
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ProgramUtilsTests {

    @Test
    fun `get program path - missing argument`() {
        assertFailsWith<IllegalStateException>("No program path specified") {
            getProgramPath(arrayOf())
        }
    }

    @ParameterizedTest
    @MethodSource("getProgramPathParams")
    fun `get program path`(args: Array<String>, expected: String) {
        assertEquals(getProgramPath(args), expected)
    }

    companion object {

        @JvmStatic
        fun getProgramPathParams() = listOf(
            Arguments.of(arrayOf("arg_1"), "arg_1"),
            Arguments.of(arrayOf("arg_1", "arg_2"), "arg_1")
        )

    }

}