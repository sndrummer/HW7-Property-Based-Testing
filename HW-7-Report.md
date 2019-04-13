1. Binary Search 
    - testPostCondition(int[], int) revealed some problems with the binary search method 
        1. Input `[[-9, -6, -4, -2, 0, 0, 3, 5, 6, 8], 5]` failed
        2. On observation it looks like the following need to be swapped since it is moving the search in the wrong direction.
        ```
        if (array[index] < value) {
            right = index - 1;
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
        