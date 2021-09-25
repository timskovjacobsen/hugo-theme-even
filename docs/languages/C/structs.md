# Structs

We can define structured data types in C via the `struct` concept. We use it to group pieces of data together into a single entity.

Structs can be thought of as a simplified version of classes from another language. Although they don't support easy implementation of methods.

Structs are templates for creating instances of custom data types describing a thing. The struct declaration itself does not allocate any memory, but creating instances of the struct does.

## Initializing structs

```c linenums="1"
--8<--
docs/languages/C/code/structs.c
--8<--
```

Any potential fields that are not given during initialization will be initialized to have a value of `0`.

## Accessing struct fields (member referencing)

A struct field can be accessed by dot notation if it's a direct value. If we have an instance of a struct `Rectangle` called `r1`, we can reference its `height` member by `r1.height`.

If the field is a pointer, we need to use arrow notation instead. TODO: `foo->bar`

## Copying structs
