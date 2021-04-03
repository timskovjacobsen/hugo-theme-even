# CSS

## Specificity

CSS Specificity is the hierarchy that CSS properties are applied in in case of conflicting property definitions.

Conflicting definitions happens if for example if an HTML element is both part of a class and has a unique ID. When defining styles for both the class and the ID, which one takes precedence?

The specificity hierarchy in CSS is:

1. inline
2. ID
3. class
4. type

## Selectors

| CSS Selector  | Explanation               |
|---------------|---------------------------|
| `a, b`        | Multiple element selector |
| `a b`         | Descendant selector       |
| `a > b`       | Child selector            |
| `a + b`       | Adjacent sibling selector |
| `[a=b]`       | Attribute selector        |
| `a:b`         | Pseudoclass selector      |
| `a::b`        | Pseudoelemtnt selector    |

### Selector examples

#### Descendant selector

Style only sublist items in the following html code:

```html linenums="1" hl_lines="5-8"
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Hello!</title>
    <style>
        ul li {
            color: blue;
        }
    </style>
</head>

<body>
    <ol>
        <li> List item one</li>
        <li> List item two</li>
        <ul>
            <li>Sublist item one</li>
            <li>Sublist item two</li>
        </ul>
        <li>List item three</li>
    </ol>
</body>
</html>
```

The CSS selector `ul li` selects all the `li` descendants of `ul`.