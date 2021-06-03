# Arrays

An array forms a sequence of elements, which are all of the same type.

```c
// Create an array with three uninitialized elements
int arr[3];
```

**The variable `arr` is essentially a pointer to the memory address of the first element in the array.**

```c
// Create an array with initialized elements
int arr[3] = {123, 221, 173};
```

If the amount of elements in the declaration is left blank, the compiler will infer the amount

```c
// Create an array with initialized elements, compiler infers length
int arr[] = {123, 221, 173};
```

!!! tip
    When creating an array, the amount of elements given can be equal to or less than the amount of elements that are initialized. Any missing elements are auto-initialized to `0`.

    Thus, code like this will work

    ```c
    // Initialize all elements to be 0
    int arr[3] = {0};
    ```

    Providing more elements than the array is declared to handle will naturally result in an error.

!!! note
    Once initialized, arrays are immutable.

## Indexing arrays

Arrays can be indexed in a "manual" fashion by pointer arithmetic, or by standard indexing

```c
#include "stdio.h"

int main(void) {
  int arr[3] = {123, 221, 173};

  printf("arr[0] = %d\n", arr[0]);
}
```

Under the hood, indexing with `#!c arr[i]` will get translated to pointer arithmetic to access the correct element. In this case, the translated code will be `#!c *(arr + 1)`

!!! note
    Accessing an array out of bounds will not give an error like in most higher level programming languages. Instead, the returned value will be whatever is located at that memory address.
    Therefore, it's very important to keep track of the length and indexing of arrays.

## Working with arrays

### Summing all array elements

```c linenums="1"
/*
 * Function: sum_array
 * --------------------
 * Computes the sum of the array whose pointer is passed as argument.
 *
 *  * array: A pointer to the first element in the array to be summed
 *  n: The number of elements in the array (length).
 *
 *  Returns: The sum of all array elements.
 */
int sum_array_ptr_arithmetic(int * array, int n) {
    int * ptr = array
    int sum = 0
    for (int i = 0; i < n;i++) {
        sum += *ptr;
        ptr++;
    }
    return sum;
}

int sum_array_ptr_indexing(int * array, int n) {
    int sum = 0
    for (int i = 0; i < n;i++) {
        sum += *array[i];
    }
    return sum;
}

int main(void) {
    int arr[] = {17, 75, 52, 25};
    int sum = sum_array_ptr_indexing(arr, 4);
    printf("sum = %d\n", sum);
    return EXIT_SUCCESS;
}
```

* In line 12 (`#!c int * prt = array`), we start a pointer variable which initially is just the "array itself". An array in C is just a pointer to the first element in the array.

* We then loop over all the elements in the array. We know the length as it was passed in as a parameter.

* In line 15 (`#!c sum += prt`), we add the current element's value to the sum. This is a *dereference* of the pointer

* In line  (`#!c ptr++`), we perform pointer arithmetic to advance `ptr` to the next memory location. The compiler will figure out that it needs to add  $4$, since each `int` element occupies $4$ bytes ($32$ bits) of memory.

    !!! note
        In the last iteration of the loop, the `ptr++` line ends up incrementing the `ptr` variable to be out-of-bounds for the array. It will refer to an memory address that is unknown to the program/programmer. This is only becomes a problem however, if we actually dereference the pointer to get the value and work with it. Since the loop terminates right after this and `sum_array` returns and has its frame destroyed, it's not an issue.

* The function `sum_array_ptr_indexing` is arguably more pretty to look at, since it utilizes the compiler's ability to handle indices.
