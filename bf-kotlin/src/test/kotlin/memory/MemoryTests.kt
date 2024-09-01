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

}