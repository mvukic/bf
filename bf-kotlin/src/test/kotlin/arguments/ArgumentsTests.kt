package arguments

import com.github.mvukic.arguments.Arguments
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ArgumentsTest {

    @Test
    fun `Empty arguments - should throw`() {
        assertFailsWith<IllegalStateException>("Not enough arguments") {
            Arguments.parse(emptyArray())
        }
    }

    @Test
    fun `No program path - should throw`() {
        assertFailsWith<IllegalStateException> {
            Arguments.parse(arrayOf(Arguments.INIT_MEMORY_KEY, "1,2,3,-1"))
        }
    }

    @Test
    fun `No memory key and value - should return null`() {
        val arguments = Arguments.parse(arrayOf(Arguments.PROGRAM_PATH_KEY, "program/path"))
        assertEquals("program/path", arguments.programPath)
        assertEquals(null, arguments.memory)
    }

    @Test
    fun `With memory key - should throw`() {
        assertFailsWith<IllegalStateException>("No memory argument provided") {
            Arguments.parse(arrayOf(Arguments.INIT_MEMORY_KEY, "1,2,3,-1"))
        }
    }

    @Test
    fun `With memory key and values - should return`() {
        val arguments = Arguments.parse(
            arrayOf(Arguments.PROGRAM_PATH_KEY, "program/path", Arguments.INIT_MEMORY_KEY, "1,2,3,-1")
        )
        assertEquals("program/path", arguments.programPath)
        assertEquals("1,2,3,-1", arguments.memory)
    }

    @Test
    fun `No input key and value - should return null`() {
        val arguments = Arguments.parse(arrayOf(Arguments.PROGRAM_PATH_KEY, "program/path"))
        assertEquals("program/path", arguments.programPath)
        assertEquals(null, arguments.inputs)
    }

    @Test
    fun `With input key - should throw`() {
        assertFailsWith<IllegalStateException>("No input argument provided") {
            Arguments.parse(arrayOf(Arguments.INIT_MEMORY_KEY, "1,2,3,-1"))
        }
    }
}