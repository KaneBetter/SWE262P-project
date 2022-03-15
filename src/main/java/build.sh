echo "Compiling json.jar..."
cd /Users/chenkaiqin/Documents/GitHub/SWE262P-project/src/main/java
javac org/json/*.java
jar cfv json-java.jar org/json/*.class
find . -name '*.class' -delete


echo "Compile a program that uses the jar"
javac -cp .:json-java.jar Test.java
echo "Run the Test file"
java Test