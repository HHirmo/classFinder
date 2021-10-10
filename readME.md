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
Make sure to replace `<filename>` with a path to the file you wish to look for classes in and `<pattern>` with what to look for. For example:
```
java -jar class-finder.jar C:\ListOfClasses.txt FooBa
```