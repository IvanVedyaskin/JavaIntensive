rm -rf target
mkdir target 
javac -d target src/java/edu/school21/printer/*/*.java
java -classpath ./target edu.school21.printer.app.Program /Users/mmeredit/IdeaProjects/day04/src/main/java/ex00/ImagesToChar/src/java/edu/school21/printer/it.bmp 0 .
