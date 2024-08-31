import com.github.mvukic.Instructions
import com.github.mvukic.getBracketPairs
import com.github.mvukic.getMemoryPath
import com.github.mvukic.getProgramPath
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class UtilsTests {

    @ParameterizedTest
    @MethodSource("getBracketPairsParams")
    fun `get bracket pairs`(program: String, expected: Map<Int, Int>) {
        val instructions = Instructions(program)
        val actual = getBracketPairs(instructions)
        assertEqualMaps(expected, actual)
    }

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

    @ParameterizedTest
    @MethodSource("getMemoryPathParams")
    fun `get memory path`(args: Array<String>, expected: String?) {
        assertEquals(getMemoryPath(args), expected)
    }

    companion object {
        @JvmStatic
        fun getBracketPairsParams() = listOf(
            Arguments.of("", mapOf<Int, Int>()),
            Arguments.of("[]", mapOf(0 to 1, 1 to 0)),
            Arguments.of("[[]]", mapOf(0 to 3, 1 to 2, 2 to 1, 3 to 0)),
            Arguments.of("[][]", mapOf(0 to 1, 1 to 0, 2 to 3, 3 to 2)),
            Arguments.of("[*,.*,*]", mapOf(0 to 7, 7 to 0)),
            Arguments.of("[*,[.*],*]", mapOf(0 to 9, 9 to 0, 3 to 6, 6 to 3)),
        )

        @JvmStatic
        fun getProgramPathParams() = listOf(
            Arguments.of(arrayOf("arg_1"), "arg_1"),
            Arguments.of(arrayOf("arg_1", "arg_2"), "arg_1")
        )

        @JvmStatic
        fun getMemoryPathParams() = listOf(
            Arguments.of(arrayOf("arg_1"), null),
            Arguments.of(arrayOf("arg_1", "arg_2"), "arg_2"),
            Arguments.of(arrayOf("arg_1", "arg_2", "arg_3"), "arg_2")
        )
    }

}

private fun assertEqualMaps(expected: Map<Int, Int>, actual: Map<Int, Int>) {
    assertEquals(expected.size, actual.size)
    for ((k, v) in expected.entries) {
        assertTrue(actual.containsKey(k))
        assertEquals(actual[k], v)
    }
}