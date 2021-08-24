import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Deque;

/**
 * Your implementation of various graph traversal algorithms.
 */
public class GraphAlgosDFSiterative {

    private static <T> List<Vertex<T>> dfsIterative(Vertex<T> start, Graph<T> graph) {

        Set<Vertex<T>> visitedSet = new HashSet<>();
        Deque<Vertex<T>> stack = new ArrayDeque<>();
        List<Vertex<T>> visitedOrder = new LinkedList<>();

        stack.push(start);

        while (!(stack.isEmpty())) {
            // Pop the next vertex to visit from the stack
            Vertex<T> vertex = stack.pop();

            // Visit the vertex if we haven't already
            if (!(visitedSet.contains(vertex))) {
                // Visit vertex
                visitedSet.add(vertex);
                visitedOrder.add(vertex);

                // Get the vertex neighbors and their distances to the vertex
                List<VertexDistance<T>> neighbors = graph.getAdjList().get(vertex);

                // Add neighbor vertices that have not been visited to the stack
                for (VertexDistance<T> neighbor : neighbors) {
                    if (!(visitedSet.contains(neighbor.getVertex()))) {
                        stack.push(vertex);
                    }
                }
            }
        }
        return visitedOrder;
    }

}