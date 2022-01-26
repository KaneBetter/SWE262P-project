# Week 1
### Assigment Description
Use one stop_words file and one data file to calculate the top 25 term frequency in the document.

### How to run the program
In order to run the `TermFrequency.java` program, we need to do following steps:

1. Use either Console or Shell and go to the dir using `cd Week1`.
1. Compile Java using `javac TermFrequency.java`.
2. Run the program with one input using `java TermFrequency ../pride-and-prejudice.txt`.
- [Source Code]
(https://replit.com/@tonychen257/SWE262PJava#Week1/TermFrequency.java)

# Week 2
## Four - Monolith Style Code

- No abstractions
- No use of library functions

### Instruction to run the code

```bash
cd Week2
javac Four.java
java Four ../pride-and-prejudice.txt
```

## Five - Cookbook Style Code

- Larger problem decomposed in procedural abstractions
- Larger problem solved as a sequence of commands, each corresponding to a procedure

```python
read_file(sys.argv[1])
filter_chars_and_normalize()
scan()
remove_stop_words()
frequencies()
sort()
```

### Instruction to run the code

```bash
cd Week2
javac Five.java
java Five ../pride-and-prejudice.txt
```

## Six - Pipeline Style Code

- Larger problem decomposed in functional abstractions. Functions, according to Mathematics, are relations from inputs to outputs.
- Larger problem solved as a pipeline of function applications

```python
print_all(sort(frequencies(remove_stop_words(scan(filter_chars_and_normalize(read_file(sys.argv[1]))))))[0:25])
```

### Instruction to run the code

```bash
cd Week2
javac Six.java
java Six ../pride-and-prejudice.txt
```
