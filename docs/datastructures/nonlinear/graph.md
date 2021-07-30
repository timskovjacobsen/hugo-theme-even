# Graph

* **TODO: NEED AN EXAMPLE OF DIJKSTRA'S ALGORITHM TO WALK THROUGH. USE AN EXAMPLE FROM THE COMPREHENSION QUESTIONS OR THE PRACTICE EXAM.**

* **TODO: MOVE ALL GRAPH ALGORITHMS OUT OF THIS FILE. SHOULD BE THEIR OWN ALGORITHMS SECTIONS. THERE ARE SO MANY GRAPH ALGORITHS THAT THEY SHOULD BE A TOPIC BY THEMSELVES. THIS FILE SHOULD ONLY DESCRIBE THE GRAPH ADT AND MAYBE SHOW DFS AND BFS. THEY CAN BE REFERRED IN FROM ELSEWHERE.**

## Storing Information in Graphs

The data contained in a graph must be held in an auxillary data structure. The choice of data structure is important to fit with the use case, as it will play a role for the efficiency of graph operations.

**TODO: Show a figure for demonstrating the examples below.**

## Graph Terminology

### Graph types

**Directed graph (Digraph)**
:   directed...

    A directed graph can at maximum have $n(n-1)$ edges. Each vertex can have $n-1$ edges and we have $n$ edges in total.

**Undirected graph**
:   undirected...

    The maximum number of edges in an undirected graph is
    
    $$ \binom n2 = \dfrac{n(n-1)}{2} $$
    
    This is half the amount of a directed graph.

**Simple graph**
:   A graph is simple if it has no self-loops or parallel edges.

    A simple graph space complexity $|E| = O(|V|²)$

**Multigraph**
:   A graph is a multigraph if is has self-loops or parallel edges.

****

### Connectedness

**Path**
:   path...

**Trail**
:   trail...

**Walk**
:   aa...

### Paths and its variants

**Path**
:   path...

**Trail**
:   trail...

**Walk**
:   aa...

**Cycle**
:   A *cycle* is

    Cycles in undirected graphs can be detected by checking if an adjacent vertex was not added because it is already present in the visited set. Note that cycles cannot be just a single edge, so we have to neglect the vertex we just came from.

**Circuit**
:   A *circuit* is

### Other terminology

**Self-loop**
:   A vertex $A$ has a self-loop if it has an edge that has $A$ as both its start and end vertex.

**Parallel edges**
:   sss

### Adjacency Matrix

* Works well when the graph is dense
* Is very inefficient for adding to the graph, since the matrix must be resized

Space complexity: $O(|V|²)$

!!!note "Number of element in a set (cardinality)"
    Since the vertices and edges of a graph are defined as *sets*, we use the symbol $|X|$ for defining the *cardinality* of $X$. *Cardinality* is the number of elements in a set.

### Adjacency List

* Works well when the graph is sparse

| Vertex | Edges  |
| ------ | ------ |
| $A$    | $e, g$ |
| $B$    | $e, f$ |
| ...    | ...    |

Space complexity: $O(|V| + |E|)$

Where $|x|$ denotes the order of $x$.

In an undirected graph, the adjacency matrix is symmetric. For a directed graph it is not symmetric.

### Edge List

## Graph Algorithms

Graph algorithms are are huge topic and there are many different algorithms for many possible scenarios. The sections below cover only the most common ones.

### Search algorithms

Both Depth-First-Search (DFS) and Breath-First-Search (BFS) are template graph search algorithms. They form general strategies for traversing a graph and are rarely useful in their generic forms. When DFS or BFS are used as means to perform some additional logic while traversing the graph, they become very useful.

Both DFS and BFS are *exhaustive* algorithms, meaning that any graph vertex that is reachable will be visited before the algorithm terminates.

DFS and BFS are explained in more detail further down.

## Depth-First-Search (DFS)

DFS traverses a graph from a given start vertex in a way where each branch of the graph is explored as far down as possible before going up again and exploring new branches.
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

## Breadth-First-Search (BFS)

BFS traverses a graph from a given start vertex by visiting all vertices that are one edge away from the start vertex. Then it visits all vertices that are two edges away from the start vertex and so on.

Levelorder traversal of a tree data structure is an example of a BFS algorithm.

The algorithm needs

1. A start vertex
2. A Queue
3. A Set of visited vertices

Time complexity of BFS:

$$O(|V|+|E|)$$

### Diagramming BFS

!!! example

    We want to traverse the following graph with BFS staring from vertex E.

    <figure>
    <img src="/datastructures/media/BFS_graph_example.png" width="400" />
    </figure>

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

If the tree is deep, choosing DFS can "waste time" by staying on one branch for a long time. BFS will likely be best to widen the search.

BFS can be used to find the shortest path between two vertices in a unweighted graph. If the graph is weighted, we need more specialized algorithms, like Dijkstra's algorithm.

## Dijkstra's Shortest Path Algorithm

Dijkstra's algorithm is greedy algorithm that finds the shortest path from one single source vertex to all other vertices for weighted graphs, where all weights are non-negative.

In most scenarios, the weights can be viewed as distances.

!!! tip
    Dijkstra's shorted path algorithm work for both **weighted**, **unweighted**, **connected**, **disconnected**, **directed** and **undirected** graphs.

!!! note
    It's possible to use Dijkstra's algorithm for finding the shortest path in an unweighted graph, but using BFS is both simpler and more efficient.

Dijkstra's algorithm requires

* **Source vertex**
* **A minimum Priority Queue**

    For storing the cumulative weights/distances from the source vertex to each vertex. The use of a minimum Priority Queue helps us choose the next vertex in the path as the one with the lowest cumulative distance.

    Using a Priority Queue, we don't need to consider the fact that a vertex can be traveled to from multiple paths. We will always be guaranteed to dequeue the vertex with the currently shortest path automatically.

* **A Set of visited vertices**

    A Set of the vertices that are currently included in the shortest path.

* **A Map**

    For storing the graph vertices as keys and currently known shortest distance from the start vertex as values. Initially, all distances are $\infty$ and are updated when each vertex is visited with the shortest distance to that vertex.

    !!! tip
        Dijkstra's algorithm inherently ensures that is a vertex has been visited, it will have been through the shortest possible path.

### Algorithm

!!! example "Implementation"

    **NOTE: This the algorithm implementation below has not been tested yet! Might not be correct!**

    ```python
    def dijkstra(graph: Graph, start: Vertex):

        # Initialize the necessary data structures
        visited = set()
        distance_map = dict{}
        priority_queue = PriorityQueue()

        # Insert infinity as initial values for all vertex distances 
        for vt in graph:
            distance_map[vt] = math.inf
        
        # Enqueue the start vertex, which naturally has a distance of 0 to itself
        priority_queue.enqueue((start, 0))
        
        # Loop while the PQ has items and all vertices are not visited
        while not priority_queue.is_empty() and len(visited) < len(graph):

            # Extract the next vertex from the PQ shortest distance 
            vertex, dist = priority_queue.dequeue()

            if vertex not in visited:
                # Mark vertex as visited
                visited.add(vertex)

                # Update the distance map w/ the vertex's minimum cumulative distance
                distance_map[vertex] = dist

                # Find neighbors that are not visited
                # NOTE: Vertex.get_neighbors is a set as well
                unvisited_neighbors = vertex.get_neighbors().difference(visited)

                # Enqueue all neighbors that are not yet in the visited set
                for neighbor_vertex, neighbor_dist in unvisited_neighbors:
                    # Enqueue the neighbor vertex
                    # The distance becomes the vertex distance plus the neighbors 
                    priority_queue.enqueue(neighbor_vertex, dist + neighbor_dist)
    ```

!!! tip "Extracting the shortest path"
    The shortest path given as a sequence of vertices is incrementally built by the algorithm. If we want to report the vertices to traverse, we must make sure that each time we update a distance for a vertex, we also report which node we came from. Since the node that we came from now provides the shortest path to the updated node, we know that travelling that direction will (currently) lead us on the shortest path back towards the start vertex. Note that the direction might change during the algorithm as distances are updated.

    Suppose we wanted to find the shortest path from a start vertex B to another vertex E. When all vertices in the graph have been visited and the main loop of the algorithm terminates, we can simply go to vertex E and "follow the arrows" back to the start vertex B.

### Time complexity

The time complexity for Dijkstra's algorithm is

$$
O \big(|V| \log |V| + |E| \log |V| \big) = O \big(\log |V| \big(|V| + |E|\big)\big)
$$

* The part $O(|V| \log |V|)$ comes from *visiting* each vertex, equivalent to $|V|$ removals from the priority queue. Removal from a priority queue backed by a binary heap is $O(\log |V|)$.
* The part $O(|E| \log |V|)$ comes from *considering* each edge, equivalent to $|E|$ additions to the priority queue. Adding to a priority queue backed by a binary heap is $O(\log |V|)$.

!!! note
    The time complexity of $O (\log |V| (|V| + |E|))$ is *without* the `decreaseKey()` operation implemented in the binary heap backed Priority Queue.

    The `decreaseKey()` operation **TODO: EXPLAIN AND LINK TO PRIM'S AS WELL!**

For a *sparse* graph Dijkstra's becomes $O(|V|² \log |V|)$, while for a *dense* graph it's $O(|V|³ \log |V|)$. Thus, for dense graphs, it might be worth looking into the Floyd-Warshall algorithm, which exhibits $O(|V|³)$ time complexity.

### Example

* **TODO: NEED AN EXAMPLE TO WALK THROUGH. USE AN EXAMPLE FROM THE COMPREHENSION QUESTIONS OR THE PRACTICE EXAM.**
