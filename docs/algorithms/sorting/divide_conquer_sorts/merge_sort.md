# Merge sort

**Merge sort** is a Divide and Conquer sorting algorithm that recursively splits the input array into smaller sub arrays, which are sorted and thereafter merged.

![merge sort visualization](/algorithms/media/mergesort_visualization.png)

## Characteristics

!!! note "Out-of-place"
    Merge Sort is an out-of-place sorting algorithm. It copies data away from the original array.

!!! note "Stable"
    Merge Sort is stable, it keeps the relative order of duplicate elements. E.g. when sorting an array containing values "4B" and "4A" by number, it will always maintain the relative positions between them. If "4B" has a lower index than "4A" in the input array, that will also be true after sorting.

!!! note "Not adaptive"
    Merge Sort is not adaptive. It does not take the pre-existing conditions of the input array into account when sorting.

## Time complexity

For Merge Sort, the splitting part where we create the recursive tree is an $O(\log n)$ procedure. The sorting/merging part is $O(n)$. Combined, this gives a final time complexity of $O(\log n)$.

This implies that if we have two sorted arrays of length $m$ and $n$ and want to merge them into a fully sorted array in $O(m+n)$ time.

## Implementation

???+ example "Code"
    === "Java"
        ```java linenums="1"
        --8<--
        docs/algorithms/sorting/divide_conquer_sorts/code/java/MergeSort.java
        --8<--
        ```

## Diagramming

!!! example

    Suppose we have the following array to be sorted with Merge Sort.
    $$
    [4,3,1,5,2,6,7]
    $$

    We need to make $13$ recursive calls to fully sort the array. This is including the first recursive call on the entire array, which is just the initial method call. This can easily be realized by drawing the recursive tree of the algorithm.

    In the **first recursive call**, we passed in the entire array. Once it returns, the entire array will be sorted
    $$
    [1,2,3,4,5,6,7]
    $$

    Let's look at one of the intermediate steps, say **after second recursive call**. Here, the array is split in two as $[4,3,1][5,2,6,7]$, where any auxiliary element in case of an uneven number of elements goes to the second array.

    When this second recursive call terminates, both sub arrays will be sorted as $[1,3,4][2,5,6,7]$. Combined to one array, this is

    $$
    [1,3,4,2,5,6,7]
    $$
