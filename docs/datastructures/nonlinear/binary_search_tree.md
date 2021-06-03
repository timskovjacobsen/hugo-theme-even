# Binary Search Tree

## Introduction

A **Binary Search Tree (BST)** is similar to a Binary Tree, except that data is arranged in a order so searching takes $O(\log n)$ time. This means that for a given node $p$, the left child and all its ancestors all contain values that are lower than the one stores in $p$. Meanwhile, the right child and all its ancestors have values that are larger than the one in $p$.

For a Binary Search Tree

$$
\textrm{all data in left-child subtree} < \textrm{current node data} < \textrm{all data right-child subtree}
$$

## Properties

!!! note "Depth"
    The depth of a node is equal to the number of edges from the node to the root of the tree.
    The depth is measured from the the root (top) of the tree, just like measuring water depth.

    The root of the tree has a depth of $0$.

    > ***Note:** This definition of the depth property is for general trees, not just BSTs.*

!!! note "Height"
    The height of a node is equal to the number of edges on the *longest path* from the root to any leaf.
    The height is measured from the bottom of the tree, just like measuring the height of a person.

    A leaf node has a height of $0$.

    * The minimum height a tree can have is $h_{\textrm{min}} = \log(n)$

    * The maximum height a tree can have is $h_{\textrm{max}} = n-1$ (for a degenerate tree)

    > ***Note:** This definition of the height property is for general trees, not just BSTs.*


    An implementation of the `height` method for a BST is shown below.

    ```java
    // Height of a BST
    public int height(Node<T> root) {
        if (root == null) {
            return 0;
        }
        Node<T> leftChild = root.getLeft();
        Node<T> rightChild = root.getRight();
        return Math.max(height(leftChild), height(rightChild)) + 1;
    }
    ```

    The implementation could easily be extended to general trees by identifying all the children of the node. For general trees, it might be preferable to have an iterator for the children of a node. 

    ```java
    // Height of a general tree with children iterator
    public int height(Node<T> root) {
        int h = 0;
        for (Node<T> child: children(root)) {
            h = Math.max(h, height(child) + 1));
        }
        return h;
    }
    ```

    Calculating the height of a tree is an $O(n)$ time complexity operation.

A BST also has *shape* properties.

!!! note "Full Tree"
    In a **Full Tree**, all nodes that are not leaves have *exactly* $2$ children. In other words, all nodes must have exactly $0$ or $2$ children.

!!! note "Complete Tree"
    In a **Complete Tree**, all levels but the deepest is filled. If the deepest level is partly filled, it must be left-aligned. Nodes can have $0$, $1$ or $2$ children and must be filled in a top-down and left-right fashion.

!!! note "Degenerate Tree"
    In a **Degenerate Tree**, the height is equal to the number of nodes and each node has only one child. A degenerate tree is essentially a linked list. It can be created by adding ascending data to the tree.

## Efficiency Overview

| Best case for operation | Time complexity |
|-------------------------|-----------------|
| Add                     | $O(\log n)$     |
| Remove                  | $O(\log n)$     |
| Search/Contains         | $O(1)$          |

| Average case for operation | Time complexity |
|----------------------------|-----------------|
| Add                        | $O(\log n)$     |
| Remove                     | $O(\log n)$     |
| Search/Contains            | $O(\log n)$     |

| Worst case for operation | Time complexity |
|--------------------------|-----------------|
| Add                      | $O(n)$          |
| Remove                   | $O(n)$          |
| Search/Contains          | $O(n)$          |

Furthermore, all traversal algorithms, pre-, post-, in- and levelorder, have $O(n)$ time complexity as all nodes must be visited.

The space complexity for a BST is $O(n)$ since $n$ items need to be stored in memory.

!!! note
    Since a BST on average has height $h = \log n$, so many of the operations scales with the height of the tree. Only degenerate trees mess this op, since they have $h = n-1$

## BST Traversal Algorithms

Tree traversal algorithms have many applications, e.g. for accessing, adding and removing data in a tree.

All traversal algorithms have time complexity of $O(n)$ since all nodes need to be traversed.

For the algorithms explained below, consider the following Binary Search Tree.

<div class="mermaid">
%%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
graph TD;
    15((15))-->2((2));
    2((2))-->1((1));
    2((2))-->9((9));
    9((9))-->7((7));
    9((9))-->12((12));
    15((15))-->26((26));
    26((26))-->19((19));
    19((19))-->17((17));
    20((20))---fakeA;
    19((19))-->20((20));
    20((20))-->22((22));
    26((26))-->30((30));
    classDef invisNode color:transparent,fill:none,stroke:none;
    class fakeA invisNode;
    class fakeB invisNode;
    linkStyle 8 stroke:none;
</div>

Note that many of the characteristics mentioned in the following for the traversal algorithms are valid for General Trees, but stated here in the context of BSTs.

### Preorder Traversal

Preorder traversal is a *depth* search algorithm which favours returning of data near the root before data near the leaves.

Each node is processed in the order *Current-Left-Right* (**CLR**). The algorithm gets its name from the fact that the current node is treated prior to its children.

A Preorder traversal **uniquely identifies a BST** and is useful for creating exact copies of the tree.

Preorder traversal is most conveniently **implemented recursively**.

!!! example "Preorder traversal - rough implementation"

    ```python
    def preorder(node):
        if node is not None:
            yield current node data
            preorder(<left child node>)
            preorder(<right child node>)
    ```

Running the `preorder` algorithm on from the root of the BST presented above will result in the sequence $15, 2, 1, 9, 7, 12, 26, 19, 17, 20, 22, 30$.

???+ example "BST from above (repeated)"
    <div class="mermaid">
    %%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
    graph TD;
        15((15))-->2((2));
        2((2))-->1((1));
        2((2))-->9((9));
        9((9))-->7((7));
        9((9))-->12((12));
        15((15))-->26((26));
        26((26))-->19((19));
        19((19))-->17((17));
        20((20))---fakeA;
        19((19))-->20((20));
        20((20))-->22((22));
        26((26))-->30((30));
        classDef invisNode color:transparent,fill:none,stroke:none;
        class fakeA invisNode;
        class fakeB invisNode;
        linkStyle 8 stroke:none;
    </div>

!!! tip "Preorder traversal tracing by hand"
    The return order of the **Preorder** algorithm can be easily determined from a properly drawn sketch of the tree.

    1. First add a tick to the **left** side of each node
    2. Afterwards, draw a tight line counter-clockwise around the tree
    3. When a tick mark is hit by the encircling line, the node is visited

### Postorder Traversal

The Postorder traversal is a *depth* search procedure which favours the leaf nodes before the nodes closer to the root.
Postorder traversals are mostly used for removal operations, since it returns the leaf nodes first.

Each node is processed in the order *Left-Right-Current* (**LRC**). The algorithm gets its name from the fact that the current node is treated after its children.

A Postorder traversal **uniquely identifies a BST**.

Postorder traversal is most conveniently **implemented recursively**.

!!! example "Postorder traversal - rough implementation"

    ```python
    def postorder(node):
        if node is not None:
            postorder(<left child node>)
            postorder(<right child node>)
            yield current node data
    ```

Running the `postorder` algorithm from the root of the tree presented above yields the sequence $1, 7, 12, 9, 2, 17, 22, 20, 19, 30, 26, 15$.

???+ example "BST from above (repeated)"
    <div class="mermaid">
    %%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
    graph TD;
        15((15))-->2((2));
        2((2))-->1((1));
        2((2))-->9((9));
        9((9))-->7((7));
        9((9))-->12((12));
        15((15))-->26((26));
        26((26))-->19((19));
        19((19))-->17((17));
        20((20))---fakeA;
        19((19))-->20((20));
        20((20))-->22((22));
        26((26))-->30((30));
        classDef invisNode color:transparent,fill:none,stroke:none;
        class fakeA invisNode;
        class fakeB invisNode;
        linkStyle 8 stroke:none;
    </div>

!!! tip "Postorder traversal tracing by hand"
    The return order of the **Postorder** algorithm can be easily determined from a properly drawn sketch of the tree.

    1. First add a tick to the **right** side of each node
    2. Afterwards, draw a tight line counter-clockwise around the tree
    3. When a tick mark is hit by the encircling line, the node is visited

???+ example "Reconstruction of BST from Postorder sequence"
    Given a Postorder traversal sequence of $[3, 2, 1, 6, 5, 4, 9, 11, 10, 8, 7]$, the reconstructed Binary Tree looks like this:

    <div class="mermaid">
    %%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
    graph TD;
        7((7))-->4((4));
        4((4))-->1((1));
        4((4))-->5((5));
        5((5))---fakeA;
        5((5))-->6((6));
        1((1))---fakeB;
        1((1))-->2((2));
        2((2))---fakeC;
        2((2))-->3((3));
        7((7))-->8((8));
        8((8))---fakeD;
        8((8))-->10((10));
        10((10))-->9((9));
        10((10))-->11((11));
        classDef invisNode color:transparent,fill:none,stroke:none;
        class fakeA invisNode;
        class fakeB invisNode;
        class fakeC invisNode;
        class fakeD invisNode;
        class fakeE invisNode;
        class fakeF invisNode;
        linkStyle 3 stroke:none;
        linkStyle 5 stroke:none;
        linkStyle 7 stroke:none;
        linkStyle 10 stroke:none;
    </div>

    We first recall that for postorder, the node record is **LRC**, the current node is recorded last. We start from the back of the sequence and follow the steps below

    1. The root must be the last number in the sequence, we therefore draw $7$ as the root.
    
    2. The next element from the back is $8$, which is larger than $7$, we therefore draw it as the right child.
    
    3. The next element is $10$, which is larger than $8$, we add it as the right child of $8$.

    4. The next element is $11$, which is larger than $10$, we add it as the right child.

    5. The next element is $9$, which is smaller than $11$. *but*, we can't add it as $11$'s left child because it would make the subtree that has $10$ as root an invalid subtree.  
    Thus, we need to take one step back to $10$ and add the it as $10$'s left child instead.

    6. Next element is $4$, which we can't add as child to any node in the right subtree because $7$ is the root and $4 < 7$. We therefore add it as $7$'s left child.

    7. Next element is $5$, which is larger than $4$, so we add it as the right child of $4$.

    8. Next element is $6$, which is larger than $5$, so we add it as the right child of $5$.

    9. Next element is $1$, which is smaller than $4$, so we add it as the left child of $4$.
    
    10. Next element is $2$, which is larger than $1$, so we add it as the right child of $1$.

    11. Next and last element is $3$, which is larger than $2$, so we add it as the right child of $2$.
    
    And we are done.    

### Inorder Traversal

The Inorder traversal is a *depth* search approach which for BSTs **return data in sorted ascending order**. If the tree is drawn with a proper layout, the data is returns from left-to-right.

The algorithm could be altered slightly to return data in descending order, namely by traversing right-to-left rather than left-to-right.

Each node is processed in the order *Left-Current-Right* (**LCR**). This ensured ascending order as the magnitude of the values follow $L < C < R$.

An Inorder traversal **does not uniquely identify a BST**.

Inorder traversal is most conveniently **implemented recursively**.

Running the `inorder` algorithm from the root of the tree presented above yields the sequence $1, 2, 7, 9, 12, 15, 17, 19, 20, 22, 26, 30$.

???+ example "BST from above (repeated)"

    <div class="mermaid">
    %%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
    graph TD;
        15((15))-->2((2));
        2((2))-->1((1));
        2((2))-->9((9));
        9((9))-->7((7));
        9((9))-->12((12));
        15((15))-->26((26));
        26((26))-->19((19));
        19((19))-->17((17));
        20((20))---fakeA;
        19((19))-->20((20));
        20((20))-->22((22));
        26((26))-->30((30));
        classDef invisNode color:transparent,fill:none,stroke:none;
        class fakeA invisNode;
        class fakeB invisNode;
        linkStyle 8 stroke:none;
    </div>

!!! tip "Inorder traversal tracing by hand"
    The return order of the **Inorder** algorithm can be easily determined from a properly drawn sketch of the tree.

    1. First add a tick to the **bottom** side of each node
    2. Afterwards, draw a tight line counter-clockwise around the tree
    3. When a tick mark is hit by the encircling line, the node is visited

### Levelorder Traversal

Levelorder traversal is a *breath-search* approach. It returns data in a left-to-right basis at each level. It is therefore easy to spot what data will be returned for a given BST if it is drawn properly beforehand.

A Levelorder traversal **does not uniquely identify a BST**.  
This can be easily seen by considering a degenerate BST of $1, 2, 3$ and a BST where $1$ is root while $2$ and $3$ are left and right children, respectively. These are different trees but the with same levelorder sequence.

Levelorder traversal is usually **implemented iteratively** with a `while` loop. Recursion doesn't fit the breadth-search approach, since we are not dealing with subtrees, but instead levels.

Running the `levelorder` algorithm from the root of the tree presented in the beginning of the section yields the sequence $15, 2, 26, 1, 9, 19, 30, 7, 12, 17, 20, 22$.

### General Binary Trees

For a standard Binary Tree (i.e. not ordered for binary search), the tree can be uniquely identified if two traversal sequences are known and one of them is in type *Inorder*.

The *Inorder* sequences defines the order of the tree while one of the other traversal sequences define the structure of the tree.  
For BSTs, the uniqueness is more trivial as *Preorder*, *Inorder* and *Postorder* all correspond to a unique tree.

## BST Search Algorithm

| Case             | Time complexity | Comment                                                                                                        |
|------------------|:---------------:|----------------------------------------------------------------------------------------------------------------|
| **Worst case**   |     $O(n)$      | We can see this in the case of a degenerate tree (linked list-like) where no data can be cut out in the search |
| **Average case** |   $O(\log n)$   |                                                                                                                |
| **Best case**    |     $O(1)$      | If the desired data is at the root                                                                             |

**TODO: Describe and implement!**

## Adding to a BST

When adding to a BST, new nodes are always inserted at leaf nodes, which maintains the structure of the BST. The path that is travelled down the tree is dependent on the data in the root and the data to add.
We want to walk down the tree and compare to each node we hit. If the data is smaller than the current node, we move left. If it's larger, we move right.

The **procedure for adding** a node with data $d$ is

1. Compare $d$ to the data of the root $d_{\text{root}}$
    1. if $d < d_{\text{root}}$, move to the left and compare as if that node was the root
    2. if $d > d_{\text{root}}$, move to the right and compare as if that node was the root

2. When hitting a node that is `null`, add the data there

It's very easy to see that the algorithm is recursive in nature.
An implementation is given further down.

!!! note
    We assume that duplicates are not added to the BST.

### Pointer reinforcement

Implementation of the `add` method can be done with various techniques. Here, the *pointer reinforcement* technique is used. Note that this particular term is not widely used.

In pointer reinforcement, we restructure the tree by returning the the node that was just added to the tree.

The newly added node that is returned is set as the parents left/right child depending. This recurses up the tree till it gets to the root. Ones the root is reached, the tree has been restructured. It's pointers have been reinforced by a series of returned nodes travelling up the tree.

### Look-ahead method

Another technique is the *look-ahead* method, where we continuously check what lies at the next node and react to it. We never actually reach a `null`, because if we see the current node has a `null`-child, we add there.  
While the look-ahead method is easier and more intuitive to understand, coding a BST this way tends to create a lot of edge cases. Pointer reinforcement is arguably a cleaner concept, but can be hard to wrap one's head around initially.

## Removing from a BST

Implementation of removal from a BST is fairly involved.

It makes heavy use of pointer reinforcement.

**TODO: Explain the method and the cases involved. Code should be in a full BST class further down with the other methods as well.**

Removal from a BST has four cases to consider:

1. **Removing a node with no children**  
    Leaf node

2. **Removing a node one child**  
    bbb

3. **Removing a node with two children**  
    Predecessor/successor.

The [csvistool](https://csvistool.com) is a great way to visualize the removal method in various scenarios.

### Finding the predecessor/successor

Finding the predecessor of a two-child node when having that node as the current node is done like this:

1. Move to the *left* child
2. Traverse *right* until reaching a right child that is `null`
3. When the right child is `null`, the predecessor has been found

Finding the successor is like this, which is very similar:

1. Move to the *right* child
2. Traverse to the *left* until reaching a left child that is `null`
3. When the left child is `null`, the successor has been found

??? example

    The image below shows a BST after removal with the predecessor and successor strategy, respectively.

    ![remove w/ predecessor or successor](/datastructures/media/removal-predecessor-successor.png)

## BST Implementation

??? example "BST implementation"

    Implementation of a BST with the *successor* strategy for removal is presented below.

    === "Java - BST"
        ```java linenums="1"
        --8<--
        docs/datastructures/nonlinear/code/java/BST.java
        --8<--
        ```

    === "Java - BSTNode"
        ```java linenums="1"
        --8<--
        docs/datastructures/nonlinear/code/java/BSTNode.java
        --8<--
        ```

??? example "Removal with predecessor"

    The full implementation of a BST shown in the example above uses the *successor* for replacing the removed data.

    The implementation could easily be modified to use the *predecessor* instead. The method below can be used to remove the predecessor.

    === "Java - BSTNode"
        ```java linenums="1"
        private BSTNode<T> removePredecessor(BSTNode<T> current, BSTNode<T> dummy) {
            if (current.getRight() == null) {
                // Base case:
                // The predecessor has been found

                // Copy the data of the current node into the dummy node so it is retained
                dummy.setData(current.getData());

                return current.getLeft();

            } else {

                current.setRight(removePredecessor(current.getRight(), dummy));
            }
            return current;
        }
        ```


**TODO:** Include code for explaining removal with the predecessor. It was a coding question for the exam.
