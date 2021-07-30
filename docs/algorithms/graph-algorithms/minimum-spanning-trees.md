# Minimum Spanning Trees

## Introduction

To understand what a Minimum Spanning Tree (MST) is, let's consider some definitions first.

!!! tip "Tree"
    A tree is an connected, acyclic and undirected graph. A tree must fulfil $|E| = |V| - 1$, where |E| and |V| are the number of edges and vertices, respectively. If undirected edges are modeled as two opposite directed edges, the tree must instead fulfil $|E| = 2(|V| - 1)$.

!!! tip "Spanning Tree"
    A Spanning Tree $T$ of a graph $G$ is a tree that connects all vertices of $G$.

    $T$ contains all the same vertices as $G$ and edges of $T$ must be present such that all vertices are reachable from any vertex trough some path. In other words, $T$ "spans" the entirety of $G$, but does not necessarily include all edges of $G$.

!!! tip "Minimum Spanning Tree"
    A Minimum Spanning Tree (MST) of a graph is a Spanning Tree where the sum of all edge weights are minimized.

    Note that a graph might have multiple MSTs.

MSTs in general do not exist for unconnected graphs.

The two most known algorithms for MSTs are described below.

## Prim's Algorithm

Prim's algorithm is a greedy algorithm that is very similar to Dijkstra's algorithm. It builds the MST one vertex at a time from the source vertex by choosing the next vertex as the immediate optimal one, thus the term greedy.

The main difference between Dijkstra's and Prim's algorithms is that Dijkstra's chooses the next edge to traverse as part of a cumulative path, whereas Prim's is indifferent to which edges were previously traversed. Other than the algorithm ideas and implementations being similar between Dijkstra's and Prim's algorithms, they are not related in the problem they are trying to solve.

### Cut Property

Prim's algorithm utilize the so-called **cut property** of MSTs, which states that a valid MST $T$ must include the minimum cost edge for any arbitrary cut in a graph $G$. In other words, it must be impossible to make a cut in $G$ whose minimum cost edge is not include in $T$.

Prim's algorithm utilizes the cut property of MSTs to greedily choose the next edge to traverse.

#### Graph cuts

**TODO: CREATE A SKETCH!**

### Implementation of Prim's algorithm

* Visited Set that will form the MST.
* Priority Queue storing the edges of minimum weights to consider. The dequeued edge from the Priority Queue marks the minimum weight edge to traverse next in the most recent graph cut.
* MST Edge Set storing the edges that have been traversed
* Source vertex

The Visited Set represents a *graph cut* separating the visited vertices from the unvisited ones.
The edges that are cut through are the edges in the Priority Queue, where the minimum cost edge will automatically be placed in the front of the priority queue.

Ties between edge duplicate edge weights that are currently in the Priority Queue are broken arbitrarily.

!!! note
    Multiple valid MSTs can be produced from the same graph if the graph contains duplicate edge weights.

The algorithm can be broken down like this:

1. Initialize data structures
    * A Visited Set $VS$ to mark all nodes that have been visited
    * A Priority Queue $PQ$ which keeps the minimum cost edge in the front of the queue
    * A Edge Set $MST$ with edges of the MST

2. Mark the source vertex $S$ as visited by adding it to $VS$

3. Add all edges connected to the source vertex $S$ to $PQ$

4. As long as $VS$ doesn't contain all vertices in the graph *or* $PQ$ still has items
    * Dequeue the next vertex from $PQ$
    * If $S$ is not in 

**TODO: ADD Implementation!!!**

### Time Complexity

The time complexity of Prim's algorithm for Minimum Spanning Trees is the same as [Dijkstra's TODO: LINK!!!!](blabla) for shortest paths, since the algorithms are very similar. Refer to that section for a more thorough explanation.

The time complexity is

$$
O(|E| \log |E| )
$$

where $|E|$ denotes the *order* of the edge set, i.e. the number of edges.

!!! note
    The time complexity of $O(|E| \log |E|)$ is assuming that the Priority Queue backed by a binary heap does not implement the [`decreaseKey()` TODO: LINK TO DECREASE-KEY DEFINTION FROM DIJKSTRA'S](blabla) operation.

    The time complexity would instead be $O(\log |V| (|V| + |E|)))$ had the `decreaseKey()` operation been implemented.

    Note that Java's Priority Queue does not implement the `decreaseKey()` operation.

## Kruskal's algorithm

Kruskal's algorithm is another way to generate an MST from a graph. It was developed by an American mathematician called Joseph Kruskal. It's a greedy algorithm which produces the optimal solution by greedily choosing the best option among the next immediate options.

While Prim's algorithm gradually generated the MST from a source vertex, Kruksal's algorithm is focused around creating clusters of vertices and unioning/merging them.

Kruskal's algorithm creates clusters, or subtrees, consisting of one or more vertices. The clusters expand by creating unions with other clusters until there is one cluster that includes all vertices of the graph.

Kruksal's algorithm can run for disconnected graphs, but will naturally fail to produce a valid MST, since we cannot have $|V|-1$ edges in our MST.

### The cycle property

Like Prim's algorithm used the *cut property*, Kruskal's algorithm uses the *cycle property*:

!!! tip ""
    For any given cycle, the maximum cost edge cannot be included in the MST.

### Disjoint sets

While many other graph algorithms like Prim's and Dijkstra's use a Visited Set, such a data structure does not work for Kruksal's algorithm. The reason is that there is no notion of a single source vertex to initialize the algorithm from. Instead, it produces clusters of vertices spread out over the graph. We can end up in a scenario where we have visited all vertices, but the MST is still not complete.

### Algorithm

The algorithm uses

* **A Priority Queue** of edges to consider is needed. From that, we always get the minimum weight edge first.

* **A Disjoint Set**
    In the beginning, we have an outer set with $|V|$ sets inside it. these inner sets represents the clusters where each cluster initially only contains a single vertex. This data structure is a *disjoint set*, no overlapping of vertices between the inner sets are allowed.

* **An Edge Set representing the MST**
    Edges are used to create a union of two clusters, which guarantees that no cycles are produced. If a given edge has its vertices in different clusters, the clusters are unioned and the edge is added to the MST. If the two edge vertices are in the same cluster, the edge is ignored and we continue to the next.

Each cluster can be viewed as a tree which forms a subgraph of the global graph.

For the algorithm, we need two important operations.

* `find(x)`: Finds the set that vertex $x$ belongs to. More specifically, finds the root of the tree that vertex $x$ is located in.

  The `find()` operation can be optimized with *path compression*. See further description below.

* `union(x, y)`: Creates a union/merge of the sets that contain $x$ and $y$.

Kruskal's algorithm overall creates $|V|$ sets initially, and then performs $2|E|$ `find` operations and $|V|-1$ `union` operations.

**TODO: IMPLEMENT KRUSKALS! MIGHT VERY WELL BE ON THE EXAM, the version without the optimizations!!!**

#### Path compression

If the tree (subgraph) on which we run a `find()` operation is degenerate, we have a time complexity of $O(|V_t|)$, i.e. linear in the number of vertices in the tree.

Path compression can be utilized to decrease the time complexity to $O(1)$. We first realize that in this framework, the only thing that matters for the `find()` operation is the root of the tree, which serves as the figurehead or name of the cluster. When we use `find(x)`, the only thing we care about is finding the root that `x` is tied to. Therefore, we can change the pointer for each node to instead point at the root instead and thereby make all `find()` operations constant.

!!! example
    A degenerate tree is a linear tree, essentially a linked list.

    Degenerate tree with G as root:

        G → E → Y → P → R → W

    Path compressed tree with G as root:

        E → G ← W
          ↗ ↑ ↖
        Y   P   R

Path compression can be implemented by use of pointer reinforcement, which is a concept that can be used for BSTs and AVL trees as well.

#### Rank

* The rank's purpose is to avoid having to maintain the height of each node throughout the algorithm, which becomes cumbersome to do when path compressions occur.
* Rank is the upper bound on the height of a node
* The rank of a node only increases if there is a tie between ranks of the roots of two trees being unioned
* The rank never decreases
* Rank only matters for root nodes of the disjoint set tree

### Minimum Spanning Forests (MSFs)

A Minimum Spanning Forest (MSF) consists of multiple Minimum Spanning Trees. There can be produced from disconnected graphs by algorithms such as Kruskal's.

* For an MSF, the number of edges is $|V| - c$, where $c$ is the number of disconnected subgraphs. Consider a disconnected graph with $8$ total vertices and three subgraphs. The MSF will contain $8-3=5$ edges.

* In case of a disconnected graph, Kruskal's algorithm will consider all edges before it finds out that the graph is disconnected.
