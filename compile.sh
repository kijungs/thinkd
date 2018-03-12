echo compiling java sources...
rm -rf class
mkdir class

javac -cp ./fastutil-7.2.0.jar -d class $(find ./src -name *.java)

echo make jar archive...
cd class
jar cf ThinkD-1.0.jar ./
rm ../ThinkD-1.0.jar
mv ThinkD-1.0.jar ../
cd ..
rm -rf class

echo done.
