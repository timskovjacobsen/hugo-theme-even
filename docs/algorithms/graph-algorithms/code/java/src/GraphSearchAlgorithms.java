import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class GraphSearchAlgorithms {

    /**
     * Performs a breadth first search (BFS) on the input graph, starting at the
     * parameterized starting vertex.
     *
     * It is assumed that the passed in start vertex and graph will not be null and
     * that the start vertex is present in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin the bfs on.
     * @param graph The graph to search through.
     * @return List of vertices in visited order.
     */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {

        Set<Vertex<T>> visitedSet = new HashSet<>();
        Queue<Vertex<T>> queue = new LinkedList<>();
        List<Vertex<T>> visitedOrder = new LinkedList<>();

        visitedSet.add(start);
        visitedOrder.add(start);

        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex<T> vertex = queue.poll();

            // Get the vertex neighbors and their distances to the vertex
            List<VertexDistance<T>> neighbors = graph.getAdjList().get(vertex);

            for (VertexDistance<T> neighbor : neighbors) {
                Vertex<T> neighborVertex = neighbor.getVertex();

                if (!visitedSet.contains(neighborVertex)) {
                    // Mark the vertex as visited and enqueue it
                    visitedSet.add(neighborVertex);
                    visitedOrder.add(neighborVertex);
                    queue.add(neighborVertex);
                }
            }
        }
        return visitedOrder;
    }

    /**
     * Performs a depth first search (DFS) on the input graph, starting at the
     * parameterized starting vertex.
     *
     * It is assumed that the passed in start vertex and graph will not be null and
     * that that the start vertex is present in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin the dfs on.
     * @param graph The graph to search through.
     * @return List of vertices in visited order.
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
        Set<Vertex<T>> visitedSet = new HashSet<>();

        List<Vertex<T>> visitedOrder = new LinkedList<>();

        return dfsHelper(start, graph, visitedSet, visitedOrder);
    }

    private static <T> List<Vertex<T>> dfsHelper(Vertex<T> start, Graph<T> graph, Set<Vertex<T>> visitedSet,
            List<Vertex<T>> visitedOrder) {

        visitedSet.add(start);
        visitedOrder.add(start);

        // Get the vertex neighbors and their distances to the vertex
        List<VertexDistance<T>> neighbors = graph.getAdjList().get(start);

        // Recurse into neighbor vertices that have not yet been visited
        for (VertexDistance<T> neighbor : neighbors) {
            Vertex<T> neighborVertex = neighbor.getVertex();

            if (!(visitedSet.contains(neighborVertex))) {

                dfsHelper(neighborVertex, graph, visitedSet, visitedOrder);
            }
        }
        return visitedOrder;
    }

}