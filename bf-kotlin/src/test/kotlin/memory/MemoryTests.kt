package memory

import com.github.mvukic.memory.Memory
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith


class MemoryTests {

    @Test
    fun `get at specified indexes`() {
        val memory = Memory(ByteArray(2))
        assertEquals(0x0, memory.get(0))
        assertEquals(0x0, memory.get(1))
    }

    @Test
    fun `set at specified index`() {
        val memory = Memory(ByteArray(2))
        assertEquals(0x0, memory.get(0))
        assertEquals(0x0, memory.get(1))
        memory.set(1, 0x1)
        assertEquals(0x1, memory.get(1))
    }

    @Test
    fun `get at out of bound index`() {
        val memory = Memory(ByteArray(2))
        assertFailsWith<IllegalStateException>("Memory at pointer '2' is null") {
            memory.get(2)
        }
    }

    @Test
    fun `set at out of bound index`() {
        val memory = Memory(ByteArray(2))
        assertFailsWith<ArrayIndexOutOfBoundsException>("Memory at pointer '2' is null") {
            memory.set(2, 0x1)
        }
    }

    @Test
    fun `parse valid memory from input`() {
        val actual = Memory.fromInput("1,2,3")
        val expected = Memory(byteArrayOf(0x1, 0x2, 0x3))

        assertEquals(expected.dumpAsString(), actual.dumpAsString())
    }

    @Test
    fun `parse invalid memory from input - number does not fit into a byte`() {
        assertFailsWith<IllegalStateException>("Numbers must be between -128 and 127") {
            Memory.fromInput("1,299,3")
        }
    }

    @Test
    fun `parse invalid memory from input - not all values are numbers`() {
        assertFailsWith<IllegalStateException>("'G' is not a number") {
            Memory.fromInput("1,G,3")
        }
    }

}