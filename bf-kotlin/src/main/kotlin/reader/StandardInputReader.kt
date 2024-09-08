package com.github.mvukic.reader

class StandardInputReader : Reader {

    override fun read(): Byte {
        return readln().toByte()
    }

    override fun readAll(): List<Byte> {
        error("This implementation does not support reading all bytes")
    }
}