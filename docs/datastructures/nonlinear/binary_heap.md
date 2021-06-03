# Binary Heap

## Introduction

Binary Heaps are a subset of Binary Trees (but not Binary Search Trees). Binary Heaps have the same 0, 1 or 2 children characteristic as Binary Trees.

The most notable characteristic of a Heap is that its shape must always be [**Complete**](binary_search_tree.md#properties), a binary heap is a complete binary tree. The completeness property implies that an Array is good backing data structure for implementing a Heap.

A Binary Heap is arguably the most commonly using Heap. Therefore, it is often just referred to as a Heap. This chapter will use **Heap** from here on out to refer to **Binary Heap**.

A Heap is not intended for accessing arbitrary data or searching, and instead support efficient *add* and *remove* operations.

## Properties

!!! height

    The height $h$ of a Binary Heap with $n$ nodes is $h = \log(n)$.

## Efficiency Overview

An efficiency table for Min/Max Heaps is given below.

| Operation               | Best   | Average     | Worst                  |
|-------------------------|--------|-------------|------------------------|
| Add                     | $O(1)$ | $O(1)$      | $O(n)$ / $O(log(n))^∗$ |
| Remove Min/Max value    | $O(1)$ | $O(\log n)$ | $O(\log n)$            |
| Arbitrary Search/Remove | $O(1)$ | $O(n)$      | $O(n)$                 |
| BuildHeap               | $O(n)$ | $O(n)$      | $O(n)$                 |
| Access Min/Max value    | $O(1)$ | $O(1)$      | $O(1)$                 |
| Space Complexity        | $O(n)$ | $O(n)$      | $O(n)$                 |

The *Remove* and *Find* operations listed are meant as the *minimum* for a Min Heap or the *maximum* for a Max Heap. The reason that finding those is so easy is that it is always stored at the root due to how heaps work.

## Heap Variations

Binary Heaps come in two order variants. The **Min Heap** and the **Max Heap**.

### Min Heap

In a **Min Heap**, the smallest data is located at the root. In general, the data in every parent, must be smaller than the data in its children.

This diagram shows a valid **Min Heap**.

<div class="mermaid">
%%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
graph TD;
    0((0))-->2((2));
    2((2))-->3((3));
    2((2))-->5((5));
    3((3))-->7((7));
    3((3))-->9((9));
    5((5))-->6((6));
    5((5))---fakeA;
    0((0))-->1((1));
    1((1))-->8((8));
    1((1))-->4((4));
    classDef invisNode color:transparent,fill:none,stroke:none;
    class fakeA invisNode;
    linkStyle 6 stroke:none;
</div>

!!! note
    There is no hierarchy between the children of a node (i.e. between siblings). In the example, the left child of the root is larger than the right child, and no ordering occurs between them. The same is true for any node.

### Max Heap

A **Max Heap** has the node with the largest data located at the root. The data in a parent must be larger than the data in its children. An example of a **Max Heap** is given below.

<div class="mermaid">
%%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
graph TD;
    9((9))-->8((8));
    8((8))-->3((3));
    3((3))-->2((2));
    3((3))-->1((1));
    8((8))-->6((6));
    6((6))-->4((4));
    6((6))---fakeA;
    9((9))-->7((7));
    7((7))-->5((5));
    7((7))-->0((0));
    classDef invisNode color:transparent,fill:none,stroke:none;
    class fakeA invisNode;
    linkStyle 6 stroke:none;
</div>

Just like for Min Heaps, there is no relationship or hierarchy between siblings.

## Implementations

A Heap is most conveniently implemented with an Array as backing data structure. Arrays are very efficient in their operations, and since Heaps have the *completeness* property, the backing Array will have contiguous data. I.e. the completeness ensures that is will be devoid of any gaps.

!!! warning "Indexing start at $1$"
    It's common for a Heap to be implemented with index $1$ as be the first element of the Array. This makes the indexing expressions much nicer to work with.

The Array that is backing the Heap has its element stored in **Levelorder**.

For the Min and Max Heaps given above, the backing Arrays become

\begin{align}
\text{Min Heap}& \qquad \emptyset, 0, 2, 1, 3, 5, 8, 4, 7, 9, 6 \\
\\
\text{Max Heap}& \qquad \emptyset, 9, 8, 7, 3, 6, 5, 0, 2, 1, 4
\end{align}

where $\emptyset$ denotes `null` as index 0 is not used.

!!! tip "Index arithmetics"

    When working with the backing array, the indices of the children and the parent of a given node must be readily available. The table below shows how to compute those indices for a Heap of size $n$ and a given index $k$.

<center>

| Node                                | Index                                      | Comment            |
|-------------------------------------|--------------------------------------------|--------------------|
| First element                       | $1$                                        |                    |
| Last element                        | $n$                                        |                    |
| Left child of element at index $k$  | $2k$                                       |                    |
| Right child of element at index $k$ | $2k + 1$                                   |                    |
| Parent of element at index $k$      | $\mathrm{floor} \left(\dfrac{k}{2}\right)$ | *Integer division* |

</center>

As shown, the table assumes that the first index of the backing Array is $1$. If the first element was located at index $0$, the last element would be at $n-1$ and all the expressions would change accordingly.

Note that some programming languages, such as Java, cut of the decimal part of the result from an integer division. Other languages, such as Python, do not.
Thus, in Java, simple integer division `n/2` could be used to index the parent. In Python, it could be done by the `//` operator.

## Adding to a Heap

When adding to a heap, we must maintain the **complete binary tree** property and the **order** property. It is much easier to ensure the completeness first by inserting the new node wisely and then deal with the order later by swapping nodes.

### Procedure

1. Insert the new node as the rightmost child of the lowest level in the heap.
   If the lower level is empty, insert as the leftmost child.

2. Perform an `upHeap` operation starting from the new node by continuously swapping with it's parent.
   Stop once the order property of the sub heap is fulfilled, i.e. once no swap in necessary with the parent.

    *This operation lends itself to a recursive implementation, as each swap operation is very similar.*

### Time complexity

The time complexity for for different operations are stated in the table below. A **Min Heap** is used as example, but complexities for a **Max Heap** can easily be inferred from it.

<center>

| Operation                    | Time complexity | Comment                                                        |
|------------------------------|-----------------|----------------------------------------------------------------|
| Adding, worst case           | $O(n)$          | *When the backing array needs to be resized*                   |
| Adding, amortized worst case | $O(\log n)$     | *When swapping through the entire height, where $h = \log(n)$* |

</center>

## Removing From a Heap

### Procedure

1. Store the root data in temporary variable. It must be returned by the method.

2. Insert the last node as the new root and set the last node to `null`, effectively removing it (garbage collected).
  
3. Perform a `downHeap` from the root to ensure new heap is valid order-wise

Note that the complete binary tree property is ensured from the start since we always remove the last node.
The last node is the rightmost node in the lowest level of the heap.

### Time complexity

| Operation              | Time complexity | Comment                                                          |
|------------------------|-----------------|------------------------------------------------------------------|
| Removing, best case    | $O(1)$          | *When no swaps are needed in the downheap, removing a leaf node* |
| Removing, average case | $O(\log n)$     | *Scales with the height of the heap, $h = \log n$*               |
| Removing, worst case   | $O(\log n)$     | *When swapping through the entire height, where $h = \log(n)$*   |

## Building a Heap From Unsorted Data

A Heap can be constructed from an array of unsorted data by the *BuildHeap* algorithm.

### Naive implementation

A naive implementation would be to individually run the `add` method on all the data, which would achieve the task, but be very inefficient.

Adding a single item has a time complexity of $O(\log n)$ and as a consequence, adding $n$ items has a time complexity of $O(n \log n)$. This can be optimized by the *BuildHeap* algorithm.

### The *BuildHeap* algorithm

The `BuildHeap` algorithm can be implemented in $O(n)$ time complexity. The operation is described below.

!!! Success " The `BuildHeap` algorithm"

    1. Insert the unsorted data into the backing array
    2. Starting at the lowest level, call `DownHeap` on all nodes that have children from right-to-left, i.e.
        
        Loop from index $size/2$ to index $1$. Index $size/2$ has the last element that has children, and therefore must be the last element that can be down heaped (or swapped with it's child/children). Leaf nodes already form valid one-element SubHeaps, so they don't need to be looked at.

        Pseudocode is given below. It's assumed that the backing array already contains the input array, possibly in unsorted order.

        ```java
        // Loop from index size/2 (last node w. children) to index 1 (root)
        for (int i = size/2; i > 0; i--) {
            // Call DownHeap method, which forms a valid SubHeap with index i as root
            recursiveDownHeap(i);
        }
        ```

        A full implementation is given further down where the algorithm is included in an alternative constructor for the heap.

This will create valid SubHeaps as the algorithm progresses.
When the algorithm reach any given index, the children of that index form roots of valid SubHeaps.

The time complexity of $O(n)$ might not be immediately obvious, since we are basically calling `DownHeap` $n/2$ times.
Since `DownHeap` is an $O(\log(n))$ operation, at first glance `BuildHeap` seems to be $O(n \log(n))$.

**It can however be proven that the algorithm in fact is $O(n)$.** Without the formal proof, this can intuitively be realized by considering the cost of the individual `DownHeap` calls depending on where we currently are in the tree.

In the bottom level, `DownHeap` calls are cheap, in fact $O(1)$ at the bottom with only a single comparison/swap. On the other hand, at the global root, `DownHeap` is $O(\log n).
The amount of data down through the tree doubles at each level, i.e. increases exponentially. Due to this, most of the data is located at the bottom of the tree. Consequently, the majority of the calls to `DownHeap` are $O(1)$, or at least much cheaper than $O(\log n)$. When averaging these considerations, the resulting time complexity of the `BuildHeap` algorithm becomes constant, $O(n)$.  

!!! tip "The `DownHeap` method"
    Since the `downHeap` functionality is used for both the `remove` and `BuildHeap` operations, it would be a good idea to have a separate method for it that both can use.
    The `DownHeap` method can be implemented both recursively and iteratively.

## Finding Max Value in a Min Heap

Finding the maximum element in a Min Heap using a brute force algorithm takes $O(n)$ time, since all nodes much be checked.

However, there is a more efficient approach:

!!! success "The opposite extreme must be a leaf"

    By considering the Heap data structure, we can observe that for a given Heap, the opposite extreme must be located in a leaf node. So for a Min Heap, the maximum value must be among the leafs. This cuts the number of nodes to search in half.
    
    Why only half? Since binary heap nodes have two children, the number of nodes doubles for each new level. In general, a Heap of size $n$ has $\textrm{ceil}(n/2)$ leaf nodes.

    The implication here is that only the latter half of the backing array needs to be examined. 
    Note that a Min Heap is not meant for accessing the maximum element.

While this section has talked about Min Heaps, the same is true for minimum values in Max Heaps.

## Heaps as Priority Queues

Heaps are often used as backing structure for Priority Queues. Because of the way Heaps work, they inherently keep the priority intact as part of their order property.
This works much better than using linear data structures.

## Heap implementations

Implementations of a Min and a Max Heap are presented in the boxes below. As a simplification, it considers nodes as only having data, not as key/value pairs. The two heap types are very similar in the code. The Min Heap is the most complete implementation given, while some methods are missing from the Max Heap.

??? example "Min Heap Implementation"

    === "Java"
        ```java linenums="1"
        --8<--
        docs/datastructures/nonlinear/code/java/MinHeap.java
        --8<--
        ```

??? example "Max Heap Implementation"

    === "Java"
        ```java linenums="1"
        --8<--
        docs/datastructures/nonlinear/code/java/MaxHeap.java
        --8<--
        ```
