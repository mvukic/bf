import com.github.mvukic.Instructions
import com.github.mvukic.getBracketPairs
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class UtilsTests {

    @ParameterizedTest
    @MethodSource("getBracketPairsParams")
    fun `get bracket pairs`(program: String, expected: Map<Int, Int>) {
        val instructions = Instructions(program)
        val actual = getBracketPairs(instructions)
        assertEqualMaps(expected, actual)
    }

    companion object {
        @JvmStatic
        fun getBracketPairsParams() = listOf(
            Arguments.of("[]", mapOf(0 to 1, 1 to 0)),
            Arguments.of("[[]]", mapOf(0 to 3, 1 to 2, 2 to 1, 3 to 0)),
            Arguments.of("[][]", mapOf(0 to 1, 1 to 0, 2 to 3, 3 to 2)),
            Arguments.of("[*,.*,*]", mapOf(0 to 7, 7 to 0)),
            Arguments.of("[*,[.*],*]", mapOf(0 to 9, 9 to 0, 3 to 6, 6 to 3)),
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