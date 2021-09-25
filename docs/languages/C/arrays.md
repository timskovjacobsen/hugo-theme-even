# Arrays

An array forms a sequence of elements, which are all the same type.

```c
// Create an array with three uninitialized elements
int arr[3];
```

The variable `arr` is essentially a pointer to the memory address of the first element in the array.

```c
// Create an array with initialized elements
int arr[3] = {123, 221, 173};
```

If the amount of elements in the declaration is left blank, the compiler will infer the amount.

```c
// Create an array with initialized elements, compiler infers length
int arr[] = {123, 221, 173};
```

Because an array itself is a pointer to the first element, the address-of operator `&` is superfluous. If we have an array `arr`, then `&arr` will be the same thing.

```c
#include <stdio.h>

int main(void) {
int arr[] = {1, 2, 3, 4, 5};

// These two lines will print the same thing
// The array itself is a pointer to the memory address of the
// first element, so the &-operator is superfluous
printf("arr = %p\n", arr);
printf("&arr = %p\n", &arr);
}
```

!!! tip
    When creating an array, the number of elements given can be equal to or fewer than the number of initialized elements. Any missing elements are auto-initialized to `0`.

    Thus, code like this will work

    ```c
    // Initialize all elements to be 0
    int arr[3] = {0};   
    ```

    However, `arr[3];` will NOT initialize elements to 0, the content is instead undefined. We are reserving memory for the array, but we are not setting any values in those memory addresses. We should instead use `arr[3] = {0};` instead.

    Providing more elements than the array is declared to handle will naturally result in an error.

    There is no way in the C standard library to initialize an array with all elements to to non-zero. We must populate the values in a loop in that case.

!!! note ""
    Once initialized, arrays are immutable.

## Indexing arrays

Arrays can be indexed in a "manual" fashion by pointer arithmetics or by standard indexing.

```c
#include "stdio.h"

int main(void) {
  int arr[3] = {123, 221, 173};

  printf("arr[0] = %d\n", arr[0]);
}
```

Under the hood, indexing with `#!c arr[i]` will get translated to pointer arithmetic to access the correct element. In this case, the translated code will be `#!c *(arr + 1)`.

!!! warning "Beware of arrays out of bounds"
    Accessing an array out of bounds does **not** throw an error in C in contrast to most higher-level programming languages. The behavior of such an operation is ***undefined***. I.e., the C language does not have any support for it. Therefore, the programmer must keep track of the length and indexing of arrays.

When calling a function, we pass the pointer to the array together with its size. Since we can't get the size directly, this property must follow the pointer around everywhere it goes.
Alternatively, we could make a struct with the array and its size and pass the struct to functions instead.

## Working with arrays

### Summing all array elements

```c linenums="1"
#include <stdio.h>
#include <stdlib.h>

/*
 * Function: sum_array
 * --------------------
 * Compute the sum of the passed-in array (pointer).
 *
 *  * array: A pointer to the first element in the array to be summed
 *  n: The number of elements in the array (size).
 *
 *  Returns: The sum of all array elements.
 */
int sum_array_ptr_arithmetic(int * array, int n) {
  int * ptr = array;
  int sum = 0;
  for (int i = 0; i < n; i++) {
    sum += *ptr;
    ptr++;
  }
  return sum;
}

int sum_array_ptr_indexing(int * array, int n) {
  int sum = 0;
  for (int i = 0; i < n; i++) {
    sum += array[i];
  }
  return sum;
}

int sum_array(int array[], int n) {
  int sum = 0;
  for (int i = 0; i < n; i++) {
    sum += array[i];
  }
  return sum;
}

int main(void) {
  int arr[] = {17, 75, 52, 25};
  int sum1 = sum_array_ptr_arithmetic(arr, 4);
  int sum2 = sum_array_ptr_indexing(arr, 4);
  int sum3 = sum_array(arr, 4);
  printf("sum1 = %d\n", sum1);  // sum1 = 169 
  printf("sum2 = %d\n", sum2);  // sum2 = 169
  printf("sum3 = %d\n", sum3);  // sum3 = 169
  return EXIT_SUCCESS;
}
```

* In line 15 (`#!c int * prt = array`), we start a pointer variable which initially is just the "array itself". An array in C is just a pointer to the first element in the array.

* We then loop over all the elements in the array. We know the length as we pass it as a parameter.

* In line 18 (`#!c sum += *prt`), we add the current element's value to the sum. Here we *dereference* the pointer

* In line 19 (`#!c ptr++`), we perform pointer arithmetic to advance `ptr` to the following memory location. The compiler will figure out that it needs to add  $4$ since each `int` element occupies $4$ bytes ($32$ bits) of memory.

    !!! note
        In the last iteration of the loop, the `ptr++` line ends up incrementing the `ptr` variable to be out-of-bounds for the array. It will refer to a memory address that is unknown to the program/programmer. Although this only becomes a problem if we dereference the pointer to get the value and work with it. Since the loop terminates right after this and `sum_array` returns and has its frame destroyed, it's not an issue.

* The function `sum_array_ptr_indexing` is arguably more pretty to look at since it utilizes the compiler's ability to handle indices.

* The function `sum_array` is equivalent to `sum_array_ptr_indexing`. It's generally preferable to pass the pointer in this way because it's more clear that we are dealing with an array.

## Arrays of arrays

Arrays can be nested like this

```c
int main(void) {
    int arr[][3] = {{1, 2, 3},
                    {11, 12, 13},
                    {21, 22, 23}};
                    {31, 32, 33}};
}
```

Note that the compiler can infer the first dimension of the array, i.e. how many inner arrays we have. But we have to state the number of elements within each array as.
