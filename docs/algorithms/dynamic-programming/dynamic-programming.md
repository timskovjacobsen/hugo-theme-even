# Dynamic Programming

Dynamic programming is an algorithmic technique that solves a large problem by solving smaller, overlapping subproblems that repeat while solving the larger problem.

Dynamic programming has many similarities to recursion. The main difference is that with dynamic programming, we store the solution to previous subproblems so they can be looked up instead of recomputed in subsequent steps. This process of storing previous solutions is called *memoization*. Also, in recursion, we are calling the function/method itself repeatedly. That is not necessarily the case for dynamic programming, although it is possible.

Dynamic programming can be seen as a more general banner for divide and conquer algorithms.

!!! tip "Memoization"
    Memoization is the process of storing solutions to subproblems that have previously been computed. Memoization avoids the need for recomputing solutions as they can be looked up instead, which is much faster, although requires more memory.

    When using memoization, we are essentially trading time complexity for space complexity.

Some famous algorithms that utilize dynamic programming are

* Knuth-Morris-Pratt (KMP) algorithm for pattern matching, more specifically the failure table
* Dijkstra's shortest path algorithm

When dealing with dynamic programming, the hardest part of often to spot and define the overlapping subproblems and the optimal substructure. Coding the solution afterwards often easier.

!!! tip "Exponential to polynomial time complexity"
    Using dynamic programming when applicable can reduce an exponential solution to a solution of polynomial time complexity.

## When to use Dynamic Programming

In order to use dynamic programming for solving a problem, the problem characteristics described below must be present.

### Overlapping subproblems

The large problem must be composed on smaller **overlapping subproblems** that are dependent on each other. The dependency entails that the solution to previously computed subproblems is used in the next subproblem.

!!! info "Dynamic programming vs divide and conquer"
    A contrast between dynamic programming and divide and conquer algorithms can be made here. Let's take *merge sort* as an example of a classic divide and conquer algorithm. It splits the problem into smaller problems and then merges their solutions into the solution to the large problem. Although, subproblems do not rely on the specific solution of previous ones. The procedure just needs two generic arrays to be merged as input and doesn't care about how any "stored" previous results. In other words, we are not solving the exact same small problem multiple times and thus do not have overlapping subproblems.

The way to efficiently deal with overlapping subproblems is to store the solutions so they can be looked up when needed, i.e. to use memoization.

Overlapping subproblems can be depicted via a Directed Acyclic Graph (DAC).

### Optimal substructure

Optimal substructure means that we can find the optimal solution for each subproblem which can then be combined into the optimal solution for the larger problem.

There must be a natural order in which the subproblems shall be solved, so the intermediate solutions can build on each other

### Sketching the problem as a DAG

In order to identify whether the problem is suited for a dynamic programming approach, we can try to sketch the problem as a Directed Acyclic Graph (DAG). Such a graph can help to spot whether subproblem solutions are interrelated or not.

If the DAG needs an edge $(u, v)$ for solving subproblem $u$, then subproblem $v$ must be computed first and used as part of the solution. All edges in the DAC denote recursive calls while the vertices are the solved problems from combining previous sub solutions.

???+ example "Fibonacci DAC"
    The graph below can be seen as a DAC for the Fibonacci problem. *Although the graph could be organized better :)*.

    <div class="mermaid">
    %%{init: {"theme": "default", "flowchart": {"curve": "basis"}}}%%
    graph TD;
        5(("fib(5)"))-->4(("fib(4)"));
        5(("fib(5)"))-->3(("fib(3)"));
        4(("fib(4)"))-->3(("fib(3)"));
        4(("fib(4)"))-->2(("fib(2)"));
        3(("fib(3)"))-->2(("fib(2)"));
        3(("fib(3)"))-->1(("fib(1)"));
        2(("fib(2)"))-->1(("fib(1)"));
        2(("fib(2)"))-->0(("fib(0)"));
        1(("fib(1)"))-->0(("fib(0)"));
    </div>

    Ww can see that each subproblems depends on the two previous solutions.

## Top-down and Bottom-up approaches

A **Bottom-up** approach generates the solution by solving the easy subproblems in the beginning harder problems further down the line. This will be a iterative, i.e. non-recursive, solution.
With Bottom-up, we need to make sure we are solving the subproblems in the correct order, which can be a bit more complicated than Top-down.
Thus, we need to arrange the subproblems before we start actually solving them.  

A **Top-Down** approach incorporates a recursive solution with memoization added to it. For this approach, we store computed solutions in a suitable data structure. In each recursive call, we try to lookup the solution. If it's there, we take the value. Otherwise, we continue recursing and compute and store the answer.
The Top-down approach can be more efficient than Bottom-up if we only need to solve a small amount of subproblems compared to the total amount of subproblems in order to get to the final solution.
Top-down achieves the solutions in a depth first search fashion, adding the sub solutions to the memoization data container when it returns from the recursive calls.

It should be noted that in practice, iteration is more efficient than recursion as recursion comes with some overhead for creating and maintaining the call stack. Therefore, iterative procedures are often used in practice if the efficiency is needed.

## Fibonacci example

Fibonacci is a good example for demonstrating dynamic programming as we it solves a large problem by repeatedly solving a smaller problem and building the large solution as the sum of those solutions.

In solving Fibonacci without dynamic programming, the same calculation is done multiple times. Consider `fib(5)`, where we are calculating `fib(1)`, `fib(2)`, `fib(2)` are calculated multiple times. Note that the function name has been abbreviated to `f()`.

                      f(5)
                     /    \
                f(4)        f(3)
                /  \         /   \
            f(3)  f(2)      f(2) f(1)
            /  \   /  \      /  \
        f(2) f(1) f(1) f(0) f(1) f(0)
        / \
    f(1) f(0)

As we compute larger and larger Fibonacci numbers, the amount of recalculations increase exponentially as a result of the tree shape. In general, the cost of the solution is $O(2^n)$.

!!! example "Standard recursive Fibonacci"
    An implementation of a standard recursive Fibonacci is given below in Python.

    ```python
    def fib_standard(n):
        """Fibonacci implementation without use of dynamic programming.
        Time complexity: O(2^n)
        """
        if n == 0:
            return 0
        if n == 1:
            return 1
        
        return fib_standard(n-1) + fib_standard(n-2)
    ```

!!! example "Dynamic Fibonacci"

    Some variants of the Fibonacci problem solved with dynamic programming is shows below.

    ```python
    def fib_dynamic(n):
        """Bottom-up approach with dynamic programming.
        Time complexity: O(n)
        """
        if n <= 1:
            return n

        # Initialize array (list) with n elements of 0
        memo = [0 for _ in range(n + 1)]
        memo[0] = 0
        memo[1] = 1

        for i in range(2, n + 1):
            memo[i] = memo[i - 1] + memo[i - 2]
        return memo[n]


    def fib_dynamic_recursive(n, memo=None):
        """Top-down approach combining dynamic programming and recursion.
        Time complexity: O(n)
        NOTE: We need 'memo' as an input to avoid recomputing it for each
        recursive call and thereby defeating the purpose of dynamic programming."""
        if n <= 1:
            return n

        if not memo:
            # Initialize array (list) with n elements of 0
            memo = [0 for _ in range(n + 1)]

        # Fill up the array with Fibonacci sub-solutions recursively
        if memo[n - 1] == 0:
            memo[n - 1] = fib_dynamic_recursive(n - 1, memo)
        if memo[n - 2] == 0:
            memo[n - 2] = fib_dynamic_recursive(n - 2, memo)

        memo[n] = memo[n - 1] + memo[n - 2]
        return memo[n]


    def fib_dynamic_pythonic(n):
        """Bottom-up approach in a more pythonic way."""
        if n <= 1:
            return n

        memo = []
        memo.append(0)
        memo.append(1)

        for i in range(2, n + 1):
            memo.append(memo[i - 1] + memo[i - 2])
        return memo[n]


    if __name__ == "__main__":
        n = range(10)
        for i in n:
            print(fib_dynamic(i))
            print(fib_dynamic_recursive(i))
            print(fib_dynamic_pythonic(i))

        # All three functions print the sequence [0, 1, 1, 2, 3, 5, 8, 13, 21, 34]
    ```

    When we use dynamic programming instead of the standard recursive-only solution, we change the time and space complexity from $O(2^n)$ and $O(1)$ to $O(n)$ and $O(n)$, respectively. Thus, the significant time efficiency gained from dynamic programming comes with a cost of added space. This is normally a very good tradeoff though.

## Bellman-Ford

The Bellman-Ford algorithm is a dynamic programming algorithm for computing the shortest path from a source vertex to all other vertices in a graph. The algorithm is very similar to Dijkstra's, which also computes the shortest path.

Bellman-Ford is based on the fact that the shortest path between two vertices uses maximum $|V| - 1$ edges. Unlike Dijkstra's, it can deal with negative edge weights.

```python
def bellman_ford(source):
    """Pseudo-code for the Bellman-Ford algorithm using dynamic programming.
    
    Time complexity: $O(|V| * |E|)
    Space complexity: O(|V|)
    """

    # Initialize a distance map from vertices to distances for the source vertex
    dist = {}

    # At the start of the algorithm, we set the shortest distance from the 
    # source to each vertex found so far equal to infinity for each vertex
    for vertex in vertices:
        dist[vertex] = inf

    # The distance to the source vertex to itself is of course 0
    dist[source] = 0

    # Consider all edges |V| - 1 times
    for i in range(len(vertices) - 1):

        # Loop over all directed edges
        for edge in edges:
            
            # Check if this path is shorter than the shortest path found so far
            if dist[edge.v] > dist[edge.u] + edge.w:
                # It is, update to the new shortest path
                dist[edge.v] = dist[edge.u] + edge.w
            
    return dist
```

The algorithm computes for iteration $i$ in the outer loop, the shortest paths from the source vertex to all other vertices that are $i$ or fewer edges away.

We have established that that each shortest path will use at most $|V| - 1$ edges. Thus, after $|V| - 1$ iterations of the outer loop, we will have computed the shortest path from the source vertex to all other vertices.

The Bellman-Ford algorithm uses dynamic programming in a **bottom-up** approach.

!!! tip "Detecting negative cycles"
    Bellman-Ford can detect negative cycles by running an additional iteration at the end. If the distance map changes, we have detected a negative cycle.

### Time and space complexity

The time complexity for the Bellman-Ford algorithm is $O(|V| \cdot |E|)$, while the space complexity is $O(|V|)$ for storing the map to look up the shortest paths found so far.

Since Dijkstra's algorithm has a time complexity of $O \big(\log |V| \big(|V| + |E|\big)\big)$ which is better than the Bellman-Ford algorithm, Dijkstra's should be preferred if no negative edge wights are present in the graph.

## Floyd-Warshall

Floyd-Warshall is a dynamic programming algorithm for computing the **shortest path between every pair of vertices in a graph**. It can handle edges with negative weight.

The algorithm assumes that the graph topology is represented by an **adjacency matrix**.

!!!tip
    The shortest path is equal to the sum of the shortest **TODO**

The shortest paths found so far for each vertex pair is stored in a distance matrix.

1. Find the shortest path from vertex $0$ with vertex $0$ as the intermediate vertex.
2. Find the shortest path from vertex $0$ with vertex $1$ as the intermediate vertex.
3. Find the shortest path from vertex $0$ with vertex $2$ as the intermediate vertex.
4 etc. ...

When we reach vertex $|V| - 1$, we have considered all vertices as intermediate vertices.

```python
def floyd_warshall(adj_matrix):
    # Init. distance matrix to be equal to adjacency matrix, size: |V| x |V|
    dist = adj_matrix
    
    for j in len(vertices):
        for i in len(vertices): 
            for k in len(vertices:
                # Check if the path is shorter than what have stored so far
                if dist[i][k] > dist[i][j] + dist[j][k]:
                    # It is shorter, update
                    dist[i][k] = dist[i][j] + dist[j][k]
                
    return dist
```

The [Wikipedia article](https://en.wikipedia.org/wiki/Floyd%E2%80%93Warshall_algorithm) has some decent visuals for explaining the algorithm.

The algorithm uses a **bottom-up** dynamic programming programming approach. The subproblems involve the intermediate vertices and the shortest path pairs.

!!! tip "Detecting negative cycles"
    Floyd-Warshall can detect negative cycles by checking the diagonal of distance matrix. If any one the diagonal elements are negative in any iteration, we have a negative cycle.

We can terminate the Bellman-Ford algorithm early if we detect a negative cycle of if the distance map is not updated in a given iteration.

### Time and space complexity

The **time complexity** of Floyd-Warshall is $O(|V|³)$. We must consider all |V|² pairs of vertices and they all must be dealt with $|V|$ times.

The space complexity is $O(|V|²)$ since we need to store all pairs in the `dist` matrix for keeping track of distances found so far.

## More examples

### Largest contiguous sum

TODO: Add code

### Exact change

TODO: Add code
