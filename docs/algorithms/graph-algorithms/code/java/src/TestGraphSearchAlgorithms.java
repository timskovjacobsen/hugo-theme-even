import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.Set;
import java.util.HashSet;
import java.util.List;

public class TestGraphSearchAlgorithms {

    /**
     * 
     * @return the "small graph" from the csvistool.com website
     */
    private static Graph createSmallGraph() {

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

        return graph;
    }

    @Test
    public void testDFS() {
        GraphSearchAlgorithms graphAlgos = new GraphSearchAlgorithms();

        // Graph graph = createSmallGraph();

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
        List<Vertex<String>> actual = graphAlgos.dfs(A, graph);

        System.out.println(actual.toString());

        // NOTE: Vertices are explored in the order they come out from the adjacency
        // list, which might not be alphabetically like in the csvistool
        String expected = "[A, B, G, F, D, E, H, C]";

        assertEquals(actual.toString(), expected);
    }

    @Test
    public void testBFS() {
        GraphSearchAlgorithms graphAlgos = new GraphSearchAlgorithms();

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
