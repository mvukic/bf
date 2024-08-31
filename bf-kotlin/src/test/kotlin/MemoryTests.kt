import com.github.mvukic.Memory
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
    fun `get at out of bound indexes`() {
        val memory = Memory(ByteArray(2))
        assertFailsWith<IllegalStateException>("Memory at pointer '2' is null") {
            memory.get(2)
        }
    }

}