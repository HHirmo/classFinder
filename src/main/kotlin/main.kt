fun main(args: Array<String>) {
    val classMatchPattern = ClassFinder()
    val filename = args[0]
    val pattern = args[1]

    val matchingClasses = classMatchPattern.searchFileForClassNames(pattern, filename)
    for (className in matchingClasses) println(className)
}