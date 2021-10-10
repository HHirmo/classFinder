# Class finder

## Compiling

The program can be compiled into an executable .jar file using the kotlin command line compiler

```
kotlinc src\main\kotlin -include-runtime -d class-finder.jar
```

## Running

The .jar file can be run with the following command

```
java -jar class-finder.jar <filename> <pattern>
```

Make sure to replace `<filename>` with a path to the file you wish to look for classes in and `<pattern>` with what to
look for. For example:

```
java -jar class-finder.jar C:\ListOfClasses.txt FooBa
```

## Using

The program takes a text file with class names separated by a line brake. It searches for matches in the class names
with the pattern which is case-sensitive unless the entire pattern is in lower case. * can be used as a wildcard and
ending the pattern in a space will look for class names ending in that word. The program will then output a list of
classes sorted alphabetically excluding package names.