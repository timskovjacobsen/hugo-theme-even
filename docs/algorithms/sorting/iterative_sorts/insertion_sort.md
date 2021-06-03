# Insertion Sort

Insertion Sort begins at the front of the array and **Guarantees that all elements below $i$ are *relatively* sorted**.

"Relatively sorted" is meant in the sense that the sub array from `[0:i]` is sorted when seen by itself, but its elements might change once the entire array has been sorted.  

## Implementation

???+ example "Code"

    === "Java"
        ```java linenums="1"
        --8<--
        docs/algorithms/sorting/iterative_sorts/code/java/InsertionSort.java
        --8<--
        ```
    === "Python"
        ```python linenums="1"
        def insertion_sort(arr):
            for n in range(1, len(arr)):
                i = n
                while i > 0 and arr[i] < arr[i - 1]:
                    # Swap values at i and i-1 and decrement i
                    arr[i], arr[i - 1] = arr[i - 1], arr[i]
                    i -= 1
        ```

* The outer loop index `n` denotes the current index that is compared and inserted into the already sorted sub array to the left of it.

* The inner loop variable `i` starts at the end of the sub array, which is `n`

## Diagramming

Diagramming Insertion Sort is very easy.

!!! tip
    After $k$ iterations, the sub array `[0:k]` will be sorted when it's looked at alone. The remaining part of the entire `[k+1:n]` array is untouched.

!!! example "Example: Array after two iterations"
    Suppose we have the array
    $$
    [58,83,15,39,69,4,86]
    $$
    After two iterations, the sub array `[0:2]` is sorted $[15,58,83]$. The remaining elements are the same. The entire array after two iterations is thus
    $$
    [15,58,83,39,69,4,86]
    $$
