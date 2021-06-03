# AVL Trees

## Introduction

**AVL Trees** are a subclass of Binary Search Trees an exhibit *self-balancing* behavior. They inherit all properties of the BST

* Shape and order properties
* Max 2 children per node
* All data in the left subtree of a node is smaller than the node
* All data in the right subtree of a node is larger than the node

On top, they add the balancing property.

**AVL** is an abbreviation from its creators, **A**delson-**V**elsky and **L**andis.

AVL Trees address the biggest drawback that standard BST have, namely that they can become degenerate, leading to bad efficiency.  
Thus, the worst case time complexity for `add`, `remove` and `search` is $O(\log n)$, where it is $O(n)$ for standard BSTs. This stems from the maximum height of an AVL tree being $\log(n)$.

!!! note
    A *degenerate* resembles a linked list, i.e. a tree with no branches and with its height equal to the number of nodes $n=h$.

In order to maintain the **balanced** property, methods that add or remove node must have means to re-create balance after their operations. The mechanism performed after add or remove operations is often referred to as a **rotation**.

!!! note "Red-Black trees"
    Another data structure that is closely related to AVL trees is *Red-Black trees*. They have the same characteristics and is essentially a different way to get implement a similar behavior.

    AVL trees tends to be faster for lookups, while Red-Black trees are faster for add/remove operations. This fact should be considered when choosing which to go with in an application.

## Time complexity

Both `add`, `remove` and `search` are $O(\log n)$ time complexity for AVL Trees.
Finding the height of a node is constant $O(1)$, since we are storing the height of each node as data inside the nodes.

## Height and balance of trees

The balance of a node is defined as the difference in the height of its children.

The **height** of a node $k$, denoted $h(k)$, is computed from the height of its children $k_{\mathrm{left}}$ and $k_{\mathrm{right}}$

$$
h(k) = \textbf{max}(h(k_{\mathrm{left}}), h(k_{\mathrm{right}})) + 1
$$

Owing of the self-balancing property, the maximum height of an AVL tree is $h_{\text{max}} = \log(n)$.

The **Balance Factor** $BF$ of the tree which has $k$ as its root is

$$
BF(k) = h(k_{\mathrm{left}}) - h(k_{\mathrm{right}})
$$

This means that for a given tree, if the left subtree has a height $h=2$, and the right subtree has a $h=3$, the balance factor if $-1$.

From these definitions, we can conclude the following:

* A node with $|BF| > 1$ is unbalanced
* A node with $BF \text{ in } \{-1, 0, 1\}$, the node is balanced

!!! note
    A node with a balance factor that is off by $1$ is still considered balanced, since it is too hard in practice to keep perfect balance, i.e. $BF=0$. It would require adding/removing nodes in pairs, one in each subtree which is impractical. If we start off with perfect balance, there is simply no way to add/remove a single node and maintain balance. This is why we allow nodes with $BF=-1$ and $BF=1$ to be balanced.

In case of unbalance, the balance factor reveals in which direction the unbalance lies. If the unbalance is to the left or right, we say the node is *Left-Heavy* or *Right-Heavy*, respectively.

!!! tip "Info stored in nodes"
    The height and balance factor for each node can be stored inside the node to achieve $O(1)$ lookup times as opposed to $O(n)$ if it's computed each time. Of course, the caveat is more memory consumption as more data need to be stored.

    **The height of a `null` node (children of leaf nodes) is $-1$. The height of a leaf node is $0$.**

!!! note
    In some implementations, the balance factor is calculated as the height of the right child minus the height of the left child.

### Updating the height of a node

When updating the height of a node, the node's entire subtree must up-to-date, since the correctness of the height and balance calculations rely on the height of the children.
**Thus, when updating the height of a tree, the order in which the update takes place is important.**

### Balanced trees

!!! example "A balanced tree"
    An example of a balanced tree is given below.

    <div class="mermaid">
    %%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
    graph TD;
        7((7))-->4((4));
        4((4))-->2((2));
        2((2))-->1((1));
        2((2))-->3((3));
        4((4))-->5((5));
        7((7))-->10((10));
        10((10))-->8((8));
        10((10))---fakeA;
        classDef invisNode color:transparent,fill:none,stroke:none;
        class fakeA invisNode;
        linkStyle 7 stroke:none;
    </div>

    With practice, it can fairly quickly be realized that this tree is balanced, but let's go through the derivation step by step.

    Starting from the root at node $7$ and looking at the **left subtree**, we get a recursive stack represented by the calculations:

    |        | Formula                                                | Calculation                | Height |
    |--------|--------------------------------------------------------|----------------------------|--------|
    | $h(3)$ | $\textbf{max}(h(\mathtt{null}), h(\mathtt{null})) + 1$ | $\textbf{max}(-1, -1) + 1$ | $0$    |
    | $h(1)$ | $\textbf{max}(h(\mathtt{null}), h(\mathtt{null})) + 1$ | $\textbf{max}(-1, -1) + 1$ | $0$    |
    | $h(5)$ | $\textbf{max}(h(\mathtt{null}), h(\mathtt{null})) + 1$ | $\textbf{max}(-1, -1) + 1$ | $0$    |
    | $h(2)$ | $\textbf{max}(h(1), h(3)) + 1$                         | $\textbf{max}(0, 0) + 1$   | $1$    |
    | $h(4)$ | $\textbf{max}(h(2), h(5)) + 1$                         | $\textbf{max}(1, 0) + 1$   | $2$    |

    **The height of the left subtree is $2$.**

    Note that we start from the bottom of the tree and work our way up because of recursive call is dependent on the child nodes.

    We continue to the **right subtree**:

    |        | Height formula                                         | Calculation                | Height |
    |--------|--------------------------------------------------------|----------------------------|--------|
    | $h(8)$ | $\textbf{max}(h(\mathtt{null}), h(\mathtt{null})) + 1$ | $\textbf{max}(-1, -1) + 1$ | $0$    |
    | $h(10)$ | $\textbf{max}(h(8), h(\mathtt{null})) + 1$ | $\textbf{max}(0, -1) + 1$ | $1$    |

    **The root of the right subtree is $1$.**

    The balance factor of the entire tree is then found as

    $$
    BF(7) = h(k_{\mathrm{left}}) - h(k_{\mathrm{right}}) = 2 - 1 = 1
    $$

    Since we allow balanced trees to be off-by-one, the tree is balanced.

    Note that all other nodes than the root also have their children's height not differ by more than one, which is a requirement.

!!! example "An unbalanced tree"
    An example of an unbalanced tree is given below.

    <div class="mermaid">
    %%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
    graph TD;
        31((31))---fakeA;
        31((31))-->37((37));
        37((37))-->34((34));
        37((37))-->42((42));
        classDef invisNode color:transparent,fill:none,stroke:none;
        class fakeA invisNode;
        linkStyle 0 stroke:none;
    </div>

    The tree has a balance factor of

    $$
    BF(31) = h(k_{\mathrm{left}}) - h(k_{\mathrm{right}}) = 2 - 0 = 2
    $$

    Note that if the tree had been inverted, i.e. if the left and the right subtrees had been switched, the balance factor would be $-2$.

## Rotations

Rotations are used when adding or removing from an AVL tree in cases where the operation renders the tree unbalanced.
Rotations change the height of nodes without disrupting the order property of the tree.

The table below gives an overview of the rotation types. The rotation shapes depict only the simplest cases which might be part of larger trees in more advanced scenarios.

<img src="/datastructures/media/rotation_types.png" alt="rotation types" class="center" style="width:550px">

### Single left rotation

Consider the tree below for a demo of a left rotation.

<div class="mermaid">
%%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
graph TD;
    A((A))-->1(subtree 1);
    A((A))-->B((B));
    B((B))-->2(subtree 2);
    B((B))-->C((C));
    C((C))-->3(subtree 3);
    C((C))-->4(subtree 4);
    classDef SubTree color:grey,fill:none,stroke:grey;
    class 1 SubTree;
    class 2 SubTree;
    class 3 SubTree;
    class 4 SubTree;
</div>

Suppose that the tree with Node A as root is unbalanced and right-heavy, with $BF<-1$ (this is not evident from the figure though, since we do not know what is in the subtrees). We want to balance the tree.

The algorithm for a left rotation is presented below in pseudocode.

```python
def left_rotation(A: Node):
    1. B = A.right_child
    2. A.right_child = B.left_child
    3. B.left_child = A
    4. update_height_and_balance_factor(A)
    5. update_height_and_balance_factor(B)
    6. return B
```

Explanation of the steps

1. This first steps seems weird, because we are essentially defining **B** to be the equal to itself, i.e. **A**'s right child. It is necessary to avoid "losing" the reference to **B** during Step 2, where we set **A**'s right child to something else.

2. We set **A**'s right child to be the former left child of **B**. We can do this safely without disrupting the order property since we know that any children of **B** must  be larger than **A**.

    ???+ example "Diagram after Step 2"
        <div class="mermaid">
        %%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
        graph TD;
            A((A))-->1(subtree 1);
            A((A))-->2(subtree 2);
            B((B))-->2(subtree 2);
            B((B))-->C((C));
            C((C))-->3(subtree 3);
            C((C))-->4(subtree 4);
            classDef SubTree color:grey,fill:none,stroke:grey;
            class 1 SubTree;
            class 2 SubTree;
            class 3 SubTree;
            class 4 SubTree;
        </div>

3. This is the step that actually performs the rotation that Step 1 and 2 prepared for. We set **B**'s left child to be **A**, which rotates **B** up as the new root and moves **A** down.  
    The rotation is now done and the tree is balanced, but we still need to update the height and balance factor store in each node.

4. We update the height and balance factor of **A**. This must be done before updating **B** to ensure that **B**'s children are all up-to-date. Only then can we safely update **B**.

5. We update **B** in a similar way to **A**.

6. We return **B** as the new root to "reinforce" the pointers. Other nodes do not need to be returned as they already had their pointers updated in Step 1 and 2.

The resulting balanced tree becomes

<div class="mermaid">
%%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
graph TD;
    B((B))-->A((A));
    A((A))-->1(subtree 1);
    A((A))-->2(subtree 2);
    B((B))-->C((C));
    C((C))-->3(subtree 3);
    C((C))-->4(subtree 4);
    classDef SubTree color:grey,fill:none,stroke:grey;
    class 1 SubTree;
    class 2 SubTree;
    class 3 SubTree;
    class 4 SubTree;
</div>

### Single right rotation

A right rotation is similar, but opposite from the left rotation described above. Therefore, this description is abbreviated compared to the detailed example for the left rotation.

The pseudocode for a right rotation is:

```python
def right_rotation(C: Node):
    1. B = C.left_child
    2. C.left_child = B.right_child
    3. B.right_child = C
    4. update_height_and_balance_factor(C)
    5. update_height_and_balance_factor(B)
    6. return B
```

???+ example "Example diagrams"
    Suppose the initial tree is

    <div class="mermaid">
    %%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
    graph TD;
        C((C))-->B((B));
        B((B))-->A((A));
        A((A))-->O(subtree 1);
        A((A))-->P(subtree 2);
        B((B))-->Q(subtree 3);
        C((C))-->R(subtree 4);
        classDef SubTree color:grey,fill:none,stroke:grey;
        class O SubTree;
        class P SubTree;
        class Q SubTree;
        class R SubTree;
    </div>

    The tree after a right rotation will be

    <div class="mermaid">
    %%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
    graph TD;
        B((B))-->A((A));
        B((B))-->C((C));
        A((A))-->O(subtree 1);
        A((A))-->P(subtree 2);
        C((C))-->R(subtree 4);
        C((C))-->Q(subtree 3);
        classDef SubTree color:grey,fill:none,stroke:grey;
        class O SubTree;
        class P SubTree;
        class Q SubTree;
        class R SubTree;
    </div>

### Double rotations

Double rotations occur when the tree has a "bend" in its shape.

The table of the rotation overview is repeated here for convenience. The bends are depicted in the second and fourth row, where double rotations are needed.

<img src="/datastructures/media/rotation_types.png" alt="rotation types" class="center" style="width:550px">

!!! example "Right-left rotation"

    Suppose we start with the tree below. The shape of the nodes of interest is seen to resemble shape from the table that requires a **right-left rotation**.

    <div class="mermaid">
    %%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
    graph TD;
        10((10))-->0(subtree 1);
        10((10))-->14((14));
        14((14))-->100(subtree 2);
        12((12))-->2(subtree 3);
        14((14))-->12((12));
        12((12))-->3(subtree 4);
        classDef SubTree color:grey,fill:none,stroke:grey;
        class 0 SubTree;
        class 100 SubTree;
        class 2 SubTree;
        class 3 SubTree;
    </div>

    We first make the right rotation by "pushing" node $12$ up in-between node $10$ and $14$ and relink the pointers as necessary. This is a *right-rotation*, since we are moving node $14$ in the right direction around node $12$.
    We can see that this is an intermediate step in the algorithm because we don't have a valid BST.

    <div class="mermaid">
    %%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
    graph TD;
        10((10))-->0(subtree 1);
        10((10))-->12((12));
        12((12))-->2(subtree 3);
        14((14))-->1(subtree 2);
        14((14))-->3(subtree 4);
        12((12))-->14((14));
        classDef SubTree color:grey,fill:none,stroke:grey;
        class 0 SubTree;
        class 1 SubTree;
        class 2 SubTree;
        class 3 SubTree;
    </div>

    Afterwards, we perform a left rotation as described in the single left rotation section.

    <div class="mermaid">
    %%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
    graph TD;
        12((12))-->10((10));
        12((12))-->14((14));
        10((10))-->0(subtree 1);
        10((10))-->1(subtree 2);
        14((14))-->2(subtree 3);
        14((14))-->3(subtree 4);
        classDef SubTree color:grey,fill:none,stroke:grey;
        class 0 SubTree;
        class 1 SubTree;
        class 2 SubTree;
        class 3 SubTree;
    </div>

!!! example "Left-right rotation in a larger tree"

    This example demonstrates a **left-right-rotation**.

    The numbers in the upper left of each node is the height of each node. The upper right denotes the balance factor.  
    These numbers are not reliable in step 2 and 3, since the images are taken in the middle of the update procedure, indicated by the red circles.

    1. The initial tree, where we would like to add $12$.

        <img src="/datastructures/media/left-right-rotation-part1.png" alt="left-right-rotation" class="center" style="width:300px">

    2. The tree after $12$ is inserted acc. to standard BST insertion algorithm.

        <img src="/datastructures/media/left-right-rotation-part2.png" alt="left-right-rotation" class="center" style="width:300px">

        The shape of the tree indicates that we need a double rotation because it has a bend in the nodes that we are traversing on the way back. **The bend resembles the second row from the table above, calling for a left-right rotation**. 

    3. The tree after the first rotation, i.e. the **left rotation**. We have rotated node $10$ in left-direction about node $15$ and changed the pointers of nodes $20$, $15$ and $10$.  
    The first part of this operation can be viewed as if $15$ is "pushed" up in between $10$ and $20$, while $12$ is left hanging.

        <img src="/datastructures/media/left-right-rotation-part3.png" alt="left-right-rotation" class="center" style="width:300px">

    4. The tree after the second rotation, i.e. the **right rotation**, where $20$ is rotation in right-direction about $15$.

        <img src="/datastructures/media/left-right-rotation-part4.png" alt="left-right-rotation" class="center" style="width:300px">

    The example images are screenshots from the [csvistool](https://csvistool.com)

### Adding and removing with rotations

Adding or removing of a node from an AVL tree is done by

1. Add/remove the node by the same algorithm as used for BST's
2. Re-balancing the tree by the appropriate rotation method

!!! note
    Rotations can occur in any area of a subtree, even if the addition or removal of a node is far away from the area where the rotation is necessary.

!!! tip "Adding doesn't change the height of unbalanced trees"
    Adding to an **unbalanced** AVL tree does not change the height of the tree. If the tree is unbalanced, the node can always be added in the spot that makes it completely balanced ($BF=1$).
    The same cannot be said about removing from an AVL tree.

For an `add` operation, even in the worst case, only one rotation is needed. This rotation might be a double-rotation. Thus, adding is an constant time $O(1)$ operation.

For a `remove` operation, we need to perform $\log(n)$ rotations in the worst case. Thus, removing is generally an $O(\log n)$ operation.

### Adding steps

1. Perform addition with the standard BST algorithm
2. Traverse up the tree until the root is reached, update height and balance factor on the way
    1. After each update, check if the node is unbalanced
    2. If the node is unbalanced
        1. Determine the rotation needed to balance the subtree rooted at the current node.
        2. Perform the rotation
        3. Update height and balance factors (the rotation changes the heights and balance factors)
    3. If the node is balanced, continue

3. Return the new root of the tree

In general, if a rotation occurs $k$ levels down in an AVL tree, the number of updates is $k$ as well, since each level need to be updated.

!!! question
    Since we do not store the parent of a given node as node data, how can we traverse upward up the tree towards the root, when there is no `node.parent` attribute to call?

    **Moving up the tree from a given node is done via the rotations since we always return the new root of the subtree that was rotated.** This effectively traverses upward the tree.

    If rotation is not necessary, the root of the new subtree is is simply the current node, which is returned. When adding a node, we it will always end up as a leaf. Since rotation is not necessary, the first return will always be the added node itself.

    This can be seen in action in [this visualization tool](https://csvistool.com/AVL).

### Removing steps

For the removal of a node, there are three cases that might be present:

1. The node to remove has two children

    In this case, we must replace the removed node's data with either the predecessor or the successor, depending on the implementation choice.

    Afterwards, the tree must be rebalanced by updating the height and balance factors of nodes and performing the necessary rotations.

2. The node to remove has only one child, either the left or the right

    In this case, we can set the parent's child to it's previous grandchild, effectively skipping over the current node. Nothing will point to it anymore.

3. The node to remove has no children

    In this case, we are removing a leaf node. Thus, we can simply make the parent the new leaf node by setting its child to `null`.

The source code implementation further down is good to look at for increasing the understanding of the removal procedure.

## Implementation

???+ example "Code"

    The code below shows an implementation of an AVL Tree. The removal method uses the successor as replacement for the removed data. 

    === "Java"
    ```java linenums="1"
    --8<--
    docs/datastructures/nonlinear/code/java/AVL.java
    --8<--
    ```
