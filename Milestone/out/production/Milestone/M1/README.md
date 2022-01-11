# Milestone1

## Task description 

1. Read an XML file (given as command line argument) into a JSON object and write the JSON object back on disk as a JSON file.
2. Read an XML file into a JSON object, and extract some smaller sub-object inside, given a certain path (use JSONPointer). Write that smaller object to disk as a JSON file.
3. Read an XML file into a JSON object, check if it has a certain key path (given in the command line too). If so, save the JSON object to disk; if not, discard it.
4. Read an XML file into a JSON object, and add the prefix "swe262_" to all of its keys.
5. Read an XML file into a JSON object, replace a sub-object on a certain key path with another JSON object that you construct, then write the result on disk as a JSON file. 

# Task1

## Step

1. Go to the file dir using `cd src/M1`
2. Generate .class file using `javac Task1.java`
3. Input with one argument, for example `java Task1 ../doc/books.xml`

The program will create a json file named `[filename].json`.

- Source Code

# Task2

## Step

1. Go to the file dir using `cd src/M1`
2. Generate .class file using `javac Task2.java`
3. Input with two argument, for example `java Task2 ../doc/books.xml /catalog/book/0`

The program will create a json file named `[filename]-part.json`.

- Source Code

# Task3

## Step

1. Go to the file dir using `cd src/M1`
2. Generate .class file using `javac Task3.java`
3. Input with two argument, for example `java Task3 ../doc/books.xml /catalog/books`, if the path isn't exist, console will pop out `Path isn't exist.`

The program will create a json file named `[filename]PathTo.json`.

- Source Code

# Task4

## Step

1. Go to the file dir using `cd src/M1`
2. Generate .class file using `javac Task4.java`
3. Input with one argument, for example `java Task4 ../doc/books.xml`

The result will show at console with all keys added prefix `swe262_`.

- Source Code

# Task5

## Step

1. Go to the file dir using `cd src/M1`
2. Generate .class file using `javac Task5.java`
3. Input with two argument, for example `java Task5 ../doc/books.xml /catalog/book/0`, if the path isn't exist, console will pop out `Path isn't exist.` If it exists, then will pop out the content.
4. Then you need to enter the key value to constrcut a new object to replace. Like the pic below.

![CleanShot 2022-01-09 at 20.29.03](README/CleanShot%202022-01-09%20at%2020.29.03.png)

The program will create a json file named `[filename]-replaced.json`.

- Source Code

# GB-scale file

When I try to convert a 44.58GB XML file into json file using `Task1.java` program, it turn out like below.

The program run out of memory, it is what I expected because I didn't do any strategy to avoid this problem.

![CleanShot 2022-01-09 at 20.31.12](README/CleanShot%202022-01-09%20at%2020.31.12.png)