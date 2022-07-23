rm -rf target
mkdir target
javac -d target src/java/edu/school21/printer/*/*.java
cp -r src/resources target/.
jar cvfm target/images-to-chars-printer.jar src/manifest.txt -C target .