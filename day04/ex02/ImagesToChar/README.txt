rm -rf target
mkdir target

cp -r src/resources target/.

touch lib/jcommander-1.82.jar
curl -o lib/jcommander-1.82.jar https://repo1.maven.org/maven2/com/beust/jcommander/1.82/jcommander-1.82.jar
touch lib/JCDP-4.0.2.jar
curl -o lib/JCDP-4.0.2.jar https://repo1.maven.org/maven2/com/diogonunes/JCDP/4.0.2/JCDP-4.0.2.jar

jar -xvf lib/JCDP-4.0.2.jar
jar -xvf lib/jcommander-1.82.jar

javac -d target -sourcepath src/java -cp lib/JCDP-4.0.2.jar:lib/jcommander-1.82.jar:. \
    src/java/edu/school21/printer/*/*.java

rm -rf META-INF
mv com target

jar cvfm target/images-to-chars-printer.jar src/manifest.txt -C target .
java -jar target/images-to-chars-printer.jar --white=RED --black=GREEN