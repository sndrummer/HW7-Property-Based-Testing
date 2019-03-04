# Objectives

* Write generators for random input to test stateless and stateful methods/objects
* Leverage JUnit 5 parameterized tests to run and report outcomes of tests on random input
* Write meaningful random tests for stateless methods/objects using any of the following

   * Post-condition holds
   * Runtime exceptions
   * Algebraic properties: inverse, append, etc.
   * Point-wise equivalent: matches slower assumed correct function

* Write meaningful random tests for stateful methods/objects using any of the followinf

  * Point-wise equivalent
  * Equivalent statements

# Reading

* [Dynamic Tests](https://blog.codefx.org/libraries/junit-5-dynamic-tests/)
* [Parametric Tests](https://blog.codefx.org/libraries/junit-5-parameterized-tests/)
* [Stateless Property based testing](https://docs.google.com/presentation/d/1iGbxZoL_cqr5vgYjyBFdE_kjKDfW7xfDzgRrJt_vXSw/edit)
* [Stateful Property Based Testing](https://docs.google.com/presentation/d/1gKC6CYCXQjIGwXd2-I6lcKgG6bi5q3nOmCqKoqX5LGI/edit#slide=id.ge46cba2e6_0_7)
* [Randoop](https://randoop.github.io/randoop/)

# Stateless Property-based Testing Problems

For each of the following classes, implement functions that generate input for test cases and write the properties that should be tested. Run the tests as **parameterized tests in JUnit 5**. **Report and fix** any errors you find. Consider any of the following for creating tests with random input:

   * Post-condition holds
   * Runtime exceptions
   * Algebraic properties: inverse, append, etc.
   * Point-wise equivalent: matches slower assumed correct function

Grading is based on the diversity of tests generated with the above techniques.

1) **(30 points)** [Binary search](https://en.wikipedia.org/wiki/Binary_search_algorithm): *O(ln n)* search for sorted array. The source is located in *homework-support/src/test/java/pbt/BinarySearchTest*. 

2) **(30 points)** [Merge sort](https://en.wikipedia.org/wiki/Merge_sort): *O(n ln n)* algorithm to sort an input array. The source is located in *homework-support/src/test/java/pbt/MergeSortTest*.

# Stateful Property-based Testing Problems

3) **(40 points)** This problem is to test an implementation of a hash table which uses [separate chaining](https://en.wikipedia.org/wiki/Hash_table) with linked lists. As discussed in class, a point-wise comparison with an oracle is one possible way to test. Another approach is to compare two objects created with different but equivalent statements. In pseudo code it goes as follows assuming that `command1` and `command2` are different be equivalent statements:


```java
prefix = randomActions();
suffix = randomActions();

TestObject T1 = new TestObject();
TestObject T2 = new TestObject();

output1 = perform(T1, prefix) + perform(T1, command1) + perform(T1, suffix);
output2 = perform(T2, prefix) + perform(T2, command2) + perform(T2, suffix);

equals(T1, T2) && equals(output1, output2);
```


An example of equivalent, but different, statements is


```
put(Ka,Va), put(Kb,Vb) <==> put(Kb,Vb), put(Ka, Va) iff Ka != Kb
```


These statements are equivalent in that `(Ka,Va)` and `(Kb,Vb)` are in the hash table no matter which set of statements are run.

Remember that it is also possible to have an error in the implementation of the property rather than the class under test! 

# Submission

Please upload a patch on canvas with your code and a **README.md** that explains the various problem solutions and reports any uncovered and corrected errors.