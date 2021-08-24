import math
from typing import Set

from graph import Graph, Vertex, VertexDistance, Edge


class FakePriorityQueue(list):
    """A minimum priority queue implemented very inefficiently with a list backing
    data structure. The minimum priority queue sorts alphabetically in case of ties.
    NOTE: Don't do this at home, should be implemented as a MinHeap instead."""

    def __init__(self, lst):
        self.lst = lst
        self.lst.sort(key=min)

    def enqueue(self, vertex_distance: VertexDistance):
        self.lst.append(vertex_distance)
        self.lst.sort(key=lambda x: (x.distance, x.vertex.data))

    def dequeue(self):
        return self.lst.pop(0)

    def is_empty(self):
        return True if not self.lst else False

    def __iter__(self):
        yield from self.lst

    def __str__(self):
        return f"{self.lst}"


def dijkstra(graph: Graph, start: Vertex) -> list:
    """Implementation of Dijkstra algorithm shortest path algorithm.
    Returns the visited order and the shortest distance from the source to each vertex.
    NOTE: Does not report the actual path taken. It could be implemented by tracking
    which vertex we came from each time we visit a new vertex. Thereby re-tracing
    pointers will reveal the shortest path.
    """

    # Initialize the necessary data structures
    visited: Set = set()
    distance_map: dict = {}
    priority_queue = FakePriorityQueue([])  # NOTE: Fake priority queue with a list

    # Additional data structures for reporting results
    visited_order = []
    distance_to_source = []

    # Insert infinity as initial values for all vertex distances
    for vertex in graph.vertices:
        distance_map[vertex] = math.inf

    # Enqueue the start vertex, which naturally has a distance of 0 to itself
    priority_queue.enqueue(VertexDistance(start, 0))

    # Loop while the PQ has items and there are unvisited vertices
    while not priority_queue.is_empty() and len(visited) < len(graph):

        # Extract the next vertex from the PQ and the shortest distance
        next_vertex = priority_queue.dequeue()
        vertex, dist_to_source = next_vertex.vertex, next_vertex.distance

        if vertex not in visited:
            # Mark vertex as visited
            visited.add(vertex)

            # Update data structures to be returned
            visited_order.append(vertex)
            distance_to_source.append(dist_to_source)

            # Update the distance map w/ the vertex's minimum cumulative distance
            distance_map[vertex] = dist_to_source

            # Get the neighbors of the vertex from the adjacency list of the graph
            neighbors = graph.adjacency_list[vertex]

            # Find neighbors that are not visited
            unvisited_neighbors = neighbors.difference(visited)

            # Enqueue all neighbors that are not yet in the visited set
            for neighbor in unvisited_neighbors:
                # Enqueue the neighbor vertex
                # The distance if now the vertex's distance to the source plus the
                # distance from the vertex to the neighbor
                new_vertex = VertexDistance(
                    neighbor.vertex, dist_to_source + neighbor.distance
                )
                priority_queue.enqueue(new_vertex)

    return visited_order, distance_to_source


if __name__ == "__main__":

    # EXAMPLE 1
    print()
    print("Example 1")
    print("---------")
    A, B, C, D, E, F, G, H = (
        Vertex("A"),
        Vertex("B"),
        Vertex("C"),
        Vertex("D"),
        Vertex("E"),
        Vertex("F"),
        Vertex("G"),
        Vertex("H"),
    )

    vertices = {A, B, C, D, E, F, G, H}

    edges = {
        Edge(A, B, 4),
        Edge(B, C, 2),
        Edge(C, D, 7),
        Edge(D, E, 5),
        Edge(D, F, 6),
        Edge(D, H, 1),
        Edge(E, C, 8),
        Edge(C, G, 0),
        Edge(B, H, 4),
        Edge(A, G, 3),
        Edge(G, F, 7),
    }
    g = Graph(vertices, edges)

    visited, distance = dijkstra(g, A)
    for vertex, dist in zip(visited, distance):
        print(vertex.data, dist)

    # NOTE: Correct order and distances:
    # [A: 0, B: 4, C: 3, D: 9, E: 11, F: 10, G: 3, H: 8]

    # EXAMPLE 2
    print()
    print("Example 2")
    print("---------")
    A, B, C, D, E = (
        Vertex("A"),
        Vertex("B"),
        Vertex("C"),
        Vertex("D"),
        Vertex("E"),
    )

    vertices = {A, B, C, D, E}

    edges = {
        Edge(A, B, 1),
        Edge(B, C, 1),
        Edge(A, C, 3),
        Edge(C, D, 0),
        Edge(C, E, 4),
        Edge(D, E, 4),
    }
    g = Graph(vertices, edges)

    visited, distance = dijkstra(g, A)
    for vertex, dist in zip(visited, distance):
        print(vertex.data, dist)

    # NOTE: Correct order and distances: [A: 0, B: 1, C: 2, D: 2, E: 6]
