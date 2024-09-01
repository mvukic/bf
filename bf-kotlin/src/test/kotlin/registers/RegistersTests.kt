package registers

import com.github.mvukic.register.Registers
import kotlin.test.Test
import kotlin.test.assertEquals


class RegistersTests {

    @Test
    fun `basic init`() {
        val registers = Registers()
        assertEquals(0, registers.memory)
        assertEquals(0, registers.instruction)
    }

}