# Functions

```c linenums="1"
--8<--
docs/languages/C/code/apply_funcs.c
--8<--
```

The output will be

    {20, 40, 60, 80, 100, 120, 140, 160}
    {60, 120, 180, 240, 300, 360, 420, 480}

Note that the function `int_func` is a custom-made type and is used an an input type for `apply_func()`. We might as well have had the function read

`#!c void apply_func(int *items, int n, int (*func) (int))`

Which would be a valid way to express the type of the function. It's often more readable to define these more complex types separately.

The `#!c int (*func) (int)` part more specifically denotes *pointer* to a function that

* Returns an `int`
* Is called `func` in the local scope of the function
* Takes an `int` as input

So, this parameters is actually a pointer to the function, not the  function itself.

Just like with arrays, with functions we don't need to use the *address-of* operator `&` to get the memory location. It's implicit.
This means that when we call `#!c apply_func(arr, n, triple)`, it's the same as `#!c apply_func(arr, n, &triple)`.

## Function return values

The `main()` function is what gets run when the program runs. A common way to indicate either success or failure is to `#!c return EXIT_SUCCESS` or `#!c return EXIT_FAILURE`.

If we want the program to error out immediately once the hit a failure, we can use `#!c exit(EXIT_FAILURE)`.

## Passing string literals to a function

When we encounter `#!c const char *s` as a function argument, it means the functions expects a string literal.
