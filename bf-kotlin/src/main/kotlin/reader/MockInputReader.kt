package com.github.mvukic.reader

class MockInputReader(bytes: ByteArray) : Reader {

    private val stack = bytes.toMutableList()

    override fun read(): Byte {
        return stack.removeFirst()
    }

}