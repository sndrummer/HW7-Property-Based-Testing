

# HW7 Property Based Testing
## Parameterized Tests
1. First you declare that it is a parameterized test
2. Use methodSource and give it THE NAME of the method that will 
generate the arguments that you would like to test.





1. Property based testing 
    - Generate a random input that satisfies the precondition
    - Feed it to the function
    - Check that a property on the output holds (postcondition)

2. Runtime Exceptions 
3. Algebraic Properties
    - Inverse: Test failing input basically, if x 
4. Equivalence of stateful objects 
    - generate random programs 
    - check that they give the same output 
    
#Equivalence of stateful objects example

```java
int[] test1(Set<int> b){
 b.add(2);
 b.add(3);
 b.add(1);
 b.add(5);
 b.add(5);
 b.remove(6);
 b.add(10);
 b.add(3);
 return b.allElements();
}

int [] res1 = test1(new SlowAndSimpleSet());
int [] res2 = test1(new SuperSmartSet());

return checkSame(res1,res2);
```


 
    