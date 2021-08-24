# Java

## Pass by Value

In Java, arguments are passed by value. This is super confusing because an argument can actually tbe mutated still.

!!! tip
Java is passing by value but, but the value passed in is a reference to an object.

In C, arguments are passed by value, but you can pass a pointer as a memory address to achieve a pass by reference behavior. Since Java is essentially passing pointers, I would think that Java is pass by value, but that is not the case! Java is passing values, but those values are pointers!

### References

* [Is Java “pass-by-reference” or “pass-by-value”?](https://stackoverflow.com/questions/40480/is-java-pass-by-reference-or-pass-by-value)
