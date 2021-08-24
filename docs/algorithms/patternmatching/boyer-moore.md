# Boyer-Moore

## Introduction

The Boyer-Moore algorithm preprocesses the pattern to generate a so-called **last occurrence table**, optimizing shifts after mismatches.

* **Performs best with large alphabets**, i.e. when the text and patters consist of characters taken from a large character pool

    * Having a large alphabet increases the probability of a character being in the text but not in the pattern. This is the case in which the algorithm excels because we can ignore large chunks of data by shifting.

* The movement in the pattern while comparing to the text is **right-to-left**

* **In case of a mismatch, checks whether the current character exists in the last-occurrence-table**
    * **If it exists:** a shift is made to move the pattern forward. The shift aligns the mismatched pattern character from before with the text character that matched the one in the last occurrence table.

    * **If it does not exist:** a shift is made to move the entire pattern past the character that did not exist.

* **After a full pattern match, we perform a naive shift by $1$**. Of course this is provided that we are not only looking for the first occurrence.

!!! note
    The Boyer-Moore algorithm explained in this document is a simplified version, which **does not** implement the *Good Suffix Rule*. The implementation of the *Good Suffix Rule* is rather complex and does not aid the performance of the algorithm much.

## The last occurrence table

The last occurrence table is produced by preprocessing the pattern. The table forms a map with keys of each of the characters present in the pattern and values with the index in which the key last occurs in the pattern.

!!! example "Example 1: Last occurrence table"
    Build the last occurrence table for pattern: $happy$

    | Character | Index |
    | :-------: | :---: |
    |    $h$    |  $0$  |
    |    $a$    |  $1$  |
    |    $p$    |  $3$  |
    |    $y$    |  $4$  |
    |    $*$    | $-1$  |

    $*=-1$ represents characters that do not occur in the pattern.

!!! example "Example 2: Last occurrence table"
    Build that last occurrence for the pattern: $aaababacacab$

    | Character | Index |
    | :-------: | :---: |
    |    $a$    | $10$  |
    |    $b$    | $11$  |
    |    $c$    |  $9$  |
    |    $d$    | $-1$  |

    $d=-1$ since it does not occur in the pattern.

## Time complexity

The **best case** for a *single match* occurs when the pattern of size $m$ occurs at the first $m$ indices of the text. The time complexity here is $O(m)$, since we must traverse through the entire $m$-sized pattern to realize that there is a match.

For finding *all occurrences*, the best case is $O(m+n/m)$. This scenario happens when:

* A mismatch happens at the first character that is compared in each iteration
* The mismatched character in the text does not occur in the pattern

In this case, the algorithm shifts $m$ locations down the list in each iteration.

!!! note
    The Boyer-Moore algorithm will always require at least $O(m)$ time to preprocess the pattern.

The worst case time complexity is $O(nm)$. If there is a mismatch at the first character in each iteration, but we can't shift more than one location each time, we are essentially back at the brute force algorithm. The reason why shifting is not possible in this case is that the mismatched character is not in the last occurrence-table.

!!! info "Right-to-Left matching"
    When considering the patterns and text for the different cases shown in the time complexity table below, remember that **the Boyer-Moore algorithm compares characters from right to left**. The pattern moves from left to right over the text, but at each pattern anchor location, we compare characters right to left.

<center>

| Occurrences | Base case  | Example best case                      | Worst case | Example worst case                      |
| ----------- | :--------: | -------------------------------------- | :--------: | --------------------------------------- |
| None        | $O(m+n/m)$ | **Pattern:** xxx <br>**Text:** yyyyyyy |  $O(mn)$   | **Pattern:** yxx <br>**Text:** xxxxxxx  |
| Single      |   $O(m)$   | **Pattern:** xxx <br>**Text:** xxxxxxx |  $O(mn)$   | **Pattern:** yxx <br>**Text:** xxxxxxx  |
| All         | $O(m+n/m)$ | **Pattern:** xxy <br>**Text:** xxxxxxy |  $O(mn)$   | **Pattern:** yxx <br>**Text:** xxxxxyxx |

</center>

### Space complexity

The space complexity of Boyer-Moore comes from storing the last occurrence table and is $O(\min(m, s))$. $m$ denotes the length of the pattern and $s$ the length of the alphabet. The last occurrence table can have size $m$ if all characters in the pattern are distinct. But if the alphabet is smaller than the pattern, it will be size $s$ instead since duplicates must be present then.

Note that this doesn't include the space required for the pattern and the text, since those are inputs and already allocated before calling the algorithm.

## Counting comparisons

!!! example
    Determine the amount of comparisons made by running Boyer-Moore on the text and the pattern.

    **Pattern:** $xxxxxy$ $(m=6)$

    **Text**: $xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxy$ $(n=42)$

    ---

    Just like the brute force algorithm, we are only moving one letter down the text in each iteration in, resulting in $n-m+1$ shifts. The reason we are shifting only one each time is that we are mismatching on the first comparison each time until we hit the very last location the pattern can be placed in (comparing right-to-left).
  
    We subtract $m$ because we start by aligning the first character in the pattern by the first character in the text, thereby "saving" $m$ shifts initially.

    * In each of the $n$ shifts, we are performing only a single comparison to realize that we can continue to the next character.
    * At the last character, we find the full match and perform $m$ comparisons.

    The number of comparisons made is
    $$ n_{\text{comparisons}} = n - m + m = n $$
    $$ n_{\text{comparisons}} = 41 $$

## Diagramming

![boyer-moore-diagramming-example](/algorithms/patternmatching/media/boyer-moore-diagramming.svg)

The shift steps are explained below.

1. Text character **a** mismatch with pattern character **b**.
Character **a** appears in the last occurrence table with index $4$.
Shift pattern so index $4$ aligns with the mismatched text character **a**.

2. Text character **a** mismatch with pattern character **c**.
Character **a** appears in the last occurrence table with index $4$.
Shifting the pattern so index $4$ aligns with the mismatched text character **a** would shift the pattern backward. This does not make sense, so all we can do is shift the pattern by $1$.

3. Text character **a** mismatch with pattern character **b**.
Character **a** appears in the last occurrence table with index $4$.
Shift pattern so index $4$ aligns with the mismatched text character **a**.

4. Text character **d** mismatch with pattern character **b**.
Character **d** does not appear in the last occurrence table.
We can therefore shift the entire pattern past this mismatch.

5. Text character **a** mismatch with pattern character **b**.
Character **a** appears in the last occurrence table with index $4$.
Shift pattern so index $4$ aligns with the mismatched text character **a**.

6. All characters match, i.e. we have a full match. Shift the pattern naively by $1$.

7. Same scenario as for comparison $7$. Align pattern's index $4$ with the mismatched text character **a**.

8. Same scenario as above.

9. Exact same scenario as for comparisons $2$, $3$ and $4$. Shift pattern by $1$.

10. Text character **b** mismatch with pattern character **a**. We reached
the end of the text so we are done.

## Implementation

???+ example "Boyer-Moore implementation"
    === "Java"
        ```java linenums="1"
        --8<--
        docs/algorithms/patternmatching/code/java/src/BoyerMoore.java
        --8<--
        ```

    The `CharacterComparator` class is written in it's own file showed below

    === "Java"
        ```java linenums="1"
        --8<--
        docs/algorithms/patternmatching/code/java/src/CharacterComparator.java
        --8<--
        ```
