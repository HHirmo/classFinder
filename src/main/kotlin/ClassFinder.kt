import java.io.File

class ClassFinder {

    fun searchFileForClassNames(pattern: String, filename: String): List<String> {
        val file = File(filename)
        val matchingClasses = mutableListOf<String>()
        val patternParsed = parsePattern(pattern)

        file.forEachLine {
            if (matchName(it, patternParsed)) matchingClasses.add(it)
        }

        return sortClasses(matchingClasses)
    }

    fun sortClasses(classes: MutableList<String>): MutableList<String> {
        classes.sortBy { it.substringAfterLast(".") }
        return classes
    }

    fun matchName(fullClass: String, pattern: Pattern): Boolean {

        return if (pattern.ignoreCase) {
            matchCaseInsensitive(pattern, fullClass)
        } else {
            matchCaseSensitive(fullClass, pattern)
        }
    }

    private fun matchCaseInsensitive(pattern: Pattern, fullClass: String): Boolean {
        val className = fullClass.substringAfterLast(".")
        var classNameSearchPosition = 0
        for (patternChar in pattern.words[0].replace("*", "").toCharArray()) {
            val result = className.toLowerCase().drop(classNameSearchPosition).indexOfFirst { it == patternChar }
            if (result == -1) return false
            classNameSearchPosition += result + 1
        }
        return !(pattern.endsWithSpace && classNameSearchPosition != className.length)
    }

    private fun matchCaseSensitive(fullClass: String, pattern: Pattern): Boolean {

        val classWords = parseClass(fullClass)

        var patternWordIterator = 0
        var classWordIterator = 0
        while (classWordIterator < classWords.size) {

            if (pattern.words[patternWordIterator].contains("*")) {
                var classWordPosition = 0
                for (patternPart in pattern.words[patternWordIterator].split("*")) {
                    val result = classWords[classWordIterator].indexOf(patternPart, classWordPosition)
                    if (result == -1) {
                        classWordIterator += 1
                    }
                    classWordPosition += result + 1
                }
                patternWordIterator += 1
            } else {
                if (classWords[classWordIterator].contains(pattern.words[patternWordIterator])) {
                    patternWordIterator += 1
                }
            }

            if (patternWordIterator == pattern.words.size) {
                return if (pattern.endsWithSpace) {
                    classWordIterator + 1 == classWords.size
                } else true
            }

            classWordIterator += 1
        }
        return false
    }

    private fun parseClass(fullClass: String): MutableList<String> {
        val className = fullClass.substringAfterLast(".")
        val classWords = mutableListOf<String>()
        for (char in className.toCharArray()) {
            if (char.isUpperCase() || classWords.size == 0) {
                classWords.add(char.toString())
            } else {
                classWords[classWords.size - 1] += char.toString()
            }
        }
        return classWords
    }
}