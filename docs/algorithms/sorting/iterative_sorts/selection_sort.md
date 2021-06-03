# Selection Sort

Selection sort is an iterative sorting algorithm where the array to be sorted is divided in two parts. One array, which is sorted to its final global positions and one array that has not yet been sorted.

There are multiple ways to implement Selection Sort. One way is to find the **minimum** value in the sub array and swapping it with the first element in the sub array. Another one uses the **maximum** value of the sub array and instead swaps with the last element in the sub array.  
**In this note, we use the maximum value approach.**

Unlike Bubble Sort, there are no optimizations for Selection Sort.

## Implementation

???+ example "Code"

    An implementation of Selection Sort using the maximum value approach is shown below.

    === "Java"
        ```java linenums="1"
        --8<--
        docs/algorithms/sorting/iterative_sorts/code/java/SelectionSort.java
        --8<--
        ```
    === "Python"
        ```python linenums="1"
        def selection_sort(arr):
            """Implementation of selection sort.

            The algorithm is in-place, unstable and not adaptive
            Time complexity: Best and worst case: O(n^2)
            """

            # Loop from the back of the array and towards the front, looking at
            # all sub array, cutting one element out each iteration.
            # For each sub array, move the largest element to the end, i.e. index n 
            for n in range(len(arr) - 1, 1, -1):

                # Find the max element in the sub array, assume index n is the largest
                max_idx = n
                for i in range(n):
                    if arr[i] > arr[max_idx]:
                        # Update max element
                        max_idx = i
                        
                # Swap the values at index n and max_idx
                arr[n], arr[max_idx] = arr[max_idx], arr[n]
            
        ```    

* `n` denotes the last index of the sub array, i.e. the index where we will put the largest element that we find in the sub array

!!! note
    The swap is not wrapped in a conditional block, so it will always take place. If no swaps are needed, the element is swapped with itself. While this effectively does nothing, it still counts as a swap.

!!! tip "Facts"
    * Selection sort will always have performed $n-1$ swaps upon completion, regardless of the characteristics of the input array. It might swap an element by itself.

    * Minimum selection sort guarantees that after $i$ iterations, the first $i$ elements are sorted to their final positions.

    * Maximum selection sort guarantees that after $i$ iterations the last $i$ elements are sorted to their final positions.

    * Selection sort is unstable, but can easily be implemented as stable if we make it out of place rather than inplace.

## Diagramming

!!! example "Example: Array after three iterations"
    Suppose we have the array
    $$
    [4,3,1,5,2,6,7]
    $$

    We look at the maximum value in each iteration and move that to the largest index in the sub array. In the first iteration, the sub array is equal to the entire array.

    Since the maximum value is already at the largest index, the swap results in the same array. Note that the swap is still made, the value just overwrites itself.
    $$
    [4,3,1,5,2,6,7]
    $$
    In the second iteration, we look at the array $[4,3,1,5,2,6]$, which again has its maximum value at the last index. So again, nothing changes in the final array.
    $$
    [4,3,1,5,2,6,7]
    $$
    In the third iteration, we look at the array $[4,3,1,5,2]$. Here we find the maximum value to be $5$ and swap it with $2$, which is at the largest index.  
    The array after the third iteration becomes.
    $$
    [4,3,1,2,5,6,7]
    $$

    The summary for comparisons and swaps are

    | Iteration | Comparisons | Swaps |
    | ---- | ---- | ---- |
    | First | 6 | 1 |
    | Second | 5 | 1 |
    | Third | 4 | 1 |

    In general, Selection Sort compares all elements in the sub array and makes one swap in each iteration.
