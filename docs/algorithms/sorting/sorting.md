# Sorting

## Properties of sorting algorithms

!!! note "Stable sorting"
    A **Stable** sorting algorithm maintains the order of duplicate entries in the array to be sorted. E.g. if the number $7$ appears multiple times in the array, all instances of $7$ must have the same relative order after sorting.

    Stability is often achieved by swapping element that are adjacent to each other.

    Stability allows for multiple levels of sorts. E.g. for sorting persons by their first name, then their last name. 

!!! note "Unstable sorting"
    Unstable sorting algorithms do not maintain the relative order of duplicate elements as they appeared in the original array.
    Instability often comes from performing swaps of elements that are non-adjacent to each other.

!!! note "Adaptivity"
    Adaptive sorting algorithms take advantage of the preexisting order of the array, improving performance.

    Some algorithms are inherently adaptive while some need to be adjusted to reap the benefits of adaptivity.

!!! note "In-place"
    A sorting is **in-place** if no $O(1)$ additional memory is necessary to achieve the sorting.

    In other words, an **in-place** sorting algorithm does not copy data into new memory addresses.

    Note that the memory used by building the recursive stack is excluded here.

!!! note "Out-of-place"
    A sorting algorithm is **out-of-place** if it copies data into new memory addresses in order to perform the sorting.

## Time complexity of sorting algorithms

| Operation | Best | Average | Worst | Stability | Memory Placement | Adaptivity | (Auxiliary) Space Complexity |
| ----------| ------ | ---------| -------- | -------- | ---------------- | ----------- | ------- |
| Bubble Sort | $O(n)$ | $O(n^2)$ | $O(n^2)$ | Stable | Inplace | Adaptive | $O(1)$ |
| Insertion Sort | $O(n)$ | $O(n^2)$ | $O(n^2)$ | Stable | Inplace | Adaptive | $O(1)$ |
| Selection Sort | $O(n^2)$ | $O(n^2)$ | $O(n^2)$ | Not Stable | Inplace | Not Adaptive | $O(1)$ |
| Cocktail Shaker Sort | $O(n)$ | $O(n^2)$ | $O(n^2)$ | Stable | Inplace | Adaptive | $O(1)$ |
| Merge Sort | $O(n \log n)$ | $O(n \log n)$ | $O(n \log n)$ | Stable | Out of place | Not Adaptive | $O(n)$ |
| Quick Sort | $O(n \log n)$ | $O(n \log n)$ | $O(n^2)$ | Not Stable | Inplace | Not Adaptive | Best/Average: $O(\log n)$, Worst: $O(n)$ |
| Quick Select | $O(n)$ | $O(n)$ | $O(n^2)$ | Not Stable | Inplace | Not Adaptive | Best/Average: $O(\log n)$, Worst: $O(n)$ |
| LSD Radix Sort | $O(kn)$ | $O(kn)$ | $O(kn)$ | Stable | Out of place | Not Adaptive | $O(n)$ |

*$k$* denotes the number of digits in the largest absolute value in the array. E.g. $k=4$ in the array $[463, 257, 1317, -73, 2]$ because the largest numerical value, $1317$, has four digits.
