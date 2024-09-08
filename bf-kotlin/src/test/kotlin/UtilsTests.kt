import com.github.mvukic.getBracketPairs
import com.github.mvukic.getBytesFromString
import com.github.mvukic.program.Program
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.*

class UtilsTests {

    @Test
    fun `parse valid bytes from string`() {
        val actual = getBytesFromString("1,2,3")
        val expected = byteArrayOf(0x1, 0x2, 0x3)

        assertContentEquals(expected, actual)
    }

    @Test
    fun `parse invalid bytes from string - number does not fit into a byte`() {
        assertFailsWith<IllegalStateException>("Numbers must be between -128 and 127") {
            getBytesFromString("1,299,3")
        }
    }

    @Test
    fun `parse invalid bytes from string - not all values are numbers`() {
        assertFailsWith<IllegalStateException>("'G' is not a number") {
            getBytesFromString("1,G,3")
        }
    }

    @ParameterizedTest
    @MethodSource("getBracketPairsParams")
    fun `get bracket pairs`(program: String, expected: Map<Int, Int>) {
        val instructions = Program(program)
        val actual = getBracketPairs(instructions)
        assertEqualMaps(expected, actual)
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

    }
}

private fun assertEqualMaps(expected: Map<Int, Int>, actual: Map<Int, Int>) {
    assertEquals(expected.size, actual.size)
    for ((k, v) in expected.entries) {
        assertTrue(actual.containsKey(k))
        assertEquals(actual[k], v)
    }
}