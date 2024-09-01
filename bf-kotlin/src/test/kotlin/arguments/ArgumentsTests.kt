package arguments

import com.github.mvukic.arguments.Arguments
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ArgumentsTest {

    @Test
    fun `Empty arguments should throw`() {
        assertFailsWith<IllegalStateException>("Not enough arguments") {
            Arguments.parse(emptyArray())
        }
    }

    @Test
    fun `No program path should throw`() {
        assertFailsWith<IllegalStateException> {
            Arguments.parse(arrayOf(Arguments.INIT_MEMORY_KEY, "1,2,3,-1"))
        }
    }

    @Test
    fun `No memory path should return null`() {
        val arguments = Arguments.parse(arrayOf(Arguments.PROGRAM_PATH_KEY, "program/path"))
        assertEquals("program/path", arguments.programPath)
        assertEquals(null, arguments.memory)
    }

    @Test
    fun `With memory path should return`() {
        val arguments = Arguments.parse(
            arrayOf(Arguments.PROGRAM_PATH_KEY, "program/path", Arguments.INIT_MEMORY_KEY, "1,2,3,-1")
        )
        assertEquals("program/path", arguments.programPath)
        assertEquals("1,2,3,-1", arguments.memory)
    }
}