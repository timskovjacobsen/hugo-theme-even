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

...

## References to some graph algoritms

* [Depth first search](/algorithms/graph-algorithms/graph-search-algorithms/#depth-first-search)

* [Breadth first search](/algorithms/graph-algorithms/graph-search-algorithms/#breadth-first-search)

* [Dijkstra's Shortest Path Algorithm](/algorithms/graph-algorithms/dijkstra/#dijkstras-algorithm)


* [Minimum spanning trees](/algorithms/graph-algorithms/minimum-spanning-trees/#minimum-spanning-trees)
