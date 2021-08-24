from typing import Callable, Union


def simple_hash_function(x: Union[int, str]) -> int:
    """A simple hash function mapping a character to an integer.

    Parameters
    ----------
    x
    A character to map to an integer.

    Returns
    -------
    If the input is an integer, returns itself.
    If the input is a string, returns its ASCII code integer value.
    """
    return ord(x) if isinstance(x, str) else x


def initial_hash(pattern: str, base: int, hash_function: Callable = None) -> int:

    if hash_function is None:
        hash_function = simple_hash_function

    m = len(pattern)

    summation = 0
    for i, char in enumerate(pattern, start=1):
        summation += hash_function(char) * base ** (m - i)
    return summation


def rolling_hash(
    hash_current: str,
    pattern_current: str,
    char_new: str,
    base: int,
    hash_function: Callable = None,
):
    """Return the rolling hash of a pattern.

    The computation removes the first character and adds a new one and updates the entire hash of the string by only considering those two characters.
    """

    if hash_function is None:
        hash_function = simple_hash_function

    m = len(pattern_current)
    char_old = pattern_current[0]

    h = hash_function
    return base * (hash_current - h(char_old) * base ** (m - 1)) + h(char_new)


if __name__ == "__main__":
    s1 = "Stack"
    hash_initial = initial_hash(pattern=s1, base=2)

    print(hash_initial)

    hash_rolling1 = rolling_hash(
        hash_current=hash_initial,
        pattern_current=s1,
        char_new="O",
        base=2,
    )
    print(hash_rolling1)

    print("NEW ----------------")

    hash_rollingNEW = rolling_hash(
        hash_current=4926,
        pattern_current="LSDRad",
        char_new="i",
        base=2,
    )
    print(hash_rollingNEW)
