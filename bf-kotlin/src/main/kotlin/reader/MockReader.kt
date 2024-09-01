package com.github.mvukic.reader

class MockReader(bytes: ByteArray) : Reader {

    private val stack = bytes.toMutableList()

    override fun read(): Byte {
        return stack.removeFirst()
    }

}