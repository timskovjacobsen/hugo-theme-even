# Graph Search Algorithms

Both **Depth-First-Search (DFS)** and **Breath-First-Search (BFS)** are template graph search algorithms. They form general strategies for traversing a graph and are rarely useful in their generic forms. When DFS or BFS are used as means to perform some additional logic while traversing the graph, they become very useful.

Both DFS and BFS are *exhaustive* algorithms, meaning that any graph vertex that is reachable will be visited before the algorithm terminates.

DFS and BFS are explained in more detail further down.

## Depth-First-Search

Depth-First-Search (DFS) traverses a graph from a given start vertex in a way where each branch of the graph is explored as far down as possible before going up again and exploring new branches.
The algorithm can be implemented by either iteration or recursion. Recursion provides the shortest and arguably the most readable implementation.

The recursive implementation utilizes the recursive stack, while the iterative implementation uses a stack in the algorithm's code to keep track of traversals.

Pre-, post- and in-order traversals of a tree data structure are examples of DFS algorithms.

The algorithm needs

1. A start vertex
2. A Set of visited vertices
3. A Stack

For the recursive implementation, the stack will be the recursive stack. For the iterative version, the stack is defined in the  algorithm.
Note that the two implementations may not visit nodes in the same order. If e.g. visiting in alphabetical order is used, the vertices would have to be pushed to the stack in reverse alphabetical order for them to get popped in order. So it depends on the implementation choices.

```java
public void DFS(Graph G, Vertex u, Set visited) {
    // Mark u as visited
    visited.push(u);

    // 
    for (Vertex w : u.getAdjacentVertices()) {
        if !(visited.contains(w)) {
            DFS(G, u, visited)
        }
    }
    // When we reach this line, we have visited all vertices adjacent to u.
    // Thus, we do not make any new recursive calls. If there are still 
    // recursive calls left in the stack, it means that one or more vertices
    // still have branches that have yet to be explored. If there are no 
    // stacks left, we have visited all vertices and the algorithm terminates. 
}
```

The algorithm visits all vertices of the graph, but not necessarily all edges. In fact, there will be edges left untrodden unless the graph is degenerate.

!!! note
    A branch that is visited during a DFS need not be visually "linear". We traverse the path that the graph connectivity gives us. When we hit a dead end, we go back until we reach a node where we haven't yet explored all paths.

Time complexity of DFS:

$$O(|V|+|E|)$$

## Breadth-First-Search

Breadth-First-Search (BFS) traverses a graph from a given start vertex by visiting all vertices that are one edge away from the start vertex. Then it visits all vertices that are two edges away from the start vertex and so on.

Levelorder traversal of a tree data structure is an example of a BFS algorithm.

The algorithm needs

1. A start vertex
2. A Queue
3. A Set of visited vertices

Time complexity of BFS:

$$O(|V|+|E|)$$

### Diagramming BFS

??? example

    We want to traverse the following graph with BFS staring from vertex E.

    <figure>
    <img src="/datastructures/media/BFS_graph_example.png" width="400" />
    </figure>

    *Image from [GTx Algorithm's course](https://learning.edx.org/course/course-v1:GTx+CS1332xIV+2T2020/home#block-v1:GTx+CS1332xIV+2T2020+type@chapter+block@7d42194fd57a450f857d9049ba3c014c).*

    With a small graph, keeping track of the order can be done without writing anything down. But for a graph of this size, it can be a bit hard to keep track.

    BFS traversal of the graph can be represented like this:

    ```shell
    1   2   3   4   5 
    E 
        B   C   A   O
                D 
        F   G   K 
            J   M   P   L
                    Q
                N   R
        H
        I  
    ``` 
    Typing it out like this while visually traversing the graph can help a lot.

    Alternatively in table form:

    | 1   | 2   | 3   | 4   | 5   | 6   |
    | --- | --- | --- | --- | --- | --- |
    | E   |     |     |     |     |     |
    |     | B   | C   | A   | O   |     |
    |     |     |     | D   |     |     |
    |     | F   | G   | K   |     |     |
    |     |     | J   | M   | P   | L   |
    |     |     |     |     | Q   |     |
    |     |     |     | N   | R   |     |
    |     | H   |     |     |     |     |
    |     | I   |     |     |     |     |

    Reading the table from column to column gives the order of vertices visited:

        E B F H I C G J A D K M N O P Q R L

## Implementation

???+ example "Implementation"

    === "Java"
        ```java linenums="1"
        --8<--
        docs/algorithms/graph-algorithms/code/java/src/GraphSearchAlgorithms.java
        --8<--
        ```

## DFS vs. BFS

Since both DFS and BSF have the same worst case time complexity, it cannot be explicitly stated which one is the best to use. It depends on the specific problem and there may be large performance differences between the two for the same problem.

Choose BFS when:

* The vertex to search for is located close to the start vertex
* The graph is not very wide
* The tree is very deep

Choose DFS when:

* The vertex to search for is located far away from the start
* We want to explore which move is better from the current state of a game (e.g. an AI for chess). The graph should in most cases have a level cap in such scenarios.
* The graph is very wide

If the graph is extremely wide, BFS might take up a lot of memory since all vertices adjacent to to the current vertex will be queued up.

If a tree is deep, choosing DFS can "waste time" by staying on one branch for a long time. BFS will likely be best to widen the search.

BFS can be used to find the shortest path between two vertices in a unweighted graph. If the graph is weighted, we need more specialized algorithms, like Dijkstra's algorithm.

### Examples

??? example

    We want to traverse the graph below with both DFS and BFS and report the vertices in the order they are visited. We break ties by using alphabetical order.

    <figure style="box-shadow:none">
    <img src="/datastructures/nonlinear/media/example-graph1.svg" width="200" />
    <figcaption>Example graph.</figcaption>
    </figure>

    Note that the graph is presented as a tree, but might as well be visualized with the vertices in random locations with crossing edges.

    * **DFS:** A B C D E F H G

    * **BFS:** A B G C H D E F
