data class Pattern(val words: MutableList<String>, val endsWithSpace: Boolean, val ignoreCase: Boolean)

fun parsePattern(pattern: String): Pattern {
    if (!pattern.startsWith("\'") || !pattern.endsWith("\'")) {
        throw PatternFormatException()
    }
    val patternEndsWithSpace = pattern.endsWith(" \'")
    val trimmedPattern = pattern.removeSurrounding("\'").removeSuffix(" ")

    var ignoreCase = true
    val patternWords = mutableListOf<String>()

    for (char in trimmedPattern.toCharArray()) {
        if (char.isUpperCase()) ignoreCase = false
        if (char.isUpperCase() || patternWords.size == 0) {
            patternWords.add(char.toString())
        } else {
            patternWords[patternWords.size - 1] += char.toString()
        }
    }
    return Pattern(patternWords, patternEndsWithSpace, ignoreCase)
}
