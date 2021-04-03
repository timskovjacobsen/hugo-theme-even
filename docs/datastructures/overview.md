# Data Structures - Overview

## Linear Data Structures

The time complexities for different linear data structures are given in the table below.

The time complexities given assume that a `size` variable is kept in sync with the state of the data structure and that the Singly and Doubly Linked Lists have a `tail` variable.

| Operation         | Array List | Singly Linked List | Doubly Linked List |
|-------------------|------------|--------------------|--------------------|
| Search            | $O(n)$     | $O(n)$             | $O(n)$             |
| Access front      | $O(1)$     | $O(1)$             | $O(1)$             |
| Access index      | $O(1)$     | $O(1)$             | $O(n)$             |
| Access back       | $O(1)$     | $O(1)$             | $O(1)$             |
| Add to front      | $O(n)$     | $O(1)$             | $O(1)$             |
| Add to index      | $O(n)$     | $O(n)$             | $O(n)$             |
| Add to back       | $O(1)^*$    | $O(1)$             | $O(1)$             |
| Remove from front | $O(n)$     | $O(1)$             | $O(1)$             |
| Remove from index | $O(n)$     | $O(n)$             | $O(n)$             |
| Remove from back  | $O(1)$     | $O(n)$             | $O(1)$             |
| Get size          | $O(1)$     | $O(1)$             | $O(1)$             |
| Empty check       | $O(1)$     | $O(1)$             | $O(1)$             |
| Clearing          | $O(1)$     | $O(1)$             | $O(1)$             |

!!! Note
    The $O(1)^*$ for *Adding to the back* of an Array List denotes the *amortized* time complexity. In short, this means that it will be $O(1)$ in the majority of cases. In rare cases when the Array List needs resizing, the complexity is $O(n)$ as a new larger array is created and the existing elements copied over. Thus, $O(n)$ is technically the correct complexity.

    More info can be found in the [Array List](../datastructures_linear/#array-list) page.

## Linear Abstract Data Types (ADTs)

