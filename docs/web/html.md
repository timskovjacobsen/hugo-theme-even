# HTML

## Element IDs

To give an html element a unique ID:

```html
<h1 id="foo">Welcome</h1>
```

This can be useful if that specific element should have unique css properties attached
to it.

To refer to the ID "foo" in css:

```css
#foo {
    color: blue;
    ...
}
```

## Classes

```html
<h1 class="baz">Welcome</h1>
<h1 class="baz">Introduction</h1>
```

In css, classes are referred to by prefixing the class name with `.`:

```css
.baz {
    color: blue;
    ...
}
```

This will style all HTML elements in class `"baz"`.
