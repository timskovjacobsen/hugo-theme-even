# Bubble Sort

Bubble Sort is an iterative sorting algorithm that "bubbles" the largest element to towards end of the array in each iteration.

Bubble Sort performs well when the input array is already sorted or almost entirely sorted because it is adaptive (if optimizations are implemented).

## Baseline algorithm

The array is sorted from the back towards the front in a fashion that can be described in word by the following example

1. Compare **first** element to:
    1. **Second** element. If first element is larger, swap the elements
    2. **Third** element. If second element is larger, swap the elements
    3. *... Continue until ***second to last*** has been compared to ***last element*** ...*

2. Compare **first** element to:
    1. **Second** element. If first element is larger, swap the elements
    2. **Third** element, if second element is larger, swap the elements
    3. *... Continue until ***third to last*** element has been compared with ***second to last*** element ...*

3. *... Continue as long as the last index to be compared is larger than 0 ...*

At each step in the outer loop, the element at the index that was treated is ensured to be at the correct final location. Thus, we don't need to include that index anymore in the algorithm. Thus, the index counter of the outer loop is decremented after each iteration. This is what sorts the array from the back.

!!! note "Code for baseline Bubble Sort"

    Python code for the baseline version of Bubble Sort **without optimizations** is presented below.

    ```python linenums="1"
    def bubble_sort(arr):
        stop_idx = len(arr) - 1
        while stop_idx > 0:
            i = 0
            while i < stop_idx:
                if arr[i] > arr[i + 1]:
                    arr[i], arr[i + 1] = arr[i + 1], arr[i] 
                i += 1
            stop_idx -= 1
    ```

    Alternative version with `for` loop:

    ```python linenums="1"
    def bubble_sort(items):
        for i in range(len(items)):
            for j in range(len(items) - 1 - i):
                if items[j] > items[j + 1]:
                    items[j], items[j + 1] = items[j + 1], items[j]
    ```

## Adaptive Bubble Sort

### Keeping track of swaps

A problem with the baseline algorithm is that it will continue even if the array is already completely sorted. Suppose we have a long array where only the first and last elements need to be swapped to have a correct sort.
The algorithm will still continue running to check for all possible combinations of swaps, since it is not context aware.

Detecting whether we can stop the algorithm early is actually quite simple:

!!! tip "Early termination criterion"
    If no swaps are made during the outer loop, the list must be sorted.

We can take advantage of this fact by implementing a boolean flag indicating whether or not a swap has taken place during the loop.

!!! example "Adaption by keeping track of swaps"

    The highlighted elements are the additions compared to the baseline algorithm.

    ```python linenums="1" hl_lines="3 5 10"
    def bubble_sort(arr):
        stop_idx = len(arr) - 1
        swap_made = True
        while stop_idx > 0 and swap_made:
            swap_made = False
            i = 0
            while i < stop_idx:
                if arr[i] > arr[i + 1]:
                    arr[i], arr[i + 1] = arr[i + 1], arr[i] 
                    swap_made = True
                i += 1
            stop_idx -= 1
    ```

In the example with a very long array, where only the two ends need to be swapped to achieve a sorted array, we would now need only two iterations in the outer loop rather than `len(arr)` iterations.

!!! note
    This optimization does not help the worst or average case time complexity of $O(n²)$, as we still might need to perform many swaps.
    The best case time complexity is improved to $O(n) though, since we will need to traverse the entire array to check if it is already sorted.

### Ignoring already sorted elements

A characteristic of Bubble Sort that the baseline algorithm does not properly utilize is that as we progress in the algorithm, bigger and bigger chunks of the array has already been sorted.

It would therefore make sense to neglect the part of the array that we know is sorted.

!!! tip "Already sorted elements"
    **Elements residing past the element that was last swapped in each iteration must already be sorted.** This fact can be used for optimizing the iteration.

This optimization is implemented in the code below.

!!! example "Adaption by ignoring already sorted elements"

    The Bubble Sort algorithm which implements the last swap optimization is presented below. In the Python version, the highlighted elements are the additions compared to the baseline algorithm.

    === "Java"
        ```java linenums="1"
        --8<--
        docs/algorithms/sorting/iterative_sorts/code/java/BubbleSort.java
        --8<--
        ```
    === "Python"
        ```python linenums="1" hl_lines="5 9 11"
        def bubble_sort(arr):
            stop_idx = len(arr) - 1
            while stop_idx > 0:
                i = 0
                last_swapped_idx = 0
                while i < stop_idx:
                    if arr[i] > arr[i + 1]:
                        arr[i], arr[i + 1] = arr[i + 1], arr[i] 
                        last_swapped_idx = i
                    i += 1
                stop_idx = last_swapped_idx
        ```

## Diagramming

!!! example
    Given an array
    $$
    [20,2,33,8,108,43,51]
    $$
    In the first iteration, we look at the first pair $20$ and $2$. Since $20$ is larger than $2$, we swap them.  
    WE advance one index and compare $20$ to $33$. They are already in the correct order, so no swap occurs. We advance to compare $33$ and $8$. These are swapped, and so on.

    After the full first iteration, Bubble Sort will yield the array
    
    $$
    [2,20,8,33,43,51,108]
    $$

    Notice that the largest element $108$ has "bubbled" all the way to the end in the first iteration.

    The second iteration follows the exact same procedure, but now with the updated array. The result is    
    $$
    [2,8,20,33,43,51,108]
    $$
