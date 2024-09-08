package com.github.mvukic.reader

import com.github.mvukic.getBytesFromString

class MockInputReader(bytes: ByteArray) : Reader {

    private val stack = bytes.toMutableList()

    override fun read(): Byte {
        return stack.removeFirst()
    }

    override fun readAll(): List<Byte> {
        return stack
    }

    companion object {
        fun fromString(value: String): MockInputReader {
            return MockInputReader(getBytesFromString(value))
        }
    }

}