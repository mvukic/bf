package program

import com.github.mvukic.program.Program
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith


class ProgramTests {

    @Test
    fun `basic init`() {
        val program = Program("+-")
        assertEquals(2, program.length)
    }

    @Test
    fun `get instruction at specified indexes`() {
        val program = Program("+-")
        assertEquals('+', program.getInstruction(0))
        assertEquals('-', program.getInstruction(1))
    }

    @Test
    fun `get instruction at out of bound indexes`() {
        val program = Program("+-")
        assertFailsWith<IllegalStateException>("Instruction at pointer '2' is null") {
            program.getInstruction(2)
        }
    }

}