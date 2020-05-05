# Leaf project

## Introduction

This repository contains the session project of my winter 2020 compilation classes at UQAM, Montreal, Canada.
This project is an interpreter aiming to implement several experimental features for my Leaf language project.

## How to build

This project was made in Java using the compiler compiler [SableCC](http://sablecc.org/). To build the project, download SableCC version 4-beta.2 and run its jar in the project root directory using the command line:

```shell
java -jar sablecc.jar -d src -p leaf ./grammar.sablecc
```

## How to run

You can provide a file name as a command line argument to read and interpret the code of this file.
You can also provide no argument to type and run code on the console.

## Language

See the directory "examples" to see examples of implemented functionnalities.

## Disclaimer

This project is only a session project, it has been done in one month and a half and thus contains many imperfections.
For instance, the language grammar requires to use a lot of semicolons, generic types are not implemented, and incorrect code may result in a crash.

## Maintenance

While I might improve or expand this project in the future, it is currently not my focus. It is however likely that I will at one point rewrite an interpreter with a custom parser and in another language.
