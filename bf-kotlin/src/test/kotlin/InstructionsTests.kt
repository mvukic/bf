import com.github.mvukic.Instructions
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith


class InstructionsTests {

    @Test
    fun `basic init`() {
        val instructions = Instructions("+-")
        assertEquals(2, instructions.length)
    }

    @Test
    fun `get at specified indexes`() {
        val instructions = Instructions("+-")
        assertEquals('+', instructions.get(0))
        assertEquals('-', instructions.get(1))
    }

    @Test
    fun `get at out of bound indexes`() {
        val instructions = Instructions("+-")
        assertFailsWith<IllegalStateException>("Instruction at pointer '2' is null") {
            instructions.get(2)
        }
    }

}