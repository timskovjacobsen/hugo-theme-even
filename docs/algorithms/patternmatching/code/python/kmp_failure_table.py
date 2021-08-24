def compute_failure_table(pattern: str) -> list:
    """Return the failure_table for the input pattern. Time complexity: O(m)."""

    m = len(pattern)
    failure_table = [0 for _ in range(m)]
    i, j = 0, 1

    while j < m:
        if pattern[i] == pattern[j]:
            failure_table[j] = i + 1
            i += 1
            j += 1
        elif pattern[i] != pattern[j] and i == 0:
            failure_table[j] = 0
            j += 1
        else:
            i = failure_table[i - 1]

    return failure_table


if __name__ == "__main__":
    p1 = "abababc"
    print(compute_failure_table(p1))  # Prints: [0, 0, 1, 2, 3, 4, 0]

    # NOTE: At index 5, we are looking at the substring "ababa". At index 4 we had a
    # value of two and we are trying to increase it to 3 at index 5.
    # Since we have only five characters and would like to create a prefix and suffix
    # with three characters each, the middle character must overlap and be part of both
    # prefix and suffix.

    print(compute_failure_table("AABAAACABAABBCAABA"))
    # Prints: [0, 1, 0, 1, 2, 2, 0, 1, 0, 1, 2, 3, 0, 0, 1, 2, 3, 4]
