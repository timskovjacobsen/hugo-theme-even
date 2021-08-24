
from dataclasses import dataclass
from typing import Any, Set


@dataclass
class Vertex:
    data: Any

    def __hash__(self):
        return hash(str(self))

    def __eq__(self, other):
        return self.data == other.data


@dataclass
class Edge:
    u: Vertex
    v: Vertex
    weight: int

    def __hash__(self):
        return hash(str(self))

    def __eq__(self, other):
        """An edge is compared by weight."""
        return self.weight == other.weight

    def __lt__(self, other):
        return self.weight < other.weight
    
    def __gt__(self, other):
        return self.weight > other.weight

@dataclass
class VertexDistance:
    """Class representing a vertex and the distance to it from another vertex."""
    vertex: Vertex
    distance: int

    def __hash__(self):
        return hash(str(self))

    def __eq__(self, other):
        return self.distance == other.distance


class Graph:
    
    def __init__(self, vertices: Set[Vertex], edges: Set[Edge], directed: bool = False) -> None:
        self.vertices = vertices
        self.edges = edges
        self.adjacency_list = dict()

        for vertex in vertices:
            self.adjacency_list[vertex] = set()

        for edge in edges:

            if edge.u in self.adjacency_list:
                # Create and add the specified edge
                self.adjacency_list[edge.u].add(VertexDistance(edge.v, edge.weight))

                if not directed:
                    # Create and add the edge in the opposite direction as well
                    self.adjacency_list[edge.v].add(VertexDistance(edge.u, edge.weight))

            else:
                raise ValueError(f"The graph is not consistent. An edge contains a vertex '{edge.u}', but it does not appear in the vertex set.")

    def __len__(self):
        return len(self.vertices)
