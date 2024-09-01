package com.github.mvukic.reader

class StandardInputReader : Reader {

    override fun read(): Byte {
        return readln().toByte()
    }
}