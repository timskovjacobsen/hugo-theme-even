# Rabin-Karp

## Introduction

* Hashes the pattern in a preprocessing step

* Hashes substrings in the text to see if they match

* Often used in plagiarism detection

The Rabin-Karp algorithm shifts the pattern over the text one spot at a time just like the brute force algorithm. The algorithm wins its efficiency by only comparing the individual characters if the hash of the strings are matching.
This is unlike other pattern matching algorithms like Boyer-Moore and Knuth-Morris-Pratt, where we might search part of the string just to realize that we don't have a full match.

## Algorithm

1. Compute the hash for the pattern and the initial hash of the first substring of the text with length $m$

2. Compare the pattern and text hashes

3. If the two hashes

    1. Match: perform  brute force comparison in a character by character manner. Even though the hashes are equal, the substring might not be equal to the pattern due to the possibility of hash collisions.

    2. Don't match: continue without comparing

4. Shift the pattern over by one, compute the rolling hash of the next substring and go to Step 2

In order for Rabin-Karp to be competitive from a time complexity standpoint, the hash function must be $O(1)$. In total, there are $n-m$ substring of length $m$ in the text.

Consequently, having a linear hash function of $O(m)$ would result in a complexity just for the hashing operations of $O(m(n-m)) \rightarrow O(nm)$. This would not be competitive, since that is similar to worst case for the brute force algorithm.

But in order to hash a string, there is noway around looking at the entire string, so how can we hash in linear time?

!!! tip "Rolling hash"
    The Rabin-Karp algorithm often uses a **rolling hash** computation. At reach "roll" one character is taken away from the beginning and one is added to the end. This saves us from having to look at the entire $m$-length string at each hash operation. We can reuse the hash from indices $[1:\text{end}]$, which becomes indices $[0, \text{end}-1]$ in the new substring.

## Rabin-Karp rolling hash computation

1. Choose a **base** $b$
2. Choose a **hash function** $h(x)$ to map single characters to integers
3. Compute the **initial hash of the pattern** as shown in the formula below

    $$
    H(p) = h(p[0]) \cdot b^{m-1} \ + \ \dots \ + \ h(p[m-2]) \cdot b^1 + h(p[m-1]) \cdot b^0
    $$

    The equation weighs the characters based on their index in the string.

4. Update the hash by this **rolling hash** formula

    $$
    H_{new}(p) = b \cdot (H_{current}- h(c_{remove}) \cdot b^{m-1}) + h(c_{new})
    $$

    where

    * $H_{current}$ denotes the current hash value that that needs to be updated. I.e. the hash from which we want to remove the first character and add a new character
    * $c_{remove}$ denotes the character to be removed, i.e. the first character in the string that was previously hashed
    * $c_{new}$ is the new character to be added at the end of the string

The base $b$ should be chosen as a large prime number to avoid hash collisions.
The hash function is generally chosen as ASCII or some other form of character-to-integer mapping.

!!! example "Hash computation"
    ```python linenums="1"
    --8<--
    docs/algorithms/patternmatching/code/python/rabin_karp.py
    --8<--
    ```

!!! example "Initial hash calculation"

    * Pattern: $p=2415 \quad (m=4)$
    * Base: $b=10$
    * Hash function: Integers map to themselves, $h(x)=x$ where $x \in \mathbb{Z}$.

    \begin{align}
    H(2415) &= h(p[0]) \cdot 10^{4-1} + h(p[1]) \cdot 10^{4-2} + h(p[2]) \cdot 10^{4-3} h(p[3]) \cdot 10^{4-4} \\ \\
    H(2415) &= 2 \cdot 10^3 + 4 \cdot 10^2 + 1 \cdot 10^1 + 5 \cdot 10^0 \\ \\
    H(2415) &= 2415
    \end{align}

!!! example "Initial and rolling hash calculation"

    * String: $\text{Stack} \ \ (m = 5)$

    * Base: $2$

    * Hash function: Integers map to themselves and characters to their ASCII value in decimal notation

    <center>

    | Character  | ASCII value in decimal |
    | :--------: | :--------------------: |
    | $\text{S}$ | $83$                   |
    | $\text{t}$ | $116$                  |
    | $\text{a}$ | $97$                   |
    | $\text{c}$ | $99$                   |
    | $\text{k}$ | $107$                  |
    | $\text{O}$ | $79$                   |

    </center>

    Initial hash computation for $\text{Stack}$

    $$
    H = 83 \cdot 2^{5-1} + 116 \cdot 2^{5-2} + 97 \cdot 2^{5-3} + 99 \cdot 2^{5-4} + 107 \cdot 2^{5-5} = 2949
    $$

    First rolling hash computation, adding $\text{O}$

    $$
    H_{new,1} = 2 \cdot (2949 - 83 \cdot 2^{5-1}) + 79 = 3321
    $$

### Counting comparisons

Direct comparisons are only made if the hash of the pattern and the hash of the text substring are equal. In that case, at most $m$ comparisons will be made in order to test for the match.

If a mismatch is realized early, the comparisons will of course terminate. Although that will only happen in case of hash collisions, which are rare for decently chosen hash functions.

## Time complexity

For Rabin-Karp to be considered efficient, the hashing function must be an $O(1)$ operation.

In total, the hash must be computed $n-m$ times. We subtract $m$ because we don't need to hash the last part of the text, where substrings are smaller than the pattern. They cannot possibly match.

<center>

| Occurrences | Best case efficiency | Worst case efficiency |
| ----------- | -------------------- | --------------------- |
| None        | $O(m+n)$             | $O(mn)$               |
| Single      | $O(m)$               | $O(mn)$               |
| All         | $O(m+n)$             | $O(mn)$               |

The **space complexity** of the Rabin-Karp algorithm is $O(m \log b)$, where $b$ is the base and $m$ is the length of the pattern.
