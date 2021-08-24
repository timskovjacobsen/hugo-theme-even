import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

public class TestMinimumSpanningTree {

    /**
     * 
     * @return the "small graph" from the csvistool.com website
     */
    private static List<Object> createSmallGraph() {

        // Create the set of vertices
        Set<Vertex<String>> vertices = new HashSet<>();
        Vertex<String> A = new Vertex<String>("A");
        vertices.add(A);
        Vertex<String> B = new Vertex<String>("B");
        vertices.add(B);
        Vertex<String> C = new Vertex<String>("C");
        vertices.add(C);
        Vertex<String> D = new Vertex<String>("D");
        vertices.add(D);
        Vertex<String> E = new Vertex<String>("E");
        vertices.add(E);
        Vertex<String> F = new Vertex<String>("F");
        vertices.add(F);
        Vertex<String> G = new Vertex<String>("G");
        vertices.add(G);
        Vertex<String> H = new Vertex<String>("H");
        vertices.add(H);

        // Create the set of edges
        Set<Edge<Vertex<String>>> edges = new HashSet<>();
        edges.add(new Edge(A, B, 7));
        edges.add(new Edge(A, D, 1));
        edges.add(new Edge(A, E, 5));
        edges.add(new Edge(B, C, 1));
        edges.add(new Edge(C, G, 2));
        edges.add(new Edge(G, F, 6));
        edges.add(new Edge(F, D, 9));
        edges.add(new Edge(G, H, 7));
        edges.add(new Edge(G, E, 8));

        // Opposite edges to make the graph undirected
        edges.add(new Edge(B, A, 7));
        edges.add(new Edge(D, A, 1));
        edges.add(new Edge(E, A, 5));
        edges.add(new Edge(C, B, 1));
        edges.add(new Edge(G, C, 2));
        edges.add(new Edge(F, G, 6));
        edges.add(new Edge(D, F, 9));
        edges.add(new Edge(H, G, 7));
        edges.add(new Edge(E, G, 8));

        Graph graph = new Graph(vertices, edges);

        List<Object> list = new ArrayList<>();
        list.add(graph);
        list.add(A); // Start vertex
        return list;
    }

    @Test
    public void testPrims() {
        TestMinimumSpanningTree graphAlgos = new TestMinimumSpanningTree());

        // Get graph and start vertex
        List<Object> list = createSmallGraph();
        Graph graph = (Graph) list.get(0);
        Vertex<String> A = (Vertex<String>) list.get(1);

        // Get the actual visited vertices in order
        Set<Edge<String>> actual = graphAlgos.prims(A, graph);

        String[] edges = actual.toString().split(",");
        for (String edge : edges) {
            System.out.println(edge);
        }

        System.out.println(actual);

        // Test if the MST has the correct number of edges
        int expectedSize = 2 * (8 - 1);
        assertEquals(actual.size(), expectedSize);

        // NOTE:
        // How to test that the correct edges are in the MST?
        // We cannot compare the order that the edges appear in the set
        // We could maybe create duplicates of all edges and then test if the sets
        // are equal, but that would require a ".equals" method that is different from
        // the one implemented by the course files I think.
    }

    @Test
    public void testBFS() {
        TestMinimumSpanningTree graphAlgos = new TestMinimumSpanningTree();

        // Create the set of vertices
        Set<Vertex<String>> vertices = new HashSet<>();
        Vertex<String> A = new Vertex<String>("A");
        vertices.add(A);
        Vertex<String> B = new Vertex<String>("B");
        vertices.add(B);
        Vertex<String> C = new Vertex<String>("C");
        vertices.add(C);
        Vertex<String> D = new Vertex<String>("D");
        vertices.add(D);
        Vertex<String> E = new Vertex<String>("E");
        vertices.add(E);
        Vertex<String> F = new Vertex<String>("F");
        vertices.add(F);
        Vertex<String> G = new Vertex<String>("G");
        vertices.add(G);
        Vertex<String> H = new Vertex<String>("H");
        vertices.add(H);

        // Create the set of edges
        Set<Edge<Vertex<String>>> edges = new HashSet<>();
        // edges.add(new Edge<Vertex<String>, Vertex<String>, Integer>(A, B, 1));
        // Edge<Vertex<String>, Vertex<String>, Integer> = new Edge<>(A, B, 1);
        edges.add(new Edge(A, B, 1));
        edges.add(new Edge(A, C, 1));
        edges.add(new Edge(A, D, 1));
        edges.add(new Edge(A, E, 1));
        edges.add(new Edge(B, G, 1));
        edges.add(new Edge(D, F, 1));
        edges.add(new Edge(F, G, 1));
        edges.add(new Edge(G, E, 1));
        edges.add(new Edge(G, H, 1));

        // Opposite edges to make the graph undirected
        edges.add(new Edge(B, A, 1));
        edges.add(new Edge(C, A, 1));
        edges.add(new Edge(A, D, 1));
        edges.add(new Edge(A, E, 1));
        edges.add(new Edge(G, B, 1));
        edges.add(new Edge(F, D, 1));
        edges.add(new Edge(G, F, 1));
        edges.add(new Edge(E, G, 1));
        edges.add(new Edge(H, G, 1));

        Graph graph = new Graph(vertices, edges);

        // Get the actual visited vertices in order
        List<Vertex<String>> actual = graphAlgos.bfs(A, graph);

        System.out.println(actual.toString());

        // NOTE: Vertices are explored in the order they come out from the adjacency
        // list, which might not be alphabetically like in the csvistool
        String expected = "[A, B, C, D, E, G, F, H]";

        assertEquals(actual.toString(), expected);
    }

}
