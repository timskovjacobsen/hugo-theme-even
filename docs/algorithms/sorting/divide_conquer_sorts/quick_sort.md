# Quick Sort

Quick sort is a divide and conquer sorting algorithm, which partitions the array into three parts:

* One part is a single element sub array containing the chosen pivot point $p$, this sub array is located in the middle
* A left sub array containing elements that are smaller than $p$
* A right sub array with elements larger than $p$.

Quick Sort is arguably the most used sorting algorithm in practice.

In each iteration, Quick Sort places a value in its final position.

## Many variations

There are many variations of **Quick Sort**. The one explained here is the **randomized inplace quick sort** utilizing the **hoare partition scheme**.

The **randomized** part refers to the choice of index for the pivot point, which as the name suggests is chosen at random. Other common choices are first, last and middle index.

Generally, any point can be used as pivot point.

The choice of a random pivot point entails that on average the, the array is split roughly in half.

## Time complexity

Quick Sort is $O(n \log n)$ in both the best and average case.

One thing to note about Quick Sort however, is that the worst case time complexity is $O(n²)$. This is in contrast to merge sort which is $O(n \log n)$ even in the worst case.

* **Inplace** because it operates by swapping elements in the original array.
* **Not stable** because it does not guarantee that the relative positions of equal values are maintained. The instability is caused by the long swaps that might happen in the algorithm.
* **Not adaptive** because it does not take into account the preexisting condition of the array. In fact, the worst case of $O(n²)$ for Quick Sort is when the array is already or almost fully sorted, which solidifies that it is not adaptive.

Quick Sort and merge sort are frequently the top candidates for practical use cases of sorts because of their efficiency.
The reason quick sort is considered better is that it is in-place, whereas merge sort is out-of-place. Merge sort copies elements to another structure taking up more space compared to the in-place version of quick sort.

## Implementation

???+ example "Code"

    === "Java"
        ```java linenums="1"
        --8<--
        docs/algorithms/sorting/divide_conquer_sorts/code/java/QuickSort.java
        --8<--
        ```

## Description of the algorithm

1. **Initial Part**

    1. Define the base case as sub arrays with length less than 1
    2. Pick a pivot point
    3. Swap the first element and the pivot point

2. **Iteration Part**

    Initialize the loop counters $i$ and $j$ and start the outer loop.  
    Start at indices `i=1` and `j=array.length-1`, i.e. the last index.  
    Start the outer loop and run as long as `i` ***has not*** crossed `j`, i.e. if `i<=j`.

    ***In each iteration, do the following***

    1. Increment `i` until it hits an element that is **larger** than the pivot
    2. Decrement `j` until it hits an element that is **smaller** than the pivot
    3. If `i` **has not** crossed `j`, we swap their values, increment `i` and decrement `j`. Continue to next iteration.  
       Otherwise, continue to next iteration without doing anything and move to Part 3 (the recursion part).

3. **Recursion Part**

    The outer loop has terminated because `i` has crossed `j`, i.e. `i > j`.

    1. Swap values at the start index and `j`
    2. Recurse to the left sub array, `arr[start:j-1]`
    3. Recurse to the right sub array, `arr[j:end]`

This will recursively sort the sub arrays and thereby the global array inplace.

!!! example

    **TODO: SCANNED INSERT IMAGE**

## Diagramming

When diagramming here, the term "iteration" is used even though Quick Sort is a divide and conquer algorithm. An "iteration" can be equated to each partitioning, i.e. each recursive call done in Part 3 of the algorithm for all non-empty arrays.

??? example

    Show the array after two iterations of Quick Sort have been performed.

    The pivot point is always chosen as the first element in the sub array and underlined in green.

    <center>
    <img src="/algorithms/media/quick_sort_diagramming.jpg" width="550">
    </center>

## Quick Select

The Quick Select algorithm can be used to select the  $k^{th}$ smallest element in an array. Quick Select is not a sorting algorithm, but uses techniques from Quick Sort.

If we have a fully sorted array, the $k^{th}$ smallest element will be located at index `k-1`.

Quick Select is $O(n)$ in the best and average case, and $O(n²)$ in the worst case. It is inplace, not stable and not adaptive just like Quick Sort.

### Description of the Quick Select algorithm

Perform the same first two parts as for Quick Sort.

In the third part, i.e. the recursion part, we only recurse to the sub array where we know the $k^{th}$ smallest element will be located.

If `j=k-1`, we have found the $k^{th}$ smallest element and we stop.
