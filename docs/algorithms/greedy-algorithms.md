# Greedy algorithms

!!! tip "The greedy approach"
    A greedy algorithm will select the choice with the most obvious immediate advantage in any given step of the algorithm.

The most obvious immediate choice leads to an optimal solution for some problems, but not for others. The greedy approach is extremely useful in the cases where it is known to provide the optimal solution, but it can lead to bad results in situations where it produces suboptimal solutions.

## Problems well suited for greedy algorithms

The Minimum Spanning Tree problem is well suited for being solved by greedy algorithms where it yields an optimal solution. Prim's and Kruksal's algorithm are examples of greedy algorithms.

If we consider a graph divided into two subgraphs, we can utilize the *cut property* to optimally move from one to the other by choosing to traverse the minimum cost edge. In other words, it does not matter what happened before the current decision or what happens in subsequent decisions. It will not affect the optimality of the current decision. Thus, we can select the choice with the most obvious immediate advantage and be safe.
Continuously doing so leads to an optimal solution for finding the Minimum Spanning Tree.

Another problem type that is well suited for greedy algorithms is The Fractional Knapsack Problem, where it also produces the optimal solution. Note a greedy algorithm will not guarantee the optimal solution for The 0/1 Knapsack Problem. In some cases the optimal solution can be found greedily, other times it will lead to suboptimal solutions.  

!!! example "Suboptimal greedy solution for 0/1 Knapsack Problem"
    Consider set of coins $[1, 2, 5, 8]$, where we have an infinite amount of each coin value. Our knapsack capacity is 10. A greedy approach for this 0/1 knapsack problem will choose $8$ first and then have no more room, where obviously the optimal solution is two coins of value $5$.

## Problems ill-suited for greedy algorithms

A problem that is not well suited for greedy algorithms is The Travelling Salesman, where it will yield a solution, but no necessarily the optimal one.

!!! info
    The Travelling Salesman is a notoriously hard problem in computer science, so a greedy approach could be taken to find a suboptimal solution if the optimal one is too difficult to find for the given problem. For such cases, greedy algorithms can be a good way for estimation getting to a proof of concept solution.

Another problem that does not lend itself well to greedy algorithms is chess. Choosing the next move passed on only the board positions after that particular move is made will be a bad strategy and easy to beat by a decent player.

!!! tip
    In general, greedy algorithms are well understood and common problems where it can be used to find optimal solutions are known.
