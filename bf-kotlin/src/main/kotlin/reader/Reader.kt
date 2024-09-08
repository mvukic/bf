package com.github.mvukic.reader

interface Reader {

    fun read(): Byte

    fun readAll(): List<Byte>

}