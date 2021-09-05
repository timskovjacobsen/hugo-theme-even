# Strings

There are two basic ways for defining strings in C.

1. Via a pointer to a string literal
2. Via a character array copied from a string literal

## String literals

!!!tip ""
    A string literal is the formal C nomenclature for a string written with double quotes `""`.

String literals are stored in read-only memory as constants. They cannot be changed.

While it's legal in C to create a string literal with `#!c char *s = "hello"` it's better practice to create it with the `const` modifier

```c
const char *s = "hello"
```

This way, we can signal to the compiler that the string literal is supposed to be constant thereby helping us avoid such issues.

!!!example "Always use `const` when defining a string literals"

    If we define a string literal without the `#!c const` keyword, line 6 in the code below will result in a `Segmentation fault (core dumped)` error message.

    ```c linenums="1" hl_lines="6"
    #include <stdio.h>
    #include <stdlib.h>

    int main(void) {
        char *string_literal = "hello";
        string_literal[2] = string_literal[1];
        printf("%s\n", string_literal);
        return EXIT_SUCCESS;
    }
    ```

    The reason is not obvious, but it's because we are trying to modify 
