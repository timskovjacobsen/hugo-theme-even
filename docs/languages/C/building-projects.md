# Building projects in C

## Header files

Header files contain C-code *declarations* rather than *definitions*. This will typically be declaration of

1. types
2. structs
3. function names with their output and argument types

In other words, header files never contains code that allocates memory.

The name of a header file always ends in `.h`.

Header files are used for including into other files ...

## Projects with multiple files

!!! example
    Say we have a file `power.c` and we want to test the code with tests located in `test-power.c`.

    === "power.c"
        ```c
        // power.c
        #include <stdio.h>

        unsigned power(unsigned x, unsigned y) {
          if (y == 0) {
            return 1;
          }
          return x * power(x, y - 1);
        }
        ```

    === "test-power.c"
        ```c
        // test-power.c
        #include <stdio.h>
        #include <stdlib.h>

        #include "power.c"  // <== include code from power.c

        int run_check(unsigned x, unsigned y, unsigned expected) {
          int actual = power(x, y);
          if (actual != expected) {
            printf("x = %d, y = %d should return %d, but returned %d\n", x, y, expected,
                 actual);
            // We use `exit(EXIT_FAILURE)` rather than `return EXIT_FAILURE` to stop the
            // program if we accounter a failed test. Otherwise the program runs to
            // completion
            exit(EXIT_FAILURE);
            }
          return EXIT_SUCCESS;
        }

        int main(void) {
          run_check(0, 0, 1);
          run_check(5, 0, 1);
          run_check(0, 5, 0);
          run_check(5, 2, 25);
          run_check(2 * 3, 2, 36);
          run_check(5, 1, 5);
          run_check(2, 10, 1024);
          run_check(10, 2, 100);
          run_check(10, 5, 100000);
          run_check(1, 0, 1);
          run_check(0, 1, 0);
          printf("\n=================\nALL TESTS PASSED!\n=================\n");
          return EXIT_SUCCESS;
        }
        ```

ELABORATE ON THE BELOW! IF INCLUDING C-FILES WORK DIRECTLY WHY USE HEADER FILES AND DUPLICATE CODE IN MULTIPLE FILES?

We want to share a function across multiple files to avoid code duplication. The way to do that in C is via header files.

1. Create a header file `foo.h`
2. Write a *declaration* for a function `bar` in `foo.h`

3. Write a function *definition* inside file `functions.c`

4. We can then include the function from a file `baz.c`

    ```c
    #include foo.h
    #include baz.c
    ```

**TODO: Is this correct?**
**TODO: Write this in a cleaner way once there is a nice example.**

## Using make

Make files are super valuable ...
