# Brute force algorithm

## Time complexity

<center>

| Occurrences | Base case | Example best case | Worst case | Example worst case |
| ---- | :---: | --- | :---: | --- |
| None | $O(n)$ | --- | $O(mn)$ | --- |
| Single | $O(m)$ | --- | $O(mn)$ | --- |
| All | $O(n)$ | --- | $O(mn)$ | --- |

</center>

!!! note
    The trivial best case scenario is technically the pattern being longer than the text, but that is not considered in the table  above since it does not help to describe the performance of the algorithm.

## Counting comparisons

!!! example "Worst case comparisons"
    * Pattern: $xxxxxy$ ($m=6$)

    * Text: $xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxy$ ($n=42$)

    We are only moving one letter down the text in each iteration, this means $n-m+1$ shifts. We subtract $m$ because we start by aligning the first character in the pattern by the first character in the text, thereby "saving" $m$ shifts initially.
    
    In each of the $n$ shifts, we are performing $m$ comparisons.

    The number of comparisons made is
    $$ n_{\text{comparisons}} = (n-m+1)m $$
    $$ n_{\text{comparisons}} = (41-6+1)6 = 222 $$

!!! example
    * Pattern: $baaa$ $(m=4)$

    * Text: $aaaaaaaaaabaaa$ $(n=14)$

    In this case, we can at each of the $(n-m)$ shift realize that the pattern is not a match. We are searching for $b$, but finding $a$ in each iteration.

    When we find the $b$ at index $(n-m)$, we find the entire pattern to match.

    Thus, the total number of comparisons is
    $$
    n_{\text{comparisons}} = n - m + m = n
    $$
