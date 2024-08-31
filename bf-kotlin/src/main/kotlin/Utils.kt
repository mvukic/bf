package com.github.mvukic

fun getBracketPairs(instructions: Instructions): Map<Int, Int> {

    val stack = mutableListOf<Int>()
    val pairs = mutableMapOf<Int, Int>()

    for (index in 0 until instructions.length) {
        when (instructions.getInstruction(index)) {
            '[' -> stack.add(index)
            ']' -> {
                if (stack.isEmpty()) error("Unmatched ']' at index $index")
                val openingIndex = stack.removeAt(stack.lastIndex)
                pairs[openingIndex] = index
                pairs[index] = openingIndex
            }
        }
    }

    return pairs
}