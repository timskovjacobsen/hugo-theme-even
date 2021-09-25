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

This way, we can signal to the compiler that the string literal is supposed to be constant thereby causing an exception should we try to modify the string. Leaving out `#!c const` will let the program compile, but later segfault when we are trying to access illegal memory.

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

For more info on read-only vs. stack memory, see [this StackOverflow answer](https://stackoverflow.com/questions/164194/why-do-i-get-a-segmentation-fault-when-writing-to-a-char-s-initialized-with-a/69005668#69005668).

## Char pointer arithmetic

If we add a number to a char pointer, we are adding to the char's numerical value, i.e. it's ascii value.

```c
#include <stdio.h>
#include <stdlib.h>

int main(void) {
  const char *ptr = "hello";

  // We loop over the string, adding 3 to each character's ascii value
  while (*ptr != '\0') {
    printf("%c + 3 --> %c", *ptr, *ptr + 3);
    ptr++;
    printf("\n");
  }
  printf("\n");
  return EXIT_SUCCESS;
}
```

## Array of strings

Since strings are actually arrays of characters, an array of strings is treated as an arrays of arrays, or a 2D array.

For 2D arrays, the compiler can infer the first dimension, i.e. how many strings there are. But the second dimension must be given explicitly with a size that all strings must fit into, including the null terminator `\O`. This can be a huge waste of memory, since all strings shorter than the longest will have unused memory tied to them.

!!! warning "Don't use an array of strings"
    Therefore, defining an array of strings this is **not recommended**

    ```c
    char strings[][8]{"apples", "oranges", "bananas", "pears"};
    ```

!!! tip "Use an array of pointers to strings"

    The best practice is to use an array of pointers.

    ```c
    #include <stdio.h>
    #include <stdlib.h>

    int main(void) {
    // Define and array of pointers to strings
    // Recall that this this is actually a pointer to the first element in
    // the array, i.e. the string "apples"
    const char *strings[] = {"apples", "oranges", "bananas", "pears", NULL};

    // Define a pointer to the array of pointers, i.e.
    // a pointer to the first character of the first string, "a"
    const char **ptr = strings;

    // Let's print relevant variables for better understanding
    printf("\n");
    printf("**prt    = %c\n", **ptr);    // "**ptr    = a"
    printf("strings  = %p\n", strings);  // "strings  = 0x7ffcff4e2bc0" (e.g.)
    printf("*strings = %s\n", *strings); // "*strings = apples"
    printf("*ptr     = %s\n\n", *ptr);   // "*ptr     = apples"

    while (*ptr != NULL) {
        // Print the string that starts at memory location `ptr`
        printf("%s\n", *ptr);

        // Increment to points at the next memory location
        ptr++;
    }
    printf("\n");
    }
    ```

    Compiling and running prints

        **prt     =  a
        strings   =  0x7ffcff4e2bc0
        *strings  =  apples
        *ptr      =  apples

        apples
        oranges
        bananas
        pears

    Note that if we leave out `NULL` as the last element in the string array, this code would not work and likely segfault.
