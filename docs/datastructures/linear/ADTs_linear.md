# Linear Abstract Data Types (ADT)

## Introduction

Abstract Data Types (ADT) are overall descriptions of how a user interacts with an
underlying data structure. They can be though of as *cookiecutters* that describe behaviors and operations (methods) that must be implemented for the data type to fulfill its purpose.

### Backing data structures

Behind every ADT is a low level data structure which can be refereed to as the *backing data structure*. The user operations are translated to operations on this data structure, which then returns (or modifies) the ADT.

Since only the user-facing operations are accounted for by ADT's the choice for which backing data structure to use is up to the developer. Naturally, each low level data structure had strength and weaknesses which should be taken into account.  
For example, both an **Array** and a **Linked List** can be used to create a **List ADT**.

## List

A **List** is a sequence of elements. Each element has an index from which it can be accessed.

It can be implemented by the lower level data structures **Array List** or **Linked List**, where Linked List has multiple variations.

| Method                       | Description                                                                              |
|------------------------------|------------------------------------------------------------------------------------------|
| `addAtIndex(index, element)` | *Add `data` at `index`. Add 1 to the index of all elements with $i >$ `index`*           |
| `removeAtIndex(index)`       | *Remove  element at `index`. Subtract 1 to the index of all elements with $i >$ `index`* |
| `get(index)`                 | *Return the element at `index`*                                                          |
| `isEmpty()`                  | *Return `True` is the list is empty, `False` otherwise*                                  |
| `clear()`                    | *Remove all elements from the list*                                                      |
| `size()`                     | *Return the size (or length) of the list*                                                |

!!! note ""
    As an implication of the some of the above  behaviors, like adding and removing, the index of a specific element might change throughout the lifetime of the List.

## Stack

A **Stack ADT** is so-called **L**ast **I**n **F**irst **O**ut (**LIFO**) data structure. It plays a significant role on many areas of computer science and has many applications in the field. It is designed to be a very simple and lightweight data container.

Stacks only allow the user to deal with the "top" element, either by removing and returning it, peeking at it or adding on top of it.

A Stack must implement the `pop` and `push` methods. These and some other commonly implemented methods are shown in the table below.

| Method           | Description                                                             |
|------------------|-------------------------------------------------------------------------|
| `push(x)`        | Add `x` to the top of the Stack                                         |
| `pop()`          | Remove and return the element that was last pushed to the Stack         |
| `peek()`/`top()` | Return, but don't remove, the element that was last pushed to the Stack |
| `size()`         | The return the number of elements in the Stack                          |
| `isEmpty()`      | Return `true` is the Stack is empty, `false` otherwise                  |
| `clear()`        | Clear the Stack and set `size=0`                                        |

### Unsupported operations for Stacks

Since only the top element is user-accessible, **the following operations are not supported by stacks:**

* <s>Searching</s>
* <s>Accessing an arbitrary index</s>
* <s>Adding or removing from an arbitrary index</s>

### Stack implementations

An implementation of a Stack is most often done with a Linked List or an Array as the backing data structure. The table below summarizes the time complexities of the two choices.

| Operation    | Linked List  | Array List       |
|--------------|--------------|------------------|
| `pop`        | $O(1)$       | $O(1)$           |
| `push`       | $O(1)$       | amortized $O(1)$ |
| `peek`/`top` | $O(1)$       | $O(1)$           |
| `clear`      | $O(1)$       | $O(1)$           |
| `isEmpty`    | $O(1)$       | $O(1)$           |
| Top of Stack | head (front) | size-1 (back)    |

The amortized $O(1)$ notation is due to occasional resizing, which is an $O(n)$ operation for Array Lists. Note that a Linked List inherently is dynamically resizable in constant time.

#### Stack with Singly Linked List

A Singly Linked List is the best backing data structure to use for a Stack.

* The top of the stack is modelled to be the `head`, making `pop` and `push` constant operations
* Operations only deal with the head of the Linked List acting as the top of the Stack and thus, a `tail` variable is unnecessary

??? example "Stack backed by Linked List"

    === "Java"
        ```java
        --8<--
        docs/datastructures/code/java/LinkedStack.java
        --8<--
        ```

    === "Python"
        ```python
        # TODO:
        ```
    === "C"
        ```c
        ```

!!! note
    A Doubly Linked List could be used to implement a Stack as well. However, there is nothing to gain from it as all operations will still be $O(1)$. Because Doubly Linked Lists have slightly more memory and time overhead compared to Singly Linked Lists, they are not typically used for implementing Stacks.

#### Stack with Array List

* Use the back of the Array for pop and push operation, which then run in amortized $O(1)$ time
* When clearing the Stack, clear the Array List by creating a new Array List
    * *This is more efficient than setting all elements to `null` and `size=0` ($O(n)$) or just setting `size=0` and let the data sit. In the latter case, garbage collection can't remove it (at least in Java)*

??? example "Stack backed by Array"

    === "Java"
        ```java
        --8<--
        docs/datastructures/code/java/ArrayStack.java
        --8<--
        ```

    === "Python"
        ```python
        # TODO:
        ```
    === "C"
        ```c
        ```

### Use-cases for Stacks

* Undo-button in browsers
* Method calls for program execution (the call stack), e.g. for recursive calls that are waiting to be executed

## Queue

A **Queue ADT** is so-called **F**irst **I**n **F**irst **O**ut (**FIFO**) data structure. It is closely related to the Stack ADT and similarly has a lot of applications in computer science.

A Queue must implement the `enqueue` and `dequeue` methods. These and some other commonly implemented methods are shown in the table below.

| Method           | Description                                                                             |
|------------------|-----------------------------------------------------------------------------------------|
| `enqueue(x)`     | Add `x` to the back of the Queue                                                        |
| `dequeue()`      | Remove and return the element that is first in the Queue                                |
| `peek()`/`top()` | Return, but don't remove, the element that was first enqueued and will be dequeued next |
| `size()`         | The return the number of elements in the Queue                                          |
| `isEmpty()`      | Return `true` is the Queue is empty, `false` otherwise                                  |
| `clear()`        | Clear the Queue and set `size=0`                                                        |

Queues can be efficiently implemented with either an Array or a Singly Linked List as backing data structure. A table of the time efficiencies for those two options are shown in the table below.

| Operation  | Array            | Singly Linked List |
|------------|------------------|--------------------|
| `enqueue`  | amortized $O(1)$ | $O(1)$             |
| `dequeue`  | $O(1)$           | $O(1)$             |
| `first`    | $O(1)$           | $O(1)$             |
| `size`     | $O(1)$           | $O(1)$             |
| `isEmpty`  | $O(1)$           | $O(1)$             |
| `clear`    | $O(1)$           | $O(1)$             |
| Resize     | $O(n)$           | $O(1)$             |
| Enqueue at | back             | back               |
| Dequeue at | front            | front              |

### Unsupported operations for Queues

As for Stacks, the following operations are not supported for Queues:

* <s>Searching</s>
* <s>Accessing an arbitrary index</s>
* <s>Adding or removing from an arbitrary index</s>

### Queue with Singly Linked List

When using a Singly Linked List, we `enqueue` from the tail and `dequeue` from the head.

<figure>
  <img src="/datastructures/media/queue-linked-list.svg" width="500" />
  <figcaption>Justification why <code>enqueuing</code> at the tail and <code>dequeuing</code> from the head of a Singly Linked List is most efficient.</figcaption>
</figure>

### Queue with Array

Using an Array for implementing a Queue directly is not very efficient, because adding to the front shifts all elements one spot, i.e. $O(n)$.

An ArrayQueue implementation can be made very efficiently though by letting the Array *wrap around* itself in a Circular Array form.  

The wrap around means that even though the array has elements, they do not necessarily begin at index 0.

!!! note
    The Queue can not be implemented efficiently by means of an Array List because their indices must be zero-aligned. Therefore, a standard Array is used to implement the Queue. Dynamic resizing is still possible, it just has to be added to the Array internally.

An ArrayQueue without wrap-around is shown in the figure below.

<figure>
  <img src="/datastructures/media/queue-array.svg" width="400" />
  <figcaption>QueueArray without wrap-around.</figcaption>
</figure>

In the basic case the front index is incremented by one when dequeuing. This works if there is no wrap-around in the array, like shown in the figure above.  
To adapt the front index dynamically while allowing wrap-arounds, we use the modulo operator.

Thus, dequeuing an element will shift the front index $f$ by the expression

$$ f_{\mathrm{dequeue}} = (f_{\mathrm{current}} + 1) \ \% \ N $$

where $N$ is the capacity of the Array.

Let's try to use this expression for the array in the figure above. The array has capacity `N=9` and its front index is currently 2. A `dequeue` operation will result in a new front index of

$$ f_{\mathrm{dequeue}} = (2+1) \ \% \ 9 = 3 $$

Which makes sense when looking at the figure.

If we look at a case where the Array wraps around

<figure>
  <img src="/datastructures/media/queue-array-wraparound.svg" width="400" />
  <figcaption>QueueArray with wrap-around.</figcaption>
</figure>

In this case, dequeueing will make the new front index $f=(7+1) \ \% \ 9 = 8$, which again fits the figure.

The *edge case* for the wrap-around is when the front is at index 8 and the next dequeuing operation will make the new front index be 0:

$$f_{\mathrm{next}}=(8+1) \ \% \ 9 = 0$$

Enqueuing a new element to the back requires knowing the index of the free spot just behind the back. **This index to enqueue at will be**:

$$ b_{\mathrm{enqueue}}=(f_{\mathrm{current}}+\mathrm{size}) \ \% \ N $$

where  $\mathrm{size}$ is the current number of elements in the array (prior to enqueuing) and $N$ is the capacity.

Following the example figure above, this is seen to match

$$ b_{\mathrm{enqueue}} = (7+4) \ \% \ 9 = 2 $$

When implementing a Queue with an Array, the we could as well have enqueued at the front and dequeued from the back with same efficiency. However, this would be counter-intuitive compared to how a real-life queue actually works where you enter from the back and get served when you are at the front. Also, it would make the Queue go in the opposite direction compared to the Linked List implementation.

### Use cases for Queues

* Customers waiting in line, e.g. for shopping sites, travel or event bookings, phone support

* Printer jobs

## Priority Queue

TODO: Not a linear data structure. Treat in other section.

## Deque

**Deque** is short for *Double Ended Queue*. This Abstract Data Type which can be efficiently implemented by
