# Data Structures

## Array

!!! success "Array - Overview"

    * Fast data accessing by indexing in $O(1)$ time
    * Slow searching/lookup in $O(n)$ time
    * All elements must have the same type
    * Have fixed length due to contiguous, static memory allocation

    ---

    | **Operation**  | **Time complexity**  | **Comment** |
    |---|:---:|---|
    | Search | $O(n)$ | *Traverse entire array in worst-case* |
    | Access index  | $O(1)$  | *Only three arithmetic operations* |
    | Resize the array  | $O(n)$  | *Copying all elements* |

Arrays are fixed-length data structures that hold multiple entities of the same type as its elements. The elements are retrieved by using their index in the array.

- An array is statically allocated to a contiguous block of memory, i.e. the elements must be next to each other in memory.  
  Data can be stored any location within the allocated memory, it does not have to be contiguous.

- At creation, the size must be specified and remains constant. If an array has length $n$, then adding an element at index $n+1$ is not possible without creating a new array with increased allocated memory. This operation involves copying all elements from the previous array, and is therefore costly.  
  This is the biggest drawback of Arrays.

- One of the most useful characteristics of arrays is fast access to an element at an index, which takes constant time.

  - Since all elements are of the same type, they take up the same amount of bits in memory.

  - If the memory address for index 0 is known, then an elements at index $i$ can be retrieved by three simple arithmetic operations:
    $$ \mathrm{index_i \ address} = \mathrm{index_0 \ address} + i \cdot \mathrm{type \ size} $$

  This makes accessing indices in arrays a constant time operation, i.e. $O(n)$.

In Java, an Array can store both primitives and objects.

## Array List

!!! success "Array List - Overview"

    * Fast data accessing by indexing in $O(1)$ time
    * Slow searching/lookup in $O(n)$ time
    * All elements must have the same type
    * Have dynamic length from resizing the underlying Array
    * Data must be contiguous and zero-aligned (start at index 0)

    ---

    | **Operation**  | **Time complexity**  | **Comment** |
    |---|:---:|---|
    | Search | $O(n)$ | *Could potentially traverse the entire list* |
    | Access front | $O(1)$ | *Easy access to index 0* |
    | Access index | $O(1)$ | *Easy index access due to contiguous data in memory* |
    | Access back | $O(1)$ | *Easy access to back via `size` variable* |
    | Add front | $O(n)$ | *Shifts all elements one index up* |
    | Add index | $O(n)$ | *Shifts all subsequent elements one index up* |
    | Add back | amortized $O(1)$ | *On rare occasions $O(n)$ when resize is triggered* |
    | Remove front | $O(n)$ | *Shifts all elements one index down* |
    | Remove index | $O(n)$ | *Shifts all subsequent elements one index down* |
    | Remove back | $O(1)$ | *Access to back in constant time* |
    | Get size | $O(1)$ | *Provided `size` variable is continuously updated on change* |
    | Empty-check | $O(1)$ | *Provided accessing `size` is $O(1)$* |
    | Clearing | $O(1)$ | *Assign to a new empty array* |

An Array List uses an Array as its underlying data structure. Only the last two points above deviate from Arrays.

An ArrayLst is a variable-length dynamic data structure, which automatically resizes if needed.

**Adding an element** to the end of an Array List is _most_ of the time an $O(1)$ operation. More specifically, if the memory is already allocated and just needs to be "filled in" with data.  
This can be if the underlying Array has a capacity of 10, but only 7 elements are currently used. Appending an 8th element is O(1).

On the other hand, if the underlying Array is already at its capacity, appending an element becomes an $O(n)$ operation. A new underlying Array must be created and all previous elements copied to it. Copying is $O(n)$.

Using **Amortized Analysis**, we can simplify (or average) the time complexity and conclude that the **Add** operation is $O(n)$.

!!! info "Amortization"
    "Amortize" means to smear out something over a time period. It is mostly used in a financial context, e.g. for mortgages.

In Java, ArrayLists can only store objects. Primitives can be stored in an Array List by using the object implementation of it rather than the "raw" primitive.

### Implementations

The box below contains implementation examples of Array Lists. The code is mostly focused around understanding and implementing the data structure and thus do not exhibit proper error handling and response to bad user inputs.

Also, each method is coded so that it is as self-contained as possible to increase clarity. In production code, helper methods should be used to avoid repetitive code.

??? example "Array List implementations"

    === "Java"
        ```java linenums="1"
        --8<--
        docs/datastructures/linear/code/java/ArrayList.java
        --8<--
        ```

    === "Python"
        ```python linenums="1"
        # TODO:
        ```
    === "C"
        ```c
        ```

## Singly Linked List

!!! success "Singly Linked List - Overview"

    * Each element can be stored anywhere in memory (no contiguity requirement)
    * Inherent resizing at each insertion or deletion
    * Quickly add to front, add to back and remove from front 
    * All elements must have same type

    ---

    | **Operation**  | **Time complexity**  | **Comment** |
    |---|:---:|---|
    | Search | $O(n)$ | *Potentially traverses all elements* |
    | Add to front | $O(1)$ | *Create new node and point it to the existing head* |
    | Add to back | $O(1)$ | *Create new node and point existing tail to it* |
    | Remove from front | $O(1)$ | *Point existing head to `null` and move head to second node* |
    | Remove from back | $O(n)$ | *Requires access to node before last => traversal of entire list.* |
    | Add in middle | $O(n)$ |  |
    | Remove in middle | $O(n)$ |  |

    !!! note
        The time complexity table assumes that the **Linked List** internally keeps track of the variables `head`, `tail` and `size`.  
        The table comments are simplified descriptions of the methods and doesn't mention edge cases. Adding to the front of an empty list will for example require setting the tail to the new node as well. 

A **Linked List** is a truly dynamic data structure which uses pointers to refer from one _node_ to the next in the list. Thus, nodes can be stored at arbitrary locations in memory.

A **Linked List** only takes up the memory that is actually necessary and traversing the list will jump around in memory following the pointers from node to node.

In contrast, the **Array List** only simulates a dynamic data structure by continuously resizing the underlying array.

A **Linked List** does not have an index per se. Finding an element requires traversing the list nodes to search for it. Each node holds the data item and a pointer to the next node.
<!-- 
<figure>
  <img src="/datastructures/media/singly-linked-linked.svg" width="400" />
  <figcaption>TODO: ADD FIGURE.</figcaption>
</figure> -->

### Edge cases

The table below describes some common edge cases when dealing with a **Linked List**.

!!! note
    The table assumes that the **Linked List** keeps track of `head`, `tail` and `size` variables.

| **Edge case**                 | **Description**                                                                                                                                                                                         |
|-------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add** to an empty list      | Affects both `head` and `tail`                                                                                                                                                                          |
| **Remove** from an empty list | Should raise error or do nothing                                                                                                                                                                        |
| **Remove** when `size=1`      | Rendering the list empty affects both `head` and `tail` as both must be set to `null`. |

Note that there are no edge cases for adding to the front. This is because every node for a **Singly Linked List** only points forward and doesn't know what is behind it.

Removing a node in the middle of a **Singly Linked List** requires keeping track of the pointer before the current node. The pointer of the node before must be changed to `current.next` and `current.next` is afterwards set to `null` and will be garbage collected.

### Implementation

The box below contains implementation examples of Linked Lists. The code is mostly focused around understanding and implementing the data structure and thus do not exhibit proper error handling and response to bad user inputs.

??? example "Singly Linked List implementations"

    === "Java"
        ```java linenums="1"
        --8<--
        docs/datastructures/linear/code/java/SinglyLinkedList.java
        --8<--
        ```

    === "Python"
        ```python linenums="1"
        # TODO:
        ```
    === "C"
        ```c
        ```

## Doubly Linked List

A Doubly Linked List is a variation of the Singly Linked List, where each node has a both a `previous` and a `next` pointer. Naturally, it requires more memory than a Singly Linked List, but in return has operations that are much more efficient.

One of the most notable differences is that it **a Doubly Linked List improves the efficiency of removing from the back**, because the tail has easy access to the node before it. This means that all operations on the ends are constant time, which makes this data structure very useful. The Doubly Linked List comes with slightly more overhead (constant time operations though) than the Singly Linked List though, so it might only make sense for some applications.

??? example "Doubly Linked List implementations"

    === "Java"
        ```java linenums="1"
        --8<--
        docs/datastructures/linear/code/java/DoublyLinkedList.java
        --8<--
        ```

    === "Python"
        ```python linenums="1"
        # TODO:
        ```
    === "C"
        ```c
        ```

## Circularly Linked List

A **Circularly Linked List** is a variation of a Linked List. They can appear in both Singly and Doubly format.

A Circularly Linked List list  can be implemented by using a `head` or a `tail` reference. Having both doesn't add any value to the data structure. In the descriptions below it is assumed that the `head` is kept track of and no `tail` reference exists.

In a Singly Linked List, `addToFront`, `addToBack` and `removeFromFront` are all $O(1)$ operations, which can also be achieved for a Circularly Linked List with some small modifications. The modifications are necessary because the last element of the list points around to the first.

The modifications to `addToFront`, `addToBack` and `removeFromFront` are explained below. Note that removing from the back of a Circularly Linked List is an $O(n)$ is the list is Singly connected, just like for a Singly Linked List.

!!! warning "Adding to the front of a Circularly Linked List in $O(1)$ time"
    1. Create the new node without adding the new data
    2. Insert the new node at index 1 by setting
        - the new node's `next` reference to `head.next`
        - `head.next` to the new node
    3. Copy the data from `head` to the new node
    4. Insert the new data into `head`

!!! warning "Adding to the back of a Circularly Linked List in $O(1)$ time"
    1. Perform the same steps as when *Adding to front*
    2. Move the head reference to `head.next`

!!! warning "Removing from the front of a Circularly Linked List in $O(1)$ time"
    1. <TODO>
