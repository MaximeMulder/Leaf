# Leaf project

## Introduction

This repository contains the session project of my winter 2020 compilation classes at UQAM, Montreal, Canada.
This project is an interpreter for a simplified version of my Leaf language project (name is still subject to change).

## How to build

This project was made in Java using the compiler compiler [SableCC](http://sablecc.org/). To build the project, download SableCC version 4-beta.4 and run its jar in the project root directory using the command line:

```shell
java -jar sablecc.jar -d src -p leaf ./language.sablecc
```
