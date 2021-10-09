fun main(args: Array<String>) {
    val classMatchPattern = ClassFinder()
    val filename = args[0]
    val pattern = args[1]

    try {
        val matchingClasses = classMatchPattern.searchFileForClassNames(pattern, filename)
        for (className in matchingClasses) println(className)
    } catch (e: PatternFormatException) {
        println("Pattern must be formatted correctly. Make sure it starts and ends with an apostrophe (\')")
    }
}