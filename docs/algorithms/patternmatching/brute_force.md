# Brute force algorithm

The brute force algorithm to pattern matching is the naive implementation. It's simple and intuitive, but naturally not very efficient.

It checks every possible combination that can be a match, without using any "clever tricks" to skip parts of the text.

Other pattern matching algorithms dig deeper into the knowledge we get in each iteration to improve this baseline algorithm. This enables them to skip parts  of the text can be guaranteed to be a mismatch.

## Algorithm

!!! example "Implementation"

    ```python
    def brute_force(pattern, text):

        m = len(pattern)
        n = len(text)

        # To search the entire text, we will at maximum need to right-shift the 
        # pattern by 1 n - m times
        # j is the text index that index 0 of the pattern is currently aligned with
        for j in range(n - m):

            # Index of pattern 
            i = 0

            # Loop for checking if each character in the pattern matches
            while i < m - 1:

                # Check if the current character is a match
                if pattern[i] == text[i + j]

                    # The current character matches, check if we are done checking the entire pattern
                    if i < m - 1:
                        # There are characters left, continue
                        i += 1
                    else:
                        # The entire pattern has been matched
                        return j
                
                else:
                    # There is a mismatch
                    # Break out of the matching loop
                    # The outer loop will then shift the pattern down the text
                    break
    ```

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

## Time complexity

<center>

| Occurrences | Base case | Example best case | Worst case | Example worst case |
| ---- | :---: | --- | :---: | --- |
| None | $O(n)$ | *We never match any characters* | $O(mn)$ | *We match the entire pattern except the last character in each iteration (inner loop)* |
| Single | $O(m)$ | *We match the entire pattern in first try* | $O(mn)$ | *Same as above, except we match the entire pattern at the last $m$ characters of the text* |
| All | $O(n)$ | *We match the entire pattern in first try, but still have to check all the text* | $O(mn)$ | *Same as above* |

</center>

!!! note
    The trivial best case scenario is technically the pattern being longer than the text, but that is not considered in the table  above since it does not help to describe the performance of the algorithm.
