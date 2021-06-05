# Boyer-Moore

The Boyer-Moore algorithm preprocesses the pattern to generate a so-called **last occurrence table**, optimizing shifts after mismatches.

* Performs best with large alphabets, i.e. when the text and patters consist of characters taken from a large character pool
  * Having a large alphabet increases the probability of a character being in the text but not in the pattern. This is the case case in which the algorithm excels because we can ignore large chunks of data by shifting.

* Movements in the pattern is **right-to-left**

* In case of a mismatch, checks whether the current character exists in the last-occurrence-table
  * If it exists, a shift is made to move the pattern forward. The shift aligns the mismatched character in the pattern the matching character in the text
  * If is does not exist, a shift is made to move the entire pattern past the character that did not exist

* After a match, we perform a naive shift by $1$. Of course this is provided that we are not only looking for the first occurrence.

!!! note
    The Boyer-Moore algorithm explained in this document is a simplified version, which **does not** implement the *Good Suffix Rule*. The implementation of the *Good Suffix Rule* is rather complex and does not aid much in performance.

## The last occurrence table

The last occurrence table is produced by preprocessing the pattern. The table forms a map with keys of each of the characters present in the pattern and values with the index in which the key last occurs in the pattern.

!!! example "Example 1: happy"
    Pattern: $happy$

    | Character | Index |
    | :-------: | :---: |
    |    $h$    |  $0$   |
    |    $a$    |  $1$   |
    |    $p$    |  $3$   |
    |    $y$    |  $4$   |
    |    $*$    |  $-1$   |

    $*=-1$ since it does not occur in the pattern

!!! example "Example 2: Last occurrence table"
    Pattern: $aaababacacab$

    | Character | Index |
    | :-------: | :---: |
    |    $a$    |  $10$   |
    |    $b$    |  $11$   |
    |    $c$    |   $9$   |
    |    $d$    |  $-1$   |

    $d=-1$ since it does not occur in the pattern

## Time complexity

The **best case** for a *single match* occurs when the pattern of size $m$ occurs at the first $m$ indices of the text. The time complexity here is $O(m)$, since we must traverse through the entire $m$-sized pattern to realize that there is a match.

For finding *all occurrences*, the best case is $O(m+n/m)$. This scenario happens when:

* A mismatch happens at the first character that is compared in each iteration
* The mismatched character in the text does not occur in the pattern

In this case, the algorithm shifts $m$ locations down the list in each iteration.

!!! note
    The Boyer-Moore algorithm will always require at least $O($m$)$ to preprocess the pattern.

The worst case time complexity is $O(nm)$. If there is a mismatch at the first character in each iteration, but we can't shift more than one location each time, we are essentially back at the brute force algorithm. The reason why shifting is not possible in this case is that the last-occurrence-table **<TODO: FINISH SENTENCE>**.

<center>

| Occurrences | Base case  | Example best case                      | Worst case | Example worst case                      |
| ----------- | :--------: | -------------------------------------- | :--------: | --------------------------------------- |
| None        | $O(m+n/m)$ | **Pattern:** xxx <br>**Text:** yyyyyyy |  $O(mn)$   | **Pattern:** yxx <br>**Text:** xxxxxxx  |
| Single      |   $O(m)$   | **Pattern:** xxx <br>**Text:** xxxxxxx |  $O(mn)$   | **Pattern:** yxx <br>**Text:** xxxxxxx  |
| All         | $O(m+n/m)$ | **Pattern:** xxy <br>**Text:** xxxxxxy |  $O(mn)$   | **Pattern:** yxx <br>**Text:** xxxxxyxx |

</center>

## Counting comparisons

!!! example
    **Pattern:** $xxxxxy$ $(m=6)$

    **Text**: $xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxy$ $(n=42)$

    * Just like the brute force algorithm, We are only moving one letter down the text in each iteration, resulting in $n-m+1$ shifts. The reason we are shifting one each time is that we realign the pattern with the last occurring x we have seen.  
    We subtract $m$ because we start by aligning the first character in the pattern by the first character in the text, thereby "saving" $m$ shifts initially.
    * In each of the $n$ shifts, we are performing only a single comparison to realize that we can continue to the next character.
    * At the last character, we find the full match and perform $m$ comparisons.

    The number of comparisons made is
    $$ n_{\text{comparisons}} = n - m + m = n $$
    $$ n_{\text{comparisons}} = 41 $$

## Implementation

!!! example "Boyer-Moore implementation"
    === "Java"
        ```java linenums="1"
        --8<--
        docs/algorithms/sorting/iterative_sorts/code/java/BubbleSort.java
        --8<--
        ```
