package com.github.mvukic

enum class InstructionType(val value: Char) {
    ADD('+'),
    SUB('-'),
    RIGHT('>'),
    LEFT('<'),
    OUTPUT('.'),
    INPUT(','),
    LOOP_START('['),
    LOOP_END(']');
}