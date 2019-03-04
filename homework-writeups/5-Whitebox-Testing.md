# Learning Outcomes

  * White-box test methods for statement, branch, complexity, and MC/DC coverage
  * Determine what coverage criteria are met by a set of tests

# Reading

  * [White-box testing](https://en.wikipedia.org/wiki/White-box_testing)
  * [Code coverage](https://en.wikipedia.org/wiki/Code_coverage)
  * [MC/DC coverage](https://en.wikipedia.org/wiki/Modified_condition/decision_coverage)
  * [Cyclomatic complexity coverage](https://en.wikipedia.org/wiki/Cyclomatic_complexity)
  
# Problems

1. **(40 points)** Write tests to give full branch coverage as indicated by the *Branch Counters* for each method in `Set` class in the [Homework-Support Repository](https://bitbucket.org/byucs329/homework-support/src/master/). Be sure each test is self-documenting relative to problem two and branch coverage. **Document and fix any discovered bugs indicating which test uncovered the bug.**

    
    Keep in mind that one method may be needed to set up the test of another method. If the setup method has a bug, then it can be very tricky to test methods that rely on it. Try to test methods in an order that makes sense for the dependencies. Also keep in mind that a bug in the implementation may make it impossible to achieve full statement or branch coverage. This consequence in itself may be an indication of the bug's existence. 

    
    Be sure to pull the repository if you already have it cloned from the previous homework. After the pull, in Eclipse, right-click on the **Homework** project and select **Maven** --> **Update Project...**. The newest version drops all the Eclipse project information from the repository and rather lets the **POM** file do all the work on a import of an **Existing Maven Project** into the workspace.

2. **(40 points)** What additional tests are required for MC/DC coverage if any? Justify your answers. Be sure to clearly label each additional test, if any, relative to problem two and MC/DC coverage. **Document and fix any discovered bugs indicating which test uncovered the bug.** Does MC/DC coverage imply condition and decision coverage? Justify your answer.

3. **(20 point)** What additional tests are required for complexity coverage if any? Justify your answer relative to the independent paths in each method. Be sure to clearly label each additional test, if any, relative to problem three and complexity coverage. **Document and fix any discovered bugs indicating which test uncovered the bug.** Does MC/DC coverage imply complexity coverage? Justify your answer.

# Submission

Please upload a single patch with your tests that includes a README-HW5-Whitebox.md file that explains where to find the tests and any additional commentary or justification for each problem. The patch should apply to the current head of the  [Homework-Support Repository](https://bitbucket.org/byucs329/homework-support/src/master/). 

