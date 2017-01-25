# java-data-structures
Data structures written in Java.

[![Build Status](https://travis-ci.org/marioluan/java-data-structures.svg?branch=master)](https://travis-ci.org/marioluan/java-data-structures)
[![Coverage Status](https://coveralls.io/repos/github/marioluan/java-data-structures/badge.svg?branch=master)](https://coveralls.io/github/marioluan/java-data-structures?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/5886d25de25f59003995104a/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/5886d25de25f59003995104a)

--

**Pre-requisites:**
- java (v1.8)
- gradle (v3.3)

## Code style & formatter (eclipse users only)
Import the files [code-style.xml](code-style.xml) and [formatter.xml](formatter.xml) into your IDE.

## Test
```bash
gradle clean coberturaCheck check
# Test summary will be located at `build/reports/tests/test/index.html`
# Coverage report will be located at `build/reports/cobertura/index.html`.
# Code style issues report will be located at `build/reports/checkstyle/main.html`
```

## Build
```bash
gradle clean assemble
```

## Available data structures
- [Deque](https://github.com/marioluan/java-data-structures/blob/master/src/main/java/io/github/marioluan/datastructures/Deque.java)
- [Randomized Queue](https://github.com/marioluan/java-data-structures/blob/master/src/main/java/io/github/marioluan/datastructures/RandomizedQueue.java)

## References
- [https://www.coursera.org/learn/algorithms-part1](https://www.coursera.org/learn/algorithms-part1)
