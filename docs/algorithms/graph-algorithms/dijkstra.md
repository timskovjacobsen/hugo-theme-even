# Dijkstra's Algorithm

Dijkstra's algorithm is a greedy algorithm that finds the shortest path from one single source vertex to all other vertices for weighted graphs, where all weights are non-negative.

In most scenarios, the weights can be viewed as distances.

!!! tip
    Dijkstra's shorted path algorithm work for both **weighted**, **unweighted**, **connected**, **disconnected**, **directed** and **undirected** graphs.

!!! note
    It's possible to use Dijkstra's algorithm for finding the shortest path in an unweighted graph, but using BFS is both simpler and more efficient.

Dijkstra's algorithm requires

* **A source vertex**
* **A minimum Priority Queue**

    For storing the cumulative weights/distances from the source vertex to each vertex. The use of a minimum Priority Queue helps us choose the next vertex in the path as the one with the lowest cumulative distance.

    Using a Priority Queue, we don't need to consider the fact that a vertex can be traveled to from multiple paths. We will always be guaranteed to dequeue the vertex with the currently shortest path automatically.

* **A Set of visited vertices**

    A Set of the vertices that are currently included in the shortest path.

* **A Map**

    For storing the graph vertices as keys and currently known shortest distance from the start vertex as values. Initially, all distances are $\infty$ and are updated when each vertex is visited with the shortest distance to that vertex.

    !!! tip
        Dijkstra's algorithm inherently ensures that if a vertex has been visited, it will have been through the shortest possible path.

## Algorithm

???+ example "Implementation"

    === "Python"
        ```python linenums="1"
        --8<--
        docs/algorithms/graph-algorithms/code/python/dijkstra.py
        --8<--
        ```

!!! tip "Extracting the shortest path"
    The shortest path given as a sequence of vertices is incrementally built by the algorithm. If we want to report the vertices to traverse, we must make sure that each time we update a distance for a vertex, we also report which node we came from. Since the node that we came from now provides the shortest path to the updated node, we know that travelling that direction will (currently) lead us on the shortest path back towards the start vertex. Note that the direction might change during the algorithm as distances are updated.

    Suppose we wanted to find the shortest path from a start vertex B to another vertex E. When all vertices in the graph have been visited and the main loop of the algorithm terminates, we can simply go to vertex E and "follow the arrows" back to the start vertex B.

## Time complexity

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

## Examples

??? example

    We want to run Dijkstra's algorithm on the graph below and report the visited order of the vertices. We break ties in alphabetical order.

    <figure style="box-shadow:none">
    <img src="/datastructures/nonlinear/media/dijkstra-example.svg" width="250" />
    <figcaption>Example graph.</figcaption>
    </figure>

    ...
