# Markdown Guide

Author: HBF    
Date: August 17, 2018

This markdown document introduces the basics of markdown for simple formatted documentations.

## What is Markdown?

Markdown is a lightweight markup language with plain text formatting syntax.
It was originally designed and used to convert to HTML and many other formats by tools. 

Markdown is commonly used to write formatted readme files, messages, and rich text document using a plain text editor. 

Markdown extensions are used by different tools to enrich features of derived formats. There are many markdown extension variations. We will take a look at GitHub Flavored Markdown (GFM) and Pandoc markdown extensions.


## Basic Syntax


### Headers

Header
=============

or

# Header
 
## Subheader


### Paragraph

Paragraphs are separated by a blank line.

Two spaces at the end of a line  
produces a line break.


### Emphasis

Text attributes _italic_, **bold**, `monospace`.

Emphasis, aka italics, with *asterisks* or _underscores_.

Strong emphasis, aka bold, with **asterisks** or __underscores__.

Combined emphasis with **asterisks and _underscores_**.

Strikethrough uses two tildes. ~~Scratch this.~~


### Horizontal rule

----


### List

Bullet list:

  * apples
  * oranges
  * pears

Numbered list:

  1. wash
  2. rinse
  3. repeat

### Hyperlink

A [Markdonw on Wikipedia](https://en.wikipedia.org/wiki/Markdown).

B [markdonw basic syntax](https://www.markdownguide.org/basic-syntax/)


### Image

![Image](Markdown-mark.svg.png)

Inline-style: ![alt text](https://upload.wikimedia.org/wikipedia/commons/thumb/4/48/Markdown-mark.svg/208px-Markdown-mark.svg.png "Logo Title Text 1")


###  Block Quoting

> block quoting.


### HTML

HTML elements can be added to markdown directly and be ignored by HTML converting tools. 

For example, include an html table like:

<table>
<tr>
<td><em>one</em></td>
<td><a href="http://google.com">a link</a></td>
</tr>
<tr>
<td><em>two</em></td>
<td><a href="https://www.markdownguide.org/basic-syntax/">another link</a></td>
</tr>
</table>

