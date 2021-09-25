# Pointers

In the C programming language, a *pointer* is a variable whose value is the memory address of another variable. As a convention, memory addresses are written in [*hex*](/numbersystems/hex), which is the base $16$ number system.

!!! tip
    In the C programming language, a *pointer* is a variable whose value is the memory address of another variable.

## Memory addresses

We can reference a memory address of a variable `a` in C by

```c
a = 12;
printf("memory address of a = %d\n", &a);
```

Prints

    memory address of a = 0x7ffcebe82e34

The memory address might not be the same on other machines or in subsequent runs.

## Pointer basics

A pointer is creates with the `*` character.

```c

```

??? example
    ```c
    #include <stdio.h>
    #include <stdlib.h>

    int f(int ** r, int ** s) {
      int temp = ** r;
      int temp2 = **s;
      int * z = *r;
      *r = *s;
      *s = z;
      printf("**r = %d\n",**r);
      printf("**s = %d\n",**s);
      *z += 3;
      **s -= 8;
      **r -= 19;
      return temp + temp2;
    }

    int main(void) {
      int a = 80;
      int b = 12;
      int * p = &a;
      int * q = &b;
      int x = f(&p, &q);
      printf("x = %d\n", x);
      printf("*p = %d\n", *p);
      printf("*q = %d\n", *q);
      printf("a = %d\n", a);
      printf("b = %d\n", b);
      return EXIT_SUCCESS;
    }
    ```

    The output will be

    ```c
    **r = 12
    **s = 80
    x = 92
    *p = -7
    *q = 75
    a = 75
    b = -7
    ```

    **TODO: Make sketch of scope boxes. Kind of hard since it changes in each line. Maybe show the final scope box.**

## Pointers to structs

Referring to field `x` of a struct `a` can be done as

```c
(*a).x
```

Since pointers can point to other pointers in a nested fashion, this syntax is often discouraged as it can become messy to look at.

Therefore, at preferred way to access field `x` of struct `a` is

```c
a->x
```

## Constants with pointers

When working with pointers and constants, pay attention to the syntax. We can specify the pointer itself or value it points to as a constant.

Define a constant

```c
const int x = 5;
```

This will signal to the compiler that `x` is not allowed to change. An attempt at changing `x` will throw an error.

Declare `ptr` as a **pointer to a constant `int`**. I.e. so the value the pointer points to is the constant part.

```c
const int * ptr = &x;   // Alternative: const int * p = &x;
```

Declare `ptr` as a **constant pointer to a changeable `int`**. Thus, we can change the value that is pointed at `*p`, but not pointer `ptr`.

```c
int * const ptr = &x;
```

We **can** then change `x` to $4$ with `*p = 4`, but we **can't** change the pointer to point to another memory location, like `p = &y`.

A combination of the above can be used to "lock" both the pointer and the value as constants with

```c
const int * const ptr = &x;
```

!!! note
    If we declare a pointer to a constant with `#!c const int * ptr = &x;`, we cannot change the value of `x` by means of `*p`. However, the value of `x` can still be changed in other ways not related to the pointer.

    To prevent that, `x` needs to have its own declaration line, saying that it's a constant, like `#!c const int x = 3`.

### Overview table

The table below gives an overview of the syntax and what can be changed for the given expressions.

| Expression | `**p` changeable? | `*p` changeable? | `ptr` changeable? |
| --- | :---: | :---: | :---: |
| `#!c int ** p` | Yes | Yes | Yes |
| `#!c const   int   ** p` | No | Yes | Yes |
| `#!c int * const * p` | Yes | No | Yes |
| `#!c int   ** const p` | Yes | Yes | No |
| `#!c const int * const * p` | No | No | Yes |
| `#!c const int ** const p` | No | Yes | No |
| `#!c int * const * const p` | Yes | No | No |
| `#!c const int * const * const p` | No | No | No |

## Pointer arithmetics

Recall that a pointer is a variable with value equal to another variable's memory address. Everything in a computer is represented as a number. Memory addresses are normally referred to in *hex*, i.e. base $16$.
Therefore, it's possible to perform arithmetic with pointers, just like with other types like integers and floats.

We do arithmetics with pointers based on the underlying type of the pointer. Incrementing will increase the pointer's value by an amount equal to the number of bytes that the particular type occupies in memory. Note that we are working with bytes, not bits here.

Thus, if we have an `int` pointer, each increment will add $4$ bytes to the memory address since an `int` occupies $4$ bytes of memory.

More generally, when adding the integer $k$ to a pointer of type `T`, the compiler adds `k * num_bytes(T)` to the memory address.

!!! note
    The compiler takes care of most of this for us! **IS THIS CORRECT?**

## Pointer pitfalls

### Dangling pointers

A *dangling pointer* is a pointer to a memory location which does not exist anymore.

Say that a pointer

```c linenums="1" hl_lines="11-12"
int * create_array(int n){
    // Initialize the array with n elements
    int arr[n];

    // Fill in the array with zeros
    for (int i; i < n; i++) {
        arr[i] = 0;
    }

    // Return the array (pointer to first element's memory location)
    // BUG: Don't do this, the local variable `arr` is destroyed 
    //      after this function terminates.
    return arr;
}
```

A function always has a stack frame assigned to it. Any local variables defined in the function lives in that stack frame.

After the function terminates, the frame is destroyed and the memory locations where the local variables are stored get freed up for later use.

!!! danger "Don't return pointers to local variables"
    If we return a pointer to a local variable, it will become a *dangling pointer* since the memory address it points to is not associated with that local variable anymore.

The way to correctly return local variables is to allocate memory for it with `malloc`. It will make the memory locations persist across function calls.
