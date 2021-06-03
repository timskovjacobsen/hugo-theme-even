# (2-4) Trees

* All (2-4) tress are balanced
* All (2-4) tress are full
* All leaf nodes must be at the same level in the tree
* Every node containing $n$ data entries must have either $0$ or $n+1$ children, where $n \ \text{in} \ \{1, 2, 3\}$
* A node can at most have $3$ data entries, adding a fourth causes overflow and triggers a *promotion*
* Each node stores data in ascending order

* A (2-4) tree of $n$ levels can at maximum store $\sum\limits^{n}_{i=0}(4^{i-1})$ nodes
* A (2-4) tree of $n$ levels can at maximum store $3 \sum\limits^{n}_{i=0}(4^{i-1})$ data entries

!!! example "Node and data counting"
    The maximum number of nodes in a (2-4) tree of $4$ levels is

    $$\text{max nodes} = \sum\limits^{n}_{i=0}(4^{i-1}) = 4^{1-1} + 4^{2-1} + 4^{3-1} + 4^{4-1}= 85 \ \text{nodes}$$

    Since $3$ data entries can reside in each node, the maximum number of data entries are

    $$\text{max data entries} = 3 \cdot (4^{1-1} + 4^{2-1} + 4^{3-1} + 4^{4-1}) = 255 \ \text{entries}$$

    The computation can be done with these two simple Python functions.

    ```python
    def max_nodes(levels):
        a = 0
        for level in range(levels):
            a += 4 ** level
        return a


    def max_data_entries(levels):
        return 3 * max_nodes(levels)
    ```

## Complexity overview

Adding, Removing and Searching are all worst case: $O(\log n)$. This stems from a (2-4) tree always being balanced by design with a height of $\log (n)$.

## Adding

### Promotion

Promotions occur when an adding operation leads to **overflow** in a node, i.e. if a node has more than the allowed three three data entries. A promotion pushes a data entry up and splits the current node.

When performing a promotion, an implementation choice is made. We can promote either the the second or the third data entry in the node which are the middle two data values. So if we have a node that is overflown with data $[6,10,18,26]$, we can promote either $10$ or $18$. An example of this is shown further down.

In the worst case, the number of promotions can be equal to the height of the tree, i.e. $\log(n)$ promotions.

### Adding examples

**In the examples below, we choose to always promote the third data.**

???+ example "Adding with promotion - Create new node"

    We start with the root node $[6,10,18]$, then add $26$, which causes overflow. The overflown node marked with red border now looks like this:

    <div class="mermaid">
        %%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
        graph TD;
            R(root):::rootNode --- N[6, 10, 18, 26]:::invalidNode;
        classDef rootNode color:orange,fill:none,stroke:orange;
        classDef invalidNode stroke:red;
        linkStyle 0 stroke:orange;
    </div>

    We promote with the third data entry. Thus, we "push" the third data $18$ up to be the new root and split the current node in two.

    <div class="mermaid">
        %%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
        graph TD;
            R(root):::rootNode --- N1[18];
            N1 --> N2[6, 10];
            N1 --> N3[26];
        classDef rootNode color:orange,fill:none,stroke:orange;
        linkStyle 0 stroke:orange;
    </div>

???+ example "Adding with promotion - Promote directly to parent"

    We have just added $14$ in the diagram below, which makes the root's rightmost child overflow, as marked with red border.

    <div class="mermaid">
      %%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
      graph TD;
          R(root):::rootNode --- N1[18];
          N1 --> N2[6, 10, 12, 14]:::invalidNode;
          N1 --> N3[26];
      classDef rootNode color:orange,fill:none,stroke:orange;
      classDef invalidNode stroke:red;
      linkStyle 0 stroke:orange;
    </div>

    In this case, we can simply promote the third data into the parent, pushing $12$ up.

    <div class="mermaid">
      %%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
      graph TD;
          R(root):::rootNode --- N1[12, 18];
          N1 --> N2[6, 10];
          N1 --> N3[14]
          N1 --> N4[26];
      classDef rootNode color:orange,fill:none,stroke:orange;
      linkStyle 0 stroke:orange;
    </div>

    We have increased the parent's data count by 1 while increasing its number of children by 1 as well. Thus, the shape property is preserved.

???+ example "Adding with promotion - Promote to parent with propagation"

    We have just added $28$ and the (2-4) tree looks like this, with an invalid node. 

    <div class="mermaid">
      %%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
      graph TD;
          R(root):::rootNode --- N1[12, 18, 40];
          N1 --> N2[6, 10];
          N1 --> N3[14]
          N1 --> N4[26, 28, 29, 32]:::invalidNode;
          N1 --> N5[43];
      classDef rootNode color:orange,fill:none,stroke:orange;
      classDef invalidNode stroke:red;
      linkStyle 0 stroke:orange;
    </div>

    We promote the third data $29$ in the invalid node.

    **But**, the parent already has four children and consequently three data entries, so it is maxed out. We still perform the promotion, which will then propagate to trigger a new promotion.

    The first step is the first promotion of $29$ into the parent, splitting the node it just left.

    <div class="mermaid">
      %%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
      graph TD;
          R(root):::rootNode --- N1[12, 18, 29, 40]:::invalidNode;
          N1 --> N2[6, 10];
          N1 --> N3[14]
          N1 --> N4[26, 28];
          N1 --> N5[32];
          N1 --> N6[43];
      classDef rootNode color:orange,fill:none,stroke:orange;
      classDef invalidNode stroke:red;
      linkStyle 0 stroke:orange;
    </div>

    The parent is now invalid, which is dealt with by a new promotion of its third data, which happens to be $29$ again.

    <div class="mermaid">
      %%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
      graph TD;
          R(root):::rootNode --- N1[29];
          N1 --> N2[12, 18];
          N1 --> N3[40];
          N2 --> N4[6, 10];
          N2 --> N5[14]
          N2 --> N6[26, 28];
          N3 --> N7[32];
          N3 --> N8[43];
      classDef rootNode color:orange,fill:none,stroke:orange;
      linkStyle 0 stroke:orange;
    </div>

    No more promotions are needed and the adding procedure is done.

## Removing

Just like for other tree types, the removal procedure is harder to understand and implement than the adding procedure.

During a removal operation, the **Node Property** and **Order Property** of the (2-4) tree could potentially be violated in intermediate steps. The **Shape Property** however, is always maintained. In other words, the tree will remain balanced at any given time.

### Transfer

Transfer solves the underflow problem immediately.

A transfer is the easiest and most efficient way to resolve an underflow. A transfer is applicable when one of the *immediate* siblings has more than one data in it. This means that sibling can give data away and still have at least one.

The process is to bring a data from the parent down into the current node and move a data from the sibling that can spare it up into the parent.

A transfer is a constant time operation, i.e. $O(1)$ time complexity.

!!! note
    * Note that the criteria for a transfer being viable is independent of the parent node. It only depends on the amount of data in the immediate siblings.

    * The sibling to transfer data from must be an *immediate* sibling, i.e. next to the node that receives the data. 

### Fusion

Fusion solves the underflow problem by removing data from the parent and fusing/merging two children. Consequently, the fusion operation could in the worst case propagate re-arrangements upwards in the tree.

This is in contrast to scenarios where the transfer operation can be used as it resolves the re-arrangements in one go.

Just like a transfer, a single fusion is a constant time operation, i.e. $O(1)$ time complexity.
If a fusion operation triggers propagations, we just add more operations, but the single fusion operation remains constant.

### Removal pseudocode

The removal procedure can roughly be summarized by the following pseudocode.

1. If data is in a leaf node, remove the node
2. Otherwise, replace with predecessor or successor
    1. If the replacement caused underflow, check if a transfer can be made. If it can, perform it and the removal **procedure is done**.
    2. If a transfer is not viable, perform a fusion and repeat the step above checking for underflow

### Flowchart of removal procedure

The flowchart below describes the removal operation from a (2-4) tree.

![(2-4) tree removal flowchart](/datastructures/media/2-4_tree_removal_flowchart.png)

!!! note
    In the chart above, "$2+$" data in a sibling means "two or more"

### Removal examples

???+ example "Removal with propagation"

    Consider the (2-4) tree below. We want to remove $35$, which is marked with red.

    <div class="mermaid">
        %%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
        graph TD;
            50[50]-->13[13];
            13-->0[0];
            13---fakeA;
            13-->35[35]:::nodeToRemove;
            50-->75[75 &nbsp &nbsp 80];
            75-->63[63];
            75-->78[78];
            75-->85[85];
            classDef invisNode color:transparent,fill:none,stroke:none;
            class fakeA invisNode;
            linkStyle 2 stroke:none;
            classDef nodeToRemove fill:#f96;
    </div>

    * **Start: Remove the data**  
      Following the removal flowchart above, we first remove the data and replace with the predecessor. Since the node with $35$ is a leaf node, there is no predecessor and the node becomes empty.

    <div class="mermaid">
        %%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
        graph TD;
            50[50]-->13[13];
            13-->0[0];
            13---fakeA;
            13-->35[ ]:::nodeInQuestion;
            50-->75[75 &nbsp &nbsp 80];
            75-->63[63];
            75-->78[78];
            75-->85[85];
            classDef invisNode color:transparent,fill:none,stroke:none;
            class fakeA invisNode;
            linkStyle 2 stroke:none;
            classDef nodeInQuestion stroke:limegreen;
    </div>

    * **Is the node that lost data invalid now?**  
      **Yes** it is, since the node with $13$ must have either $0$ or $2$ children. *The node in question is marked with a green border*.

    * **Is it the root?**  
      No

    * **Does a sibling have 2+ data?**  
      No, we have only one sibling and it has $1$ data in it. Thus, we can't make a *transfer* and must perform a *fusion*.

    * **Fusion: bring parent down & fuse with sibling**  
      We bring $13$ down into the empty node and fuse it with its sibling, the node with $0$.

    <div class="mermaid">
        %%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
        graph TD;
            50[50]-->A[ ]:::nodeInQuestion;
            A-->0[0 &nbsp &nbsp 13];
            50-->75[75 &nbsp &nbsp 80];
            75-->63[63];
            75-->78[78];
            75-->85[85];
            classDef nodeInQuestion stroke:limegreen;
    </div>

    * **Is the node that lost data invalid now?**  
      Yes, the parent node that had $13$ is now invalid (empty).

    * **Is it the root?**  
      No

    * **Does a sibling have 2+ data?**  
      Yes, the sibling node has data $75, 80$. Thus, we can perform a transfer.

    * **Transfer: bring the parent down & sibling data up**  
      $50$ moves from the root down to the empty node where $13$ were. $75$ moves up as the new root. The resulting (2-4) tree is

    <div class="mermaid">
        %%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
        graph TD;
            75[75]-->50[50];
            50-->0[0 &nbsp &nbsp 13];
            75-->80[80];
            80-->63[63];
            80-->78[78];
            80-->85[85];
    </div>

      **However, this is not yet a valid tree**, since the node with $50$ does not have $0$ or $n+1$ children. Its sibling with $80$ is also invalid in the same way.  
      We can also conclude that $63$ is now in an incorrect position, since it is in the right subtree even though it is smaller than the root.

      We are still missing one piece that the diagram above neglect to include in its algorithm. Namely to transfer the node $63$ to be the rightmost child of the $50$ node. This is simply done by removing the pointer to it from node $80$ and creating a pointer to it from node $50$.

      **The final tree is**

      <div class="mermaid">
          %%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
          graph TD;
              75[75]-->50[50];
              50-->0[0 &nbsp &nbsp 13];
              75-->80[80];
              50-->63[63];
              80-->78[78];
              80-->85[85];
      </div>

## Diagramming of (2-4) trees

!!! note
    When an overflow occurs from adding data to a node, an implementation choice must be made for how to handle it. It's common to promote either the 2nd or 3rd data in the node to the parent. If no parent exists, we  create a node for it.
