package reader

import com.github.mvukic.reader.MockInputReader
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class MockReaderTests {

    @Test
    fun `Read values from the reader`() {
        val reader = MockInputReader(byteArrayOf(0x1, 0x2, 0x3))

        assertEquals(0x1, reader.read())
        assertEquals(0x2, reader.read())
        assertEquals(0x3, reader.read())
    }

    @Test
    fun `parse valid memory from string`() {
        val reader = MockInputReader.fromString("1,2,3")
        val actual = reader.readAll().toByteArray()

        val expected = byteArrayOf(0x1, 0x2, 0x3)

        assertContentEquals(expected, actual)
    }

    @Test
    fun `parse invalid memory from string - number does not fit into a byte`() {
        assertFailsWith<IllegalStateException>("Numbers must be between -128 and 127") {
            MockInputReader.fromString("1,299,3")
        }
    }

    @Test
    fun `parse invalid memory from string - not all values are numbers`() {
        assertFailsWith<IllegalStateException>("'G' is not a number") {
            MockInputReader.fromString("1,G,3")
        }
    }

}