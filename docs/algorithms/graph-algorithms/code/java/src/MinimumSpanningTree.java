import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import java.util.List;

public class MinimumSpanningTree {

    /**
     * Runs Prim's algorithm on the given graph and returns the Minimum Spanning
     * Tree (MST) in the form of a set of Edges. If the graph is disconnected and
     * therefore no valid MST exists, return null.
     *
     * It is assumed that the graph is undirected, i.e always has two opposite edges
     * with equal weight.
     * 
     * It is also assumes that the start vertex and the graph are not null and that
     * * the start vertex is present in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin Prims on.
     * @param graph The graph we are applying Prims to.
     * @return The MST of the graph or null if there is no valid MST.
     */
    public static <T> Set<Edge<T>> prims(Vertex<T> start, Graph<T> graph) {

        // A visited set to keep track of vertices that have been visited
        Set<Vertex<T>> VS = new HashSet<>();

        // A minimum priority queue to keep track of the next minimum cost edge to
        // consider
        PriorityQueue<Edge<T>> PQ = new PriorityQueue<>();

        // A set to hold the edges that make up the Minimum Spanning Tree
        Set<Edge<T>> MST = new HashSet<>();

        // Get the neighbor vertices of the start vertex
        List<VertexDistance<T>> startNeighbors = graph.getAdjList().get(start);

        // Add edges to neighbors the priority queue
        for (VertexDistance<T> neighbor : startNeighbors) {
            // Add a copy of the edge from the vertex to the neighbor to the priority
            // queue
            PQ.add(new Edge<>(start, neighbor.getVertex(), neighbor.getDistance()));
        }

        // Mark the source vertex as visited
        VS.add(start);

        // As long as the priority queue has items and there aer still vertices left to
        // visit
        while (!(PQ.isEmpty()) && VS.size() != graph.getVertices().size()) {
            // Dequeue the minimum cost edge from the priority queue
            Edge<T> edge = PQ.poll();

            // If the second vertex of the edge is not in the visited set, we perform
            // operations with it. Otherwise we continue to the next edge in the
            // priority queue.
            Vertex<T> v = edge.getV();
            if (!VS.contains(v)) {
                // Mark vertex V as visited
                VS.add(v);

                // Add the edge and its opposing edge to the Minimum Spanning Tree
                MST.add(edge);
                MST.add(new Edge<>(edge.getV(), edge.getU(), edge.getWeight()));

                //
                // Get the neighbors vertices of the start vertex
                List<VertexDistance<T>> neighbors = graph.getAdjList().get(v);

                // Enqueue edges connected to vertex v if the other vertex on the
                // edge has not been visited
                for (VertexDistance<T> neighbor : neighbors) {

                    Vertex<T> neighborVertex = neighbor.getVertex();
                    if (!VS.contains(neighborVertex)) {
                        PQ.add(new Edge<>(v, neighborVertex, neighbor.getDistance()));
                    }
                }
            }
        }

        // Check if the computed Minimum Spanning Tree is valid
        // A valid MST has 2*(|V| - 1) edges
        int V = graph.getVertices().size();
        if (MST.size() == 2 * (V - 1)) {
            // The generated MST is valid
            return MST;
        }
        // If we reach this code, there is no valid MST
        return null;
    }

    /**
     * NOTE: ======================================================== IMPLEMENTATION
     * IS NOT WORKING AT THE MOMENT, IN PROGRESS
     * ==============================================================
     * 
     * Runs Kruskal's algorithm on the given graph and returns the Minimum Spanning
     * Tree (MST) in the form of a set of Edges. If the graph is disconnected and
     * therefore no valid MST exists, return null.
     *
     * It is assumed that the graph is undirected, i.e always has two opposite edges
     * with equal weight.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin Prims on.
     * @param graph The graph we are applying Prims to.
     * @return The MST of the graph or null if there is no valid MST.
     */
    public static <T> Set<Edge<T>> kruskals(Graph<T> graph) {

        // ds
        Set<Edge<T>> MST = new HashSet<>();
        PriorityQueue<Edge<T>> PQ = new PriorityQueue<>();

        int nVertices = graph.getVertices().size();

        // TODO: The disjoint set should probably be a set of trees
        Set<Set<T>> disjointSet = new HashSet<>();

        for (Vertex<T> v : graph.getVertices()) {
            Set<T> vSet = new HashSet<T>();
            disjointSet.add(vSet);
        }

        // Populate the priority queue with all edges in the graph
        for (Edge<T> e : graph.getEdges()) {
            PQ.add(e);
        }

        while (!(PQ.isEmpty()) && MST.size() < nVertices - 1) {
            Edge<T> edge = PQ.poll();

            //
            // if ()
        }
    }

    // private static <T> find

    // private static <T> boolean isInSameCluster(Vertex<T> u, Vertex<T> v) {

    // return false;
    // }
}
