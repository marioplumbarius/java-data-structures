# java-data-structures
Data structures written in Java.

[![Build Status](https://travis-ci.org/marioluan/java-data-structures.svg?branch=master)](https://travis-ci.org/marioluan/java-data-structures)
[![Coverage Status](https://coveralls.io/repos/github/marioluan/java-data-structures/badge.svg?branch=master)](https://coveralls.io/github/marioluan/java-data-structures?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/5886d25de25f59003995104a/badge.svg?style=flat-square)](https://www.versioneye.com/user/projects/5886d25de25f59003995104a)
[![codebeat badge](https://codebeat.co/badges/b3d65929-147a-4049-a356-42611a060b8d)](https://codebeat.co/projects/github-com-marioluan-java-data-structures)

**Pre-requisites:**
- docker (17.12.0-ce, build c97c6d6)
- docker-compose (1.19.0)

## Test
```bash
docker-compose run gradle gradle clean test check
# Test summary will be located at `build/reports/tests/test/index.html`
# Coverage report will be located at `build/reports/jacoco/test/html/index.html`
# Code style issues report will be located at `build/reports/checkstyle/main.html`
```

## How to contribute
Head over [CONTRIBUTING.md](CONTRIBUTING.md).

## Available data structures
- Multiset
    - [Bag](https://github.com/marioluan/java-data-structures/blob/master/src/main/java/io/github/marioluan/datastructures/multiset/Bag.java)
- Stack
    - [Linked List](https://github.com/marioluan/java-data-structures/blob/master/src/main/java/io/github/marioluan/datastructures/stack/LinkedList.java)
    - [Dynamic Array](https://github.com/marioluan/java-data-structures/blob/master/src/main/java/io/github/marioluan/datastructures/stack/DynamicArray.java)
- Queue
    - [Deque](https://github.com/marioluan/java-data-structures/blob/master/src/main/java/io/github/marioluan/datastructures/queue/Deque.java)
    - [Randomized Queue](https://github.com/marioluan/java-data-structures/blob/master/src/main/java/io/github/marioluan/datastructures/queue/RandomizedQueue.java)
- Priority Queue
    - [Ordered Linked List](https://github.com/marioluan/java-data-structures/blob/master/src/main/java/io/github/marioluan/datastructures/priority/queue/MinOrderedLinkedListPriorityQueue.java)
    - [Unordered Linked List](https://github.com/marioluan/java-data-structures/blob/master/src/main/java/io/github/marioluan/datastructures/priority/queue/MinUnorderedLinkedListPriorityQueue.java)
    - [MaxBinaryHeap](https://github.com/marioluan/java-data-structures/blob/master/src/main/java/io/github/marioluan/datastructures/priority/queue/MaxBinaryHeap.java)
- Symbol Table
    - [Unordered Linked List](https://github.com/marioluan/java-data-structures/blob/master/src/main/java/io/github/marioluan/datastructures/symboltable/LinkedListSymbolTable.java)
    - [Ordered Array](https://github.com/marioluan/java-data-structures/blob/master/src/main/java/io/github/marioluan/datastructures/symboltable/ArraySymbolTable.java)
    - [Binary Search Tree](https://github.com/marioluan/java-data-structures/blob/master/src/main/java/io/github/marioluan/datastructures/symboltable/BinarySearchTree.java)
    - [Interval Search Tree](https://github.com/marioluan/java-data-structures/blob/master/src/main/java/io/github/marioluan/datastructures/symboltable/IntervalSearchTree.java) (TODO: unit tests)
    - [1D Range Count](https://github.com/marioluan/java-data-structures/blob/master/src/main/java/io/github/marioluan/datastructures/symboltable/OneDRangeCount.java)
    - Hash Table
        - [Separate Chaining Hash Table](https://github.com/marioluan/java-data-structures/blob/master/src/main/java/io/github/marioluan/datastructures/symboltable/hash/SeparateChainingHashTable.java)
        - [Linear Probing Hash Table](https://github.com/marioluan/java-data-structures/blob/master/src/main/java/io/github/marioluan/datastructures/symboltable/hash/LinearProbingHashTable.java)
- Graph
    - [Undirected](https://github.com/marioluan/java-data-structures/blob/master/src/main/java/io/github/marioluan/datastructures/graph/Undirected.java)
    - [Directed (Digraph)](https://github.com/marioluan/java-data-structures/blob/master/src/main/java/io/github/marioluan/datastructures/graph/Digraph.java)
    - Search
        - [Depth-First Search](https://github.com/marioluan/java-data-structures/blob/master/src/main/java/io/github/marioluan/datastructures/graph/search/DepthFirstSearch.java)
        - [Breadth-First Search](https://github.com/marioluan/java-data-structures/blob/master/src/main/java/io/github/marioluan/datastructures/graph/search/BreadthFirstSearch.java)
        - [Connected Components](https://github.com/marioluan/java-data-structures/blob/master/src/main/java/io/github/marioluan/datastructures/graph/search/ConnectedComponents.java)
    - Sort
        - [Topological Sort](https://github.com/marioluan/java-data-structures/blob/master/src/main/java/io/github/marioluan/datastructures/graph/sort/TopologicalSort.java)

## References
- [https://www.coursera.org/learn/algorithms-part1](https://www.coursera.org/learn/algorithms-part1)
- [https://www.coursera.org/learn/algorithms-part2](https://www.coursera.org/learn/algorithms-part2)
