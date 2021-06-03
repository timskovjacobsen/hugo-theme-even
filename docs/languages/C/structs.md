# Structs

## Initializing structs

```c
// Initialize a struct of type Rectangle
Rectangle r = { .b = 8, .h = 5}
```

Any potential fields that are not given during initialization will be initialized to have a value of `0`.

```c
// Initialize an array of rectangles
Rectangle r[] = { { .b = 8, .h = 5 },
                  { .b = 2, .h = 3 },
                  { .b = 6, .h = 7 } };
```
