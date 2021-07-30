# Rabin-Karp

## Introduction

* Hashes the pattern in a preprocessing step

* Hashes substrings in the text to see if they match

* Often used in plagiarism detection

## Algorithm

1. Compute the hash for the pattern and the initial hash for the first $m$ length substring of the text

2. Compare the pattern and text hashes

3. If the two hashes

    1. Match, perform  brute force comparison in a character by character manner. Even though the hashes are equal, the substring might not be equal to the pattern due to the possibility of hash collisions.

    2. Don't match, continue without comparing

4. Compute the rolling hash of the next substring and go to Step 2

In order for Rabin-Karp to be competitive from a time complexity standpoint, the hash function must be $O(1)$. In total, there are $n-m$ substring of length $m$ in the text.

Consequently, having a linear hash function of $O(m)$ would result in a complexity just for the hashing operations of $O(m(n-m)) \rightarrow O(nm)$. This would not be competitive, since that is similar to worst case for the brute force algorithm.

But in order to hash a string, there is noway around looking at the entire string.

!!! tip "Rolling hash"
    The Rabin-Karp algorithm often uses a **rolling hash** computation. At reach "roll" one character is taken away from the beginning and one is added to the end. This saves us from having to look at the entire $m$-length string at each hash operation. We can reuse the hash from indices $[1:end]$ and

## Rabin-Karp rolling hash computation

1. Choose a **base** $b$
2. Choose a **hash function** $h(x)$ to map single characters to integers
3. Compute the **hash of the pattern** as shown in the formula below

$$
H(p) = h(p[0]) \cdot b^{m-1} \ + \ \dots \ + \ h(p[m-2]) \cdot b^1 + h(p[m-1]) \cdot b^0
$$

The equation weighs the characters in the strings based on their positions.

The base $b$ should be chosen as a large prime number to avoid hash collisions.
The hash function is generally chosen as ASCII or some other form of character-to-integer mapping.

```python
from typing import Callable
from dataclasses import dataclass

class RabinKarp:
    pattern: str
    # text: str = None
    hash_function: Callable[[int], int]
    base: int

    def initial_hash(self) -> int:
        
        # Construct list with decreasing 
        exponents = [m - i for i in range(len(self.pattern))]
        
        summation = 0
        for i, exp in in zip(self.pattern, exponents): 
            sum += self.hash_function(i) * self.base**exp
        return summation

    def rolling_hash():
        # TODO:
        pass
```

An example hash function could be

```python
def hash_function(x: int) -> int:
    """A hash function that simply returns it input."""
    return x

```

!!! example "Initial hash calculation"
    * Pattern: $p=2415 \quad (m=4)$
    * Base: $b=10$
    * Hash function: Digits map to themselves, $h(x)=x$ where $x \in \mathbb{Z}$.

    \begin{align}
    H(2415) &= h(p[0]) \cdot 10^{4-1} + h(p[1]) \cdot 10^{4-2} + h(p[2]) \cdot 10^{4-3} h(p[3]) \cdot 10^{4-4} \\ \\
    H(2415) &= 2 \cdot 10^3 + 4 \cdot 10^2 + 1 \cdot 10^1 + 5 \cdot 10^0 \\ \\
    H(2415) &= 2415
    \end{align}

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

<\center>
