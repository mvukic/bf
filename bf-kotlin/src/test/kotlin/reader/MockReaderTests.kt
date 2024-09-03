package reader

import com.github.mvukic.reader.MockReader
import kotlin.test.Test
import kotlin.test.assertEquals

class MockReaderTests {

    @Test
    fun `Read values from the reader`() {
        val reader = MockReader(byteArrayOf(0x1, 0x2, 0x3))

        assertEquals(0x1, reader.read())
        assertEquals(0x2, reader.read())
        assertEquals(0x3, reader.read())
    }

}