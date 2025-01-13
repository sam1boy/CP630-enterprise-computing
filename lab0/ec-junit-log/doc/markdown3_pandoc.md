## [Pandoc Markdown Extensions](file:///C:/enterprise/pandoc-2.2.3.2/Pandoc%20User's%20Guide.html#templates) 

Using Pandoc markdown extensions requires the [Pandoc](https://pandoc.org/index.html) converter.

### backtick code block
	
```java
if (a > 3) {
  moveShip(5 * gravity, DOWN);
}
```

Like regular code blocks, fenced code blocks must be separated from surrounding text by blank lines.

If the code itself contains a row of tildes or backticks, just use a longer row of tildes or backticks at the start and end:

```
code including tildes
```

### backtick_code_blocks

Same as fenced_code_blocks, but uses backticks (`) instead of tildes (~).
Extension: fenced_code_attributes

Optionally, you may attach attributes to fenced or backtick code block using this syntax:

~~~~ {#mycode .haskell .numberLines startFrom="100"}
qsort []     = []
qsort (x:xs) = qsort (filter (< x) xs) ++ [x] ++
               qsort (filter (>= x) xs)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	
### line_blocks

A line block is a sequence of lines beginning with a vertical bar (|) followed by a space. The division into lines will be preserved in the output, as will any leading spaces; otherwise, the lines will be formatted as Markdown. This is useful for verse and addresses:

| The limerick packs laughs anatomical
| In space that is quite economical.
|    But the good ones I've seen
|    So seldom are clean
| And the clean ones so seldom are comical


