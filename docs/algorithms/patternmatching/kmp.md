# Knuth-Morris-Pratt (KMP)

## Introduction

* Failure table

Most notably, the KMP algorithm is smart enough to not compare characters that have already been identified as a match. Even after shifting the pattern, this efficiency is maintained.

## Failure table

The pattern is preprocessed by generating a failure table. The failure table has these characteristics.

* $f[i]$ is the length of the longest proper suffix that is also a prefix of the slice $p[$0$:1]$. Here, $p$ is the strng representing the pattern.

* $j$ is the index of the character we are probing, i.e. testing for a new match. We refer to $j$ as the *query index*.
    * $j$ only moves forward or remain at the index it is, it never moves backwards.
    * In the iteration where $j$ moves beyond the table boundary, the algorithm terminates.
    * If $j$ moves forward in each iteration, the time complexity becomes $O(m)$
    * If $j$ remains at the same index, $i$ moves backwards by one.
    * $j$ remains at the same index in at most $m$ iterations

* $i$ is the index of the character that sits one spot past the longest prefix that was just found. We refer to $i$ as the *prefix index*.
    * $i>=0$
    * The largest possible increment for $i$ is 1
    * $i$ can at most move backwards $m$ times

Based on the above, it can be concluded that the time complexity of generating the failure table is $O(2m) \implies O(m)$.

!!! example
    Pattern: $abadabk$

    Failure table:

    <center style="box-sizing: border-box">

    |  $i$ :  |  $0$  |  $1$  |  $2$  |  $3$  |  $4$  |  $5$  |  $6$  |
    | :-----: | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
    | $p[i]$: |  $a$  |  $b$  |  $a$  |  $d$  |  $a$  |  $b$  |  $k$  |
    | $f[i]$: |  $0$  |  $0$  |  $1$  |  $0$  |  $1$  |  $2$  |  $0$  |

    </center>

The count becomes $1$ at index $4$ because the prefix and suffix $a$ are equal. At index $5$, both the prefix and the suffix of $p[0:i]$ are equal to $ab$. Thus, we report a length of $2$.

The worst case comparison count for the failure table is $2m$ comparisons.

## Implementation

???+ example "Implementation"

    === "Java"
        ```java linenums="1"
        --8<--
        docs/algorithms/patternmatching/code/java/src/KnuthMorrisPratt.java
        --8<--
        ```

    === "Python"
        ```python linenums="1"
        --8<--
        docs/algorithms/patternmatching/code/python/kmp_failure_table.py
        --8<--
        ```

## Algorithm

* When a mismatch occurs, the failure table $f$ is queried for the *pattern index* $i$, i.e. we get $f[i]$.

* When a mismatch occurs, we shift the pattern to get ready for a new match check with the text. The shift is done in a way so that all characters that matched before the mismatch will still match after the pattern shifts.

    This part is quite important because it sets KMP apart from e.g. the Boyer-Moore algorithm.

* The movement in the pattern while comparing to the text is **left-to-right**. This is in contract to Boyer-Moore, which compares in *reverse* order.

The algorithm considers three cases

1. **TODO**

2. **TODO**

3. The character in the text and the pattern mismatched.
This is where the cleverness of the KMP algorithm shows. **TODO**.

The worst case comparison count for the KMP algorithm is $2n$ comparisons.

!!! example "Pseudocode"

    ```python
    def kmp_search(text: str, pattern: str) -> bool:
        """KMP algorithm for checking if a pattern occurs in a text."""
        n = len(text)
        failure_table = compute_failure_table(text, pattern)

        # Indices for text and pattern
        i, j = 0, 0

        while i < n:

            # Check if characters match
            if text[i] == pattern[j]
                #  --- CASE 1 ---
                # The characters match
                if j = m - 1:
                    # We are at the end of the pattern and all characters matched
                    return True
                else:
                    # There are still characters left in the pattern, advance to next
                    i += 1
                    j += 1

            # Check if we are at first index in the pattern
            else if text[i] != pattern[j] and j = 0:
                # --- CASE 2 ---
                # We are at first pattern index, we have not enough information to
                # perform a clever shift. Move text index by one
                i += 1
            else:
                # --- CASE 3 ---
                # We are can perform a clever shift
                # Shift the pattern so the prefix of the pattern aligns with a suffix
                # of the text that was a match
                j = failure_table[j - 1]

        # If we reach this part, there was no match
        return False
            
    ```

    **TODO: ADD JAVA IMPLEMENTATION!!!**

## Time complexity

The table below shows the time complexity for the KMP algorithm.
<center>

| Occurrences | Best case  | Worst case |
| ----------- | ---------- | ---------- |
| None        | $O(m + n)$ | $O(m + n)$ |
| Single      | $O(m)$     | $O(m + n)$ |
| All         | $O(m + n)$ | $O(m + n)$ |

</center>

* Worst case: $O(m + n)$
    * $O(m)$ for generating the failure table
    * $O(n)$ for traversing each character (at least) once

**Thus, the KMP algorithm is linear in the worst case and therefore more efficient than the Boyer-Moore algorithm.**

The space complexity of the KMP algorithm is $O(m)$, which amounts to storing the failure table.
We ignore the text and pattern strings when talking space complexity because they are only inputs and not something the algorithm produces.

## Examples

!!! example 1
    This example is from the [csvistool](https://csvistool.com/KMP).
    It reports matches in green, mismatches in red and preprocessed sub patterns in yellow. The preprocessed patterns are skipped in subsequent iterations, which is where the KMP algorithm excels.

    ![kmp example from vistool](/algorithms/media/kmp_example.png)

    The algorithm terminates after $22$ comparisons (green and red cells).

## References

* [Data Structures & Algorithms IV: Pattern Matching, Dijkstra's, MST, and Dynamic Programming Algorithms](https://www.edx.org/course/data-structures-algorithms-iv-pattern-matching-djikstras-mst-and-dynamic-programming-algorithms)
* [csvistool](https://csvistool.com/KMP)
