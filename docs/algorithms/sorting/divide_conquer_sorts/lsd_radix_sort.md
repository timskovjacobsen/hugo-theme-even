# LSD Radix Sort

The **Least Significant Digit Radix Sort**, or **LSD Radix Sort**, is a non-comparison sorting algorithm.

Since it is a non-comparison algorithm, it must make assumptions about the input array. The assumption for LSD Radix Sort is that all elements in the array are integer-like.

The algorithm works by looking at the least significant digits of the elements and ordering by those.

The algorithm has a outer loop, which runs $k$ times, where $k$ denotes the number of digits in the largest absolute value element in the array. So if we have an array of $[113, -4113, 6284, -21246, 35, 55]$, $k$ would be $5$, because the largest absolute value in the array, $21246$, has $5$ digits.

* In the first iteration of the outer loop, the entire array will be sorted by the least significant digit. For base 10 integers, this will be the ones place, i.e. the right-most digit.

* In the second iteration of the outer loop, the, the entire array will be sorted by second least significant digit.

* ...

* When the outer loop has run $k$ times, the array is fully sorted

!!! warning "Only integer-like data"
    LSD Radix Sort works only on input arrays that are integer-like, i.e. any data that can be represented in integer format.

!!! tip "$O(kn)$ time complexity"
    Standard sorting algorithms that rely on comparisons are inherently limited to a time complexity of $O(n \log n)$.
    LSD Radix Sort does much better in $O(kn)$ time.

    Here $k$ denotes the number of digits in the largest number present in the array.

## Buckets

LSD Radix Sort is member of a sorting algorithm family called *Bucket Sorts*. It sorts by placing the elements into buckets.

Each bucket works as a queue when elements are put back into the array to be sorted. For this, any data structure that has queue-like behavior can be used.

!!! note
    Note that this procedure does not compare any of the integers. Each element is looked at individually and placed in the appropriate bucket in each iteration.

## Implementation

???+ example "Code"
    === "Java"
        ```java linenums="1"
        --8<--
        docs/algorithms/sorting/divide_conquer_sorts/code/java/LSDRadixSort.java
        --8<--
        ```

## Diagramming

!!! example

    We have the input array
    $$
    [1000,2014,231,53,−1,−92,−9403,634,−207]
    $$

    **The algorithm will need four iterations to sort the array**, since the largest absolute number has four digits.

    After the first iteration, the array will be sorted compared to the least significant digit of each number, i.e. the one's place.

    $$
    [-207, -9403, -92, -1, 1000, 231, 53, 2014, 634]
    $$

    Everything is now sorted by the one's place, the one's digits are $-7,-3,-2,-1,0,1,3,4,4$.  
    Notice how the negative values are taken into account. $-207$ has the least significant digit $-7$.

    We then "remove" the previous least significant digit, i.e. the one's place, from the integers. Thus, the lest significant digit becomes the ten's place.

    The second iteration ,sorted by the ten's place is

    $$
    [-92, -207, -9403, -1, 1000, 2014, 231, 634, 53]
    $$

    Notice how all data now is sorted by the ten's places, which are $-9,0,0,0,0, 1, 3, 3, 5$. If two integers have the same digit in the ten's place, they will be relatively sorted by the one's place'
