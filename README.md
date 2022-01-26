# SWE262P-project

Github repo for SWE 262P Programming Styles Project.

Group member: Kaiqin Chen, Ruiyan Ma

[Milestone Readme File](https://github.com/tonychen257/SWE262P-project/blob/main/Milestone/src/M1/README.md)

[Milestone2](https://github.com/tonychen257/SWE262P-project/blob/main/Milestone2_README.md)

[Milestone Fork Repo](https://github.com/tonychen257/JSON-java)

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

