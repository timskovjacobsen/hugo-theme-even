# Hash Maps

## Introduction

**Hash Maps** are look-up data structures with average $O(1)$ access time, arguably making it the most important data structure. **Hash Maps** can also be referred to as a **Hash Table**.

Hash Maps are backed by Arrays, where empty spots are kept as `null`.

Hash Maps achieve their unparalleled efficiency by using **hash functions** when storing data.

HashMaps have *average* $O(1)$ access time, but this can be worsened by hash collisions. In practical implementations, collisions are rare, making HashMaps great for dictionary-like storage containers.

## Efficiency Overview

| **Operation**                  | **Best** | **Average** |    **Worst**     | **Comment** |
|--------------------------------|:--------:|------------:|:----------------:|-------------|
| `put(key)` (closed addressing) |  $O(1)$  |      $O(1)$ |      $O(1)$      | ...         |
| `put(key)` (open addressing)   |  $O(1)$  |      $O(1)$ | $O(n)^* / O(n²)$ | ...         |
| `remove(key)`                  |  $O(1)$  |      $O(1)$ |      $O(n)$      | ...         |
| `keys()`                       |  $O(n)$  |      $O(n)$ |      $O(n)$      | ...         |
| `values()`                     |  $O(n)$  |      $O(n)$ |      $O(n)$      | ...         |

The $^*$ denotes *amortized* time complexity. Without resizing, the worst time complexity is $O(1)$. In rare cases, we need to resize the backing table, which is an $O(n²)$ operation in worst case. More specifically, we might need to traverse all keys, $O(n)$, to realize that a resize is needed and then resize and re-hash, $O(n)$, all the entries into the new table.

## Hash Functions

Hash Functions must

* Map keys to integers, regardless of the data type of the key
* Exhibit the feature that equal inputs must have equal hashes

In mathematical terms, a Hash Function is written as

$$ h: K \rightarrow \mathbb{Z} $$

which signifies a mapping is taking place with keys as input and integers as output.

The integer output ends up being the index for data storage in the backing table (array). This means that any key type must be converted to an integer by the hash function, also strings, immutable objects etc.

!!! warning "Constraint: Equal inputs => equal hashes"
    The most important constraint for hash functions is that if we have two input keys that are equal, there resulting hash must be equal as well.

    In other terms, if we have two input keys

    $$ k_1=k_2 $$

    then it must be true that

    $$ h(k_1)=h(k_2) $$

    In principle, a hash function that produces the same output regardless of input is a valid hash function, although not a very practical one.

While the above criterion must be met, **good hash functions also strive to towards two outputs being equal only if their two corresponding inputs are equal.**

Theoretically, it is not possible to completely ensure this, which brings us to the topic of *hash collisions*.

## Compression Functions

Examples of Compression Functions are

!!! example "Modulo Compression"
    Creates evenly distributed data across the backing table and places no restriction on the length.

    The index after compression $i_c$ is computed as

    $$ i_c = \text{abs}( \ h(k) \ \% \ N \ ) $$

    where $h(k)$ is the directly computed hash (i.e. the "raw index") and $N$ is the capacity of the backing table. The absolute value is necessary to catch special cases to avoid overflow.

    Suppose a hash is calculated to be $h(k)=64$ and $N=50$. The index after compression then becomes $i_c=14$.

!!! note
    When performing compression, it is important to consider collisions.

## Hash Map Properties

### Table Size

The  table size is simply the length of the table that is the underlying data structure, also called the backing table. The backing table is an array.

### Load Factor

The load factor of a Hash Map is found as the size divided by the capacity of the backing table

$$ \text{load factor} = \frac{\text{size}}{N} $$

The load factor is naturally heavily dependant on the choice of hash collision strategy.

A large load factor increases the risk of collisions. A a small load factor requires much more memory as the backing table has many elements (where most are `null`).

## Hash Collisions

TODO:

!!! Note "The Pigeonhole Principle"
    TODO:

## Collision Handling Strategies

When we do have collisions, we need a strategy for how to handle them. I.e. how to find a way to store the entry we are trying to add even though the index that is actually hashes to is taken.

!!! danger "Collisions lower the efficiency"

    Collisions will hurt the $O(1)$ efficiency of the HashMap, as additional computations are needed to store the entry. Lookups will be slower because the key is not stored directly in the index it initially hashes to. We need to search further in order to find the entry.

While it's important to have a solid collision strategy for handling collisions when they do occur, it is much more important to avoid them all together.

!!! success "Good hash functions means fewer collisions"

    Avoiding collisions as much as possible is done by having a well-designed hash function and carefully choose the sizing of the backing table. When and how to resize of backing table is also important.

We generally have two types of collision handling strategies, **Closed Addressing** and **Open Addressing**. These types are explained in the following.

## Closed Addressing

TODO: A DIAGRAM WOULD BE GOOD HERE.

When using a **Closed Addressing** collision handling strategy, an entry is sure to be located in the HashMap at the compressed index that was actually computed. Thus, even though we have a collision, we will still store the entry at that index.

### External Chaining

Uses an **Array of Linked Lists** as backing data structure. The main idea is that each index can store multiple elements, which allows for the compression function to output identical indices.

This method effectively chains together elements that have the same hashcode.

Each Array element points to the head of the Linked List that is stored at each index.
In most implementations, **inserting a key/value pair into the Array corresponds to adding to the front of the Linked List**. Recall that adding to the front of a Linked List is $O(1)$ time complexity.

In this implementation, we will never run out of indices to put new data, since the Linked Lists each can have infinite length.

## Open Addressing

In an **Open Addressing** strategy for collision handling, the final index of the entry in the HashMap is different from the initially computed compressed index. Thus, when collisions occur, we change the index until we find an index that is free.

Open addressing can be implemented with *probing* strategies for collision handling. There are different ways of probing the alternative index when a collision occurs. Some are described further down.

Another form of Open Addressing is *double hashing* which is also treated in the following.

When using open addressing, DEL Markers must be used when removing entries. Setting removed entries to `null` will break future lookup operations since indices are not guaranteed to be at their initially computed index.

### Linear Probing

The **linear probing** collision strategy increments the index by one in case of a collision when performing `put` operations.

If the incremented index is occupied as well, we simply increment by one again. The process repeats until we find an index that is un-occupied, i.e. has `null` as entry, or we have probed $N$ times. Here, $N$ is the length of the backing table.

!!! note
    When incrementing the index, it must support wrap-around (by use of the modulo operator) to ensure that we don't probe indices that are larger than the length of the backing table.

If we have probed $N$ times and still not found an empty index, we must resize the backing table. This should never happen though, as a `MAX_LOAD_FACTOR < 1` should be defined to resize the backing table before it could happen.

**Linear probing** is arguably the simplest open addressing approach, but has the drawback of creating contiguous blocks of data, since the index is incremented by one.
We might end up with a very long block of data, which will cause lookup times to be slower.

Imagine we want to look up a *key* which hashes to index $i$. If that index is already taken by a different key that hashed to the same compressed index, we must keep looking. I.e. increment the index by one and look there.

If we hit a contiguous block of $k$ entries, where the one we search for is the last, it will take $k$ probes to find it. A situation like this is not good since it hurts the efficiency of lookups, which should be as close to $O(1)$ as possible.

The multiple-probing scenario can be explained by the Python code below. The `table` variable represents the backing table (array) that is the underlying data container.

```python linenums="1"
def get(key):
    """Find a key in a HashMap that uses linear probing."""

    i = compressed_hash(key)
    if table[i] != null:

        while table[i].key != key:
            # The entry was not at this index
            # Advance to next index with support for with wrap-around
            i = (i + 1) % N

        # If we reach this code, we found the entry, return the value
        return table[i].value

    else:
        # The compressed index is null, which means key can't be in the HashMap
        raise NoSuchElementException()
```

!!! warning "DEL Markers"
    The importance of using DEL Markers rather than setting removed entries back to `null` becomes evident from the `else` clause in the code above.

    Suppose we had a contiguous block of 5 entries, that all hashed to the same index initially.
    If we remove the 4th index in the block and set it to `null`, the 5th entry will erroneously not be found by the `get` method. We will hit a `null` value at the 4th index in the block and conclude that the key is not in the HashMap.

    Instead, we should define a DEL markers (sometimes called *tombstones*) as a kind of flag to denote that an entry has been removed. A DEL Marker can e.g. be `False`
    
    In order for our code to work properly, we must rely on the fact that **if an index is `null`, no key in the map will hash to that value**.

    It should be noted that DEL Markers reduce the efficiency of a HashMap. As a result, **we prefer to not transfer DEL Markers to the new backing table when we do resizing**.

    > DEL Markers are not specific to linked probing, but are useful for all probing methods.

!!! example

    When using DEL Markers and removed elements are marked with a flag, we can utilize the space they occupy again for later insertions.

    Suppose we have the backing table below

    <figure>
    <img src="/datastructures/media/HashMapInsertionAtDELMarker.png" width="700" />
    </figure>

    If we want to add $40$ (assume both key and value are 40) to HashMap, we use these steps:

    1. Compute initial index $i = 40 \ \% \ 13 = 1$. Index is already taken, continue the linear probing.
    2. Compute next index $i = i + 1 = 2$, which is also taken
    3. Compute next index $i = i + 1 = 3$, which is also occupied, but by a DEL Marker (the string "<deleted>" in this case).  
    We now know that we can place $40$ here. However, we need to check if $40$ is already in the list before we can add it. Thus

        * Store the info that we can add $40$ at index 3 if we do not find  duplicate

    4. Continue probing until we hit a `null` index (or have probed $N$ times). If we find $40$ somewhere, replace the existing entry with $40$ and return the existing value.

    5. If we do not find a duplicate, add $40$ to index 3.

    If we find more than one DEL Marker, we put it in the first one.

    When removing and creating a DEL Marker the deleted key is stored so that a potential re-adding of the same key can be done more efficiently. The efficiency is gained by not having to traverse further down the contiguous block of entries to check if the key is already present in the map. Re-added kan simply be inserted at the DEL Marker where they were deleted from.

!!! note "Probing $N$ times *can* happen"
    At first glance, it might not be possible to reach a `probe_count == N` as long as we set a reasonable `MAX_LOAD_FACTOR`. BUT, this can in fact happen if we use DEL Markers and don't include them in the `MAX_LOAD_FACTOR`.

    If we have a backing table with $N=10$ and do the following

    * add $0$, then delete it
    * add $1$, then delete it
    * ...
    * add $9$, then delete it

    We will end up with a backing table that is full of DEL Markers, not `null` values. 

    The code above assumes that we can never have a situation where the backing array is completely full. There must be at least one `null` index. This restriction can be dictated by including DEL Markers in the `MAX_LOAD_FACTOR`, which will ensure resize before the array is full.

    In case the array *is* full and doesn't contain the key, the `while` clause becomes an infinite loop.
    If we needed to in the specific implementation, we could keep track of the number of times we have probed. If we have probed $N =$`table.length` times and not found the key, we conclude it's not there.

    > *Note: The code is not actually tested!*

### Quadratic Probing

The **quadratic probing** collision strategy computes a new index by use of a quadratic probing function, defined as

$$
i = (h² + i_0) \ \% \ N
$$

where

* $i$ is the new probing index
* $h$ is the *probe count*
* $i_0$ is the initially computed index
* $N$ is the length of the backing table

Thus, the index increases quadratically due to the $h²$ term.

!!! success "Avoiding contiguous blocks"
    Quadratic probing overcomes the issue of linear probing where probed data ends up in contiguous blocks, making lookups inefficient. Instead, the collisions happen as a *secondary clustering*, whereas for linear probing, the collisions occur as *primary clustering*.

!!! example

    Consider the following scenario for adding data to a HashMap that uses quadratic probing:

    * We compute an initial index $i_0 = 3$ and have a HashMap with a table length of $10$
    * The index is occupied, so we probe first time with $h=1$ and get a new index $i = (1² + 3) \ \% \ 10 = 4$
    * Index $4$ is also occupied, so we probe the second time and get a new index $i = (2² + 3) \ \% \ 10 = 7$
    * Index $7$ is also occupied, so we probe the third time and get a new index $i = (3² + 3) \ \% \ 10 = 2$

    If we have probed $N$ times and not found a valid index, we resize the backing table, re-hash all existing entries (except potential DEL Markers) and try probing again. Resizing is not guaranteed to solve the problem though.

From the example above, it can be realized that we can get into a scenario where infinite probing occurs. Theoretically, there are no bounds for the number of nested resizes. In most practical cases, infinite probing is unlikely to happen though.
Maps *can* be constructed in clever ways to avoid this, but such ways are not explained here.

### Double Hashing

In double hashing, we perform a second hashing in case of collisions. Every key has a different rate of change as the probe count increases, as double hashing gives us a different factor to multiply with the probe count for each key. This helps to split up clusters in the data stored in the HashMap.

$$
i = (i_0 + h c) \ \% \ N
$$

where

* $i$ is the new probing index
* $i_0$ is the initially computed index
* $h$ is the probe count
* $c$ is the index that results from a second hash function
* $N$ is the length of the backing table

!!! note

    Notice in the double hashing formula that a $c=1$ is equivalent to linear probing. Linear probing is in fact a special case of double hashing.

    It's important to note that **quadratic probing is not a special case of double hashing**. For quadratic probing, the formula above would have $c=h$ so the parenthesis becomes $(i_0 + h²)$. This would make the second function not a true hash functions, as it would require both the key **and the probe count as input. True hash functions have only the key as the input.

The procedure is as follows:

1. Compute the first hash $h(k)$ to get the initial index $i_0$  

    * If index $i_0$ is open, store $k$ there and terminate
    * If index $i_0$ is taken (collision), go to Step 2.

2. Compute the second hash $d(k)$ to get the probing constant $c$  
    $$
    d(k) = q - h(k) \ \% \ q
    $$

    where $q$ is a prime number fulfilling $q<N$.  
    Having $q$ be prime minimizes the risk of creating clusters, which decrease the efficiency of the data structure.

!!! example

    We have a HashMap with a backing table with capacity $N=7$. The main hash function simply maps integer keys to their won integers. Compression is done by the modulo function.

    The second hash function is $d(k) = 5 - k \ \% \ 5$.

    Print the first 7 indices that are probed assuming that collisions occur for all probings.

    ---

    ```python linenums="1"
    def i(k, N):
        # Compute the initial index from the main hash function
        # that maps integer keys to their integers
        # and the modulo compression function
        i0 = k % N

        # Compute the second hash function
        d = 5 - k % 5
        
        print(f"Initial index:  {i0}")

        for i in [1, 2, 3, 4, 5, 6]:
            # Print the next probe index
            print(f"Probe #{i}:       {(i0 + i*d % N)}")
    ```

    Running `#!python i(92, 7)` prints

    ```python
    """
    Initial index:  1
    Probe #1:       4
    Probe #2:       0
    Probe #3:       3
    Probe #4:       6
    Probe #5:       2
    Probe #6:       5
    """
    ```

The second hash function should be sufficiently dissimilar to the main hash function and should have a characteristic that splits out the keys to minimize data clusters.

In addition, the second hash function must never output $0$ as it would make the $hc$ term $0$. Returned numbers should not have common factors with the table length $N$ to avoid missing indices when probing.

## Collision Handling in Practice

In practice, **external chaining** and **linear probing** are often used due to their simplicity.
The incrementing used in linear probing is at a low level a very fast operation since. Faster than e.g. multiplication.

A drawback to quadratic probing is that it is quite tricky to implement. Because of this, quadratic probing is not often used in practice.

Just like quadratic probing, double hashing is not trivial to implement. It's therefore not seen in practice as  much as external chaining or linear probing. The infinite probing problem is also a concern for practical use cases, where it in instead better to improve the hash function than handling collisions.  
It should also be noted that even though both quadratic probing and double hashing leads to fewer probings, the operations to compute the next index are more expensive than the simple increment used in linear probing.

* Java implements as HashMap with a variation of external chaining, where a chain is replaced by balanced BST if its length becomes too large. The load factor is 0.75.
* Python's dictionary uses a variation of linear probing and a max load factor of $2/3$.
* C++ uses external chaining with a max load factor of $1.0$. Recall that the load factor can actually exceed 1.0 with external chaining as each index can have many entries.

## Hash Map Implementation

### External chaining

Implementation of a Hash Map using External Chaining as collision strategy is presented in the box below.

??? example "Implementation"

    === "Java - HashMap"
        ```java linenums="1"
        --8<--
        docs/datastructures/nonlinear/code/java/ExternalChainingHashMap.java
        --8<--
        ```

    === "Java - HashMapEntry"
        ```java linenums="1"
        --8<--
        docs/datastructures/nonlinear/code/java/ExternalChainingMapEntry.java
        --8<--
        ```
