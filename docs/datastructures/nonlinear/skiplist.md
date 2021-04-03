# Skip List

A **Skip List** is a probabilistic data structure.

## Efficiency Overview

| Best case for operation | Time complexity |
|-------------------------|-----------------|
| Add                     | $O(1)$          |
| Remove                  | $O(1)$          |
| Search/Contains         | $O(1)$          |

| Average case for operation | Time complexity |
|----------------------------|-----------------|
| Add                        | $O(\log n)$     |
| Remove                     | $O(\log n)$     |
| Search/Contains            | $O(\log n)$     |

| Worst case for operation | Time complexity |
|--------------------------|-----------------|
| Add                      | $O(n)$          |
| Remove                   | $O(n)$          |
| Search/Contains          | $O(n)$          |

The space complexity for a Skip List is $O(n)$ since $n$ items need to be stored in memory.
