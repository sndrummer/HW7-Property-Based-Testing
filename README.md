# HW-7 Report

##1. Binary Search 
   testPostCondition(int[], int) revealed some problems with the binary search method 
   1. Input `[[-9, -6, -4, -2, 0, 0, 3, 5, 6, 8], 5]` failed.
   2. On observation it looks like the following need to be swapped since it is moving the search in the wrong direction.
        ```
        if (array[index] < value) {
            right = index - 1;
        } else
              left = index + 1;
        ```  
        This was changed to 
         ```
         if (array[index] < value) {
            left = index + 1;
         } else
            right = index - 1;
         ```

##2. Merge Sort   
   testPostCondition(int[] array) quickly revealed that there were some problems with the mergeSort
   - Original array: 
   ```
   [0, -1, -2, -3, -8, 8, -3, -10, 9, -1, -6, -9, 8, 10]
   ```
  vs Sorted array: 
  ```
  [-10, -9, -3, -1, -1, 0, 8, 10]
  ```
 - I noticed the following two lines 
  ```java
    int[] left = sort(array, from, m - 1);
    int[] right = sort(array, m + 1, to);
```
   The m index was not being covered which could cause the deletion of values so I just changed it to 
   ```java
    int[] left = sort(array, from, m);
    int[] right = sort(array, m + 1, to);
```
   and it appears to be working now.
   
##3. Stateful Testing
- While testing the hashTable I came across an error that was showing up some of the time in the tests
such as the following `testStatefulEquivalencePutThenRemoveOrder()` where a key was removed and then another key value pair was added on the first hash table
and then the same thing but in opposite order on the second hash table. What I found was the following discrepency while testing. 
The Assertion of equality failed with this difference: 
```
Expected :{ nrBuckets: 25, buffer: [null, null, null, null, (4, -2) :: null, (55555, 11111) :: (-5, -6) :: null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null]}
Actual   :{ nrBuckets: 25, buffer: [null, null, null, null, (4, -2) :: null, (55555, 11111) :: null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null]}
``` 
I noticed that when removing a key that is equal to the hash value, it was not deleted properly.
I realized that the following code: 
```
        while (el != null) {
            if (el.next != null && el.next.key == key) {
                el.next = el.next.next;
           }
            el = el.next;
        }
```
should be changed to 
```
         while (el != null) {
            while (el.next != null && el.next.key == key) {
                el.next = el.next.next;
            }
            el = el.next;
        }
```
This change seemed to resolve the problem because now it will continue to remove all the key/value pairs
with the given key. Which I believe is how it should function. 

