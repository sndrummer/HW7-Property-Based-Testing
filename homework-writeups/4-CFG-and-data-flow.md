# Objectives

* Build control flow graphs (CFG) for any given method
* Compute a forward data-flow analysis on a control flow graph to determine the reaching definitions
* Compete a backward data-flow analysis on a control flow graph to determine the live variables

# Reading

* [Control Flow](https://en.wikipedia.org/wiki/Control_flow)
* [13-static-analysis-classic-problems.ppt](https://bitbucket.org/byucs329/byu-cs-329-lecture-notes/src/master/13-static-analysis-classic-problems.ppt)
* [Reaching Definitions](https://en.wikipedia.org/wiki/Reaching_definition)
* [Live Variables](https://en.wikipedia.org/wiki/Live_variable_analysis)

# Problems
1. (**10 points**) Build a CFG for the *f()* method in the **Misc** class.
2. (**25 points**) Perform a reaching definitions analysis using the CFG for *f()*. Be sure to clearly indicate *kill*/*gen* sets for each node in the CFG. Use the analysis to show whether or not the definition of *i* on line 43 can reach the read of *i* on line 52.
3. (**10 points**) Build a CFG for the *dnfsort()* method in the **Misc** class.
4. (**25 points**) Perform a live variable analysis using the CFG for *dnfsort()*. Be sure to show the *kill*/*gen* sets for each node in the CFG.
5. (**10 points**) Submit this assignment on Canvas as a PDF.
