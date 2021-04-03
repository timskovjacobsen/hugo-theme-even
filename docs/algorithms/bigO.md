# Big-O Notation

Big-O is theoretical performance analysis of algorithms. It explores how the performance of an algorithm scales as the input size approaches infinity. The performance evaluation is most often done by considering the *worst-case* input. Thus, Big-O is generally an upper-bound performance evaluation.

The analysis is done by considering the amount of *primitive* operations that an algorithm contains. The computational complexity is summed up in a function $f(n)$, where $n$ is the input size.

!!! info
    At first glance, the considering the *average-case* input might be the best way to measure the performance of an algorithm. However, since an algorithm might have different performance for two inputs of the same size, evaluation of the average case involves computation of the probability distribution of possible inputs.  
    Due to this, the it is most often the *worst-case* input that is considered when analyzing computational complexity.

Big-O can be used to measure both *time* and *space* complexity. Most often time complexity is of highest interest and that is likely to be what is talked about if people don't mention which.

## Computational Time Complexity

The computational time complexity of an algorithm is often denoted in Big-O notation.

The most used notations given in ascending order of time complexity are listed below.

$$
O(1) < O(\log_2 n) < O(n) < O(n \log_2 n) < O(n^2) < O(n^3) < O(2^n)
$$

Or in words

$$
\text{constant} < \text{log} < \text{linear} < \text{n log n} < \text{quadratic}
< \text{cubic} < \text{exponential}
$$

## Computational Space Complexity

Space complexity is used to describe the space efficiency of an algorithm. Often times when talking about algorithmic complexity, it is *time* complexity that is meant rather than space complexity.

## Measuring time efficiency of an algorithm

Take a function of primitive operations $f(n)$, where $n$ is the size of the input.
In most cases when talking about Big-O analysis, it is the most accurate **Worst-Case performance** that is of interest. This means the worst performance while treating the worst set of data.

Other performance measures are **Best-Case** and **Average-Case**, where the Average-Case is quite difficult to compute.

## Primitive operations

- Assigning a value
- Arithmetic operations
- Comparing two entities
- Function or method calls
- Accessing or referencing entities

All primitive operations execute in constant time, $O(1)$.

## Determining the time complexity

Some conventions for writing Big-O notations are described below. In all cases, $n$ is the size of the input that an algorithm receives.

1. **Constants are ignored**  

    Suppose $C$ is a constant, then
    $$ O(C n) \rightarrow O(n) $$

    $$ O(8n) \rightarrow O(n) $$

    Constants are ignored, since they are negligible as $n$ goes towards infinity.

    !!! note
        For theoretical analysis, constants are ignored as described above. However, in practice these factors *can* play a significant role depending on the given algorithm, especially if the constants are very large.

2. **Lower order terms are ignored**  

    When an expression holds multiple terms of different order, only the highest  order term prevails.

    $$ O(n² + 200n + n \log(n)) \rightarrow O(n²) $$

    $$ O(10n + n \log(n) + 10) \rightarrow O(n \log(n))$$

## Simplification of expressions

When inspecting an algorithm, one often record the terms and factors of the code into an
an expression that can then be simplified. Some simplification examples are given by the conventions above due to order of magnitude. Some other examples simplification of mathematical nature are are given below.

### $n$ as denominator

Consider the expression below, where $C_1$ and $C_2$ are constants

$$ O \left( C_1 + \frac{C_2}{n} \right)  \rightarrow O(1) $$

reduces to constant time since the term $C_2/n$ will go towards zero as $n$ goes towards infinity.

## Amortized Analysis

**TODO:**

## Examples

## Sorting

The most efficient sorting algorithms can be run in $O(n \log n)$ time.

*Merge sort* runs in $O(n \log n)$ time. Python uses another algorithm called
*Tim sort* which is roughly $O(n \log n)$ time.
