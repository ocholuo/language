
# HTML

[toc]

---

## introduction

What is HTML?
- HTML stands for Hyper Text Markup Language
- HTML describes the structure of a Web page
- HTML consists of a series of elements
- HTML elements tell the browser how to display the content
- HTML elements are represented by tags
- HTML tags label pieces of content such as "heading", "paragraph", "table", and so on
- Browsers do not display the HTML tags, but use them to render the content of the page


![Screen Shot 2020-05-12 at 20.24.54](https://i.imgur.com/kPTQ6qH.png)

### HTML Tags

`<tagname>content goes here...</tagname>`

in pairs, start tag + end tag.

```html
<html>

<head>
<title>Page title</title>
</head>

<body>
<h1>This is a heading</h1>
<p>This is a paragraph.</p>
<p>This is another paragraph.</p>
</body>

</html>
```

### The <!DOCTYPE> Declaration

- The <!DOCTYPE> declaration represents the document type, and helps browsers to display web pages correctly.
- only appear once, at the top of the page (before any HTML tags).
- The <!DOCTYPE> declaration is not case sensitive.
- The <!DOCTYPE> declaration for HTML5 is: `<!DOCTYPE html>`


### Empty HTML Elements
HTML elements with no content are called empty elements.

`<br>` is an empty element without a closing tag (the `<br>` tag defines a line break):

`<p>This is a <br> paragraph with a line break.</p>`


### inlines vs block level Elements

![Screen Shot 2020-05-12 at 19.50.31](https://i.imgur.com/7rOUAfh.png)

---

## HTML Attributes

W3C recommends lowercase in HTML, and demands lowercase for stricter document types like XHTML.

**The `href` Attribute**： HTML links

`<a href="https://www.w3schools.com">This is a link</a>`


**The `src` Attribute**: The filename of the image source is specified in the src attribute:

`<img src="img_girl.jpg">`


**The `width` and `height` Attributes**

`<img src="img_girl.jpg" width="500" height="600">`


**The `alt` Attribute**:
- an alternative text to be used, useful if the image cannot be displayed or does not exist.
- The value of the alt attribute can be read by screen readers. This way, someone "listening" to the webpage, e.g. a vision impaired person, can "hear" the element.

`<img src="img_girl.jpg" alt="Girl with a jacket">`


**The `style` Attribute**
- specify the styling of an element, like color, font, size etc.

`<p style="color:red">This is a red paragraph.</p>`


**The `lang` Attribute**
- The language of the document can be declared in the `<html>` tag
- important for *accessibility applications (screen readers) and search engines*:

```html
<!DOCTYPE html>
<html lang="en-US">
<body>

</body>
</html>
```


**The `title` Attribute**
- title attribute is added to the `<p>` element.
- will be displayed as a tooltip when you mouse over the paragraph:

```html
<h2 title="I'm a header">The title Attribute</h2>
<p title="I'm a tooltip">Mouse over this paragraph, to display the title attribute as a tooltip.</p>
```

---


## HTML Headings
HTML headings are defined with the `<h1>` to `<h6>` tags.

### Bigger Headings
Each HTML heading has a default size.
specify the size for any heading with the style attribute, using the CSS font-size property:

`<h1 style="font-size:60px;">Heading 1</h1>`

### HTML Horizontal Rules `<hr>`

---

## HTML Paragraphs

You cannot be sure how HTML will be displayed.

Large or small screens, and resized windows will create different results.

With HTML, you cannot change the output by adding extra spaces or extra lines in your HTML code.

The browser will remove any extra spaces and extra lines when the page is displayed:

all the same:

```html

<p>This is a paragraph.</p>

<p>This is a                     paragraph.</p>

<p>
This
is
a
paragraph.
</p>
```

### Line Breaks `<br>`


### The Poem Problem `<pre> </pre>`
This poem will display on a single line:

```html
<pre>
  My Bonnie lies over the ocean.

  My Bonnie lies over the sea.
</pre>
```

---

## HTML Styles

`<tagname style="property:value;">`


### The HTML `Style` Attribute

### CSS property

#### `background-color`
```html
<body style="background-color:powderblue;">

<h1>This is a heading</h1>
<p>This is a paragraph.</p>

</body>
```

#### `color`
the text color for an HTML element:
```html
<h1 style="color:blue;">This is a heading</h1>
<p style="color:red;">This is a paragraph.</p>
```

#### `font-family` 字体
defines the font to be used for an HTML element:
```html
<h1 style="font-family:verdana;">This is a heading</h1>
<p style="font-family:courier;">This is a paragraph.</p>
```

#### `font-size` 字号
defines the text size for an HTML element:
```html
<h1 style="font-size:300%;">This is a heading</h1>
<p style="font-size:160%;">This is a paragraph.</p>
```

#### `text-align` 居中 text alignment
defines the horizontal text alignment for an HTML element:
```html
<h1 style="text-align:center;">Centered Heading</h1>
<p style="text-align:center;">Centered paragraph.</p>
```

---

## HTML Text Formatting

```html

<b> - Bold text
<strong> - Important text

<i> Italic text </i>
<em> Emphasized text </em>


<small> Smaller text </small>

<mark> Marked text 荧光笔标注 </mark>
<del> - Deleted text 划去
<ins> - Inserted text 下横线
<sub> - Subscript text 脚注的
<sup> - Superscript text 上标
```

---

## HTML Quotation and Citation Elements

### HTML `<q>` for Short Quotations
```html
<p>WWF's goal is to: <q>Build a future where people live in harmony with nature.</q></p>
```

### HTML `<blockquote>` for Quotations
defines a section that is quoted from another source.
```html
<p>Here is a quote from WWF's website:</p>
<blockquote cite="http://www.worldwildlife.org/who/index.html">
For 50 years, WWF has been protecting the future of nature.
The world's leading conservation organization,
WWF works in 100 countries and is supported by
1.2 million members in the United States and
close to 5 million globally.
</blockquote>

// 并不会断行，是一整句话
```


### HTML `<abbr>` for Abbreviations
defines an abbreviation or an acronym.

Marking abbreviations can *give useful information to browsers, translation systems and search-engines*.

```html
<p>The <abbr title="World Health Organization">WHO</abbr> was founded in 1948.</p>
```


### HTML `<address>` for Contact Information
defines contact information (author/owner) of a document or an article.

The `<address>` element is usually displayed in italic. Most browsers will add a line break before and after the element.

```html
<address>
Written by John Doe.<br>
Visit us at:<br>
Example.com<br>
Box 564, Disneyland<br>
USA
</address>
```

### HTML `<cite>` for Work Title
defines the title of a work.

Browsers usually display `<cite>` elements in italic.
```html
<p><cite>The Scream</cite> by Edvard Munch. Painted in 1893.</p>
```

### HTML `<bdo>` for Bi-Directional Override
defines bi-directional override. override the current text direction:
```html
<bdo>This text will be written from right to left</bdo>
正方形
<br>
<bdo dir="rtl">This text will be written from right to left</bdo>
反方向
```

---

## HTML Comments

###HTML Comment Tags

exclamation point (!) in the opening tag, but not in the closing tag.

not displayed by the browser, but can help document HTML source code.

```html
<!-- This is a comment -->
<p>This is a paragraph.</p>
<!-- Remember to add more information here -->
```

---

## HTML Colors

- color names
- html5 color names
- hexadecimal
- RGB

### `background-color` and `color`

```HTML

<body style="background-color:powderblue;">

<h1 style="background-color:DodgerBlue;">Hello World</h1>
<p style="background-color:Tomato;">Lorem ipsum...</p>

<h1 style="color:Tomato;">Hello World</h1>
<p style="color:DodgerBlue;">Lorem ipsum...</p>
<p style="color:MediumSeaGreen;">Ut wisi enim...</p>

</body>

```


### `Border` Border Color

```html
<h1 style="border:2px solid Tomato;">Hello World</h1>
<h1 style="border:2px solid DodgerBlue;">Hello World</h1>
<h1 style="border:2px solid Violet;">Hello World</h1>
```

### color values

`rgba(255, 99, 71, 0.5)`

`#ff6347`

`hsl(hue, saturation, lightness)`

```html
<h1 style="background-color:rgb(255, 99, 71);">...</h1>
<h1 style="background-color:#ff6347;">...</h1>
<h1 style="background-color:hsl(9, 100%, 64%);">...</h1>

// 50% transparent
<h1 style="background-color:rgba(255, 99, 71, 0.5);">...</h1>
<h1 style="background-color:hsla(9, 100%, 64%, 0.5);">...</h1>
```


---

## HTML Styles - `CSS` Cascading Style Sheets.

CSS can be added to HTML elements in 3 ways:
- Inline - by using the style attribute in HTML elements
- Internal - by using a `<style>` element in the <head> section
- External - by using an external CSS file

### Inline CSS
- used to apply a unique style to a *single HTML element*.
- uses the `style` attribute of an HTML element.

`<h1 style="color:blue;">This is a Blue Heading</h1>`

- **avoid this**
  - keep the presentation the functionality and the styling completely separate or as much as possible.
  - inline css mix the presentation with Styling, not professional, not scalable, not practical.

### Internal CSS `<head> <style> body {} h1 {} p {} </style> </head>`
- define a style for a *single HTML page*.
- defined in the `<head>` section of an HTML page, within a `<style>` element:

- **avoid**
  - only for that single html file, also fattens up the HTML file.

```html
<head>
<style>
body {background-color: powderblue;}
h1 {
  color: blue;
  font-family: verdana;
  font-size: 300%;
}
</style>
</head>
```

### External CSS `<head> <link rel="stylesheet" href="name.css"> </head>`
- define the style for *many HTML pages*.
- With an external style sheet, change entire web site, by changing one file!
- add a `<link>` to it in the `<head>` section of the HTML page:

![Screen Shot 2020-05-12 at 22.35.19](https://i.imgur.com/GT3XrCC.png)

```html
<head>
  <link rel="stylesheet" href="styles.css">
</head>
```

**crate a separate css file**: the `"styles.css"`:

```
body {
  background-color: powderblue;
}
h1 {
  color: blue;
}
/p {
  color: red;
}
```

#### External References
External style sheets can be referenced with a full URL or with a path relative to the current web page.

full URL to link to a style sheet:
```html
<link rel="stylesheet" href="https://www.w3schools.com/html/styles.css">
```

style sheet located in the html folder on the current web site:
```html
<link rel="stylesheet" href="/html/styles.css">
```

style sheet located in the same folder as the current page:
```html
<link rel="stylesheet" href="styles.css">
```



### The `id` Attribute 根据id改样式

To define a specific style for one special element, add an id attribute to the element:

```html
<p id="p01">I am different</p>
```

then define a style for the element with the specific id:

```html
#p01 {
  color: blue;
}
```

**id** is *unique*, wont's use it for other element.
**class** is may use for other element.


### The `class` Attribute 根据class改样式
To define a style for special types of elements, add a class attribute to the element:
```html
<p class="error">I am different</p>
```

then define a style for the elements with the specific class:
```html
p.error {
  color: red;
}
```


### CSS `Border` `Padding`

The CSS `border` property defines a border around an HTML element:

The CSS `padding` property defines a padding (space) between the text and the border:

The CSS `margin` property defines a margin (space) outside the border:

```
p {
  border: 1px solid powderblue;
  padding: 30px;  内圈框
  margin: 50px;   外圈框
}
```


---

## HTML Links

### HTML Links - Syntax
an absolute URL (a full web address).
`<a href="https://www.w3schools.com/html/">Visit our HTML tutorial</a>`


### link pages Paths
External pages can be referenced with a full URL or with a path relative to the current web page.

full URL to link to a web page:
`<a href="https://www.w3schools.com/html/default.asp">HTML tutorial</a>`

a page located in the html folder on the current web site:
`<a href="/html/default.asp">HTML tutorial</a>`

a page located in the same folder as the current page:
`<a href="default.asp">HTML tutorial</a>`


### The `target` Attribute
The target attribute specifies where to open the linked document.

The target attribute can have one of the following values:

- `_blank` - Opens the linked document in a new window or tab
- `_self` - Opens the linked document in the same window/tab as it was clicked (this is default)
- `_parent` - Opens the linked document in the parent frame
- `_top` - Opens the linked document in the full body of the window 替代当前窗口
- `framename` - Opens the linked document in a named frame


### Image as a Link
It is common to use images as links:
```html
<a href="default.asp">
  <img src="smiley.gif" alt="HTML tutorial" style="width:42px;height:42px;border:0;">
</a>
```

### Button as a Link
To use an HTML button as a link, you have to add some JavaScript code.

JavaScript allows you to specify what happens at certain events, such as a click of a button:

```html
<button>HTML Tutorial</button>

<button onclick="document.location = 'default.asp'">HTML Tutorial</button>
```


### `title` attribute
The `title` attribute specifies extra information about an element. The information is most often shown as a tooltip text when the mouse moves over the element.

<a href="https://www.w3schools.com/html/" title="Go to W3Schools HTML section">Visit our HTML Tutorial</a>



### HTML Links - Different Colors

By default, a link will appear like this (in all browsers):

- An unvisited link is underlined and blue
- A visited link is underlined and purple
- An active link is underlined and red

```html
<style>
a:link {
  color: green;
  background-color: transparent;
  text-decoration: none;
}

a:visited {
  color: pink;
  background-color: transparent;
  text-decoration: none;
}

a:hover {
  color: red;
  background-color: transparent;
  text-decoration: underline;
}   移动到他的时候

a:active {
  color: yellow;
  background-color: transparent;
  text-decoration: underline;
}
</style>
```


#### Link Buttons
A link can also be styled as a button, by using CSS:

```html
<style>
a:link, a:visited {
  background-color: #f44336;        设置内圈颜色
  color: white;
  padding: 15px 25px;               把内圈做大
  text-align: center;
  text-decoration: none;
  display: inline-block;
}

a:hover, a:active {        移动到他的时候
  background-color: red;
}   
</style>
```

---

## HTML Links - Create Bookmarks

### Create a Bookmark in HTML
Bookmarks can be useful if a web page is very long.

To create a bookmark - first create the bookmark, then add a link to it.

When the link is clicked, the page will scroll down or up to the location with the bookmark.

create a bookmark with the id attribute:
`<h2 id="C4">Chapter 4</h2>`

add a link to the bookmark ("Jump to Chapter 4"), from within the same page:
`<a href="#C4">Jump to Chapter 4</a>`

```html
<body>
<p><a href="#C4">Jump to Chapter 4</a></p>
<p><a href="#C10">Jump to Chapter 10</a></p>

<h2>Chapter 1</h2>
<p>This chapter explains ba bla bla</p>

<h2>Chapter 2</h2>
<p>This chapter explains ba bla bla</p>

<h2>Chapter 3</h2>
<p>This chapter explains ba bla bla</p>

<h2 id="C4">Chapter 4</h2>
<p>This chapter explains ba bla bla</p>
</body>
```

---

## HTML Images

```html

<img src="img_chania.jpg" alt="Flowers in Chania" width="460" height="345">

```

### Images

#### Width and Height, or Style?
The `width`, `height`, and `style` attributes are valid in HTML.

suggest using the `style` attribute. It prevents styles sheets from changing the size of images:


```html
<!DOCTYPE html>
<html>

<head>
<style>
/* This stylesheet sets the width of all images to 100%: */
img {
  width: 100%;
}
</style>
</head>

<body>

<h2>Styling Images</h2>
<p>The image below has the width attribute set to 128 pixels, but the stylesheet overrides it, and sets the width to 100%.</p>
<img src="html5.gif" alt="HTML5 Icon" width="128" height="128">

<p>The image below uses the style attribute, where the width is set to 128 pixels which overrides the stylesheet:</p>
<img src="html5.gif" alt="HTML5 Icon" style="width:128px;height:128px;">

</body>

</html>
```


#### location

```html
Images in Another Folder
<img src="/images/html5.gif" alt="HTML5 Icon" style="width:128px;height:128px;">

Images on Another Server:
<img src="https://www.w3schools.com/images/w3schools_green.jpg" alt="W3Schools.com">
```


#### Animated Images
HTML allows animated GIFs:
```html
<img src="programming.gif" alt="Computer Man" style="width:48px;height:48px;">
```


#### Image as a Link `<a href="url"> <img src="url" style="border:0;"> </a>`
To use an image as a link, put the `<img>` tag inside the `<a>` tag:

Note: `border:0;` is added to prevent IE9 (and earlier) from displaying a border around the image (when the image is a link).

```html
<a href="default.asp">
  <img src="smiley.gif" alt="HTML tutorial" style="width:42px;height:42px;border:0;">
</a>
```


#### Image Floating `<img src="url" style="float:right;">`
Use the CSS float property to let the image float to the right or to the left of a text:

```html
float to the right of the text:
<p>
  <img src="smiley.gif" alt="Smiley face" style="float:right;width:42px;height:42px;">
  Hello
</p>

float to the left
<p>
  <img src="smiley.gif" alt="Smiley face" style="float:left;width:42px;height:42px;">
  Hello
</p>
```


### Image Maps `<img src="url" alt="tag" usemap="#workmap">`  
The `<map>` tag defines an image-map. An image-map is an image with clickable areas.

may insert the `<map>` element anywhere,.

```html

<img src="workplace.jpg" alt="Workplace" usemap="#workmap">  


<map name="workmap">
 <area shape="rect" coords="34,44,270,350" alt="Computer" href="computer.htm">
 <area shape="rect" coords="290,172,333,250" alt="Phone" href="phone.htm">
 <area shape="circle" coords="337,300,44" alt="Coffee" href="coffee.htm">
</map>
```

`<img>`: add a usemap attribute:
`<map>`: element is used to create an image map, and linked to the image by name attribute:
`<area>` element: defined clickable area
- define the shape of the area, and you can choose one of these values:
- `rect` - defines a rectangular region
- `circle` - defines a circular region
- `poly` - defines a polygonal region
- `default` - defines the entire region


#### Image Map and JavaScript
A clickable area: a `link to another page`, or trigger a `JavaScript function`.

Add a click event on the `<area>` element to execute a JavaScript function:

```html
<!DOCTYPE html>
<html>
<body>

<img src="workplace.jpg" alt="Workplace" usemap="#workmap" width="400" height="379">

<map name="workmap">
  <area shape="circle" coords="337,300,44" onclick="myFunction()">
</map>


<script>
function myFunction() {
  alert("You clicked the coffee cup!");
}
</script>

</body>
</html>

```


### HTML Background Images

文字背景：
```html
<body>
<div style="background-image: url('img_girl.jpg');">
  You can specify background images.
</div>
</body>

or

<style>
div {
  background-image: url('img_girl.jpg');
}
</style>
```

网页页面背景：
```html
<style>
body {
  background-image: url('img_girl.jpg');
  background-repeat: no-repeat;            // avoid the repeating
  background-attachment: fixed;            // 拉伸至全屏
  background-size: cover;
  background-size: 100% 100%               // stretch to fit the entire image
}
</style>
```

#### Background Image

To add a background image *on an HTML element*,

**use the HTML style attribute and the CSS background-image property**
```html
<body>

<div style="background-image: url('img_girl.jpg');">
  hello.         // 只有字段大小的图片显示出来
</div>

</body>
```

Specify the background image *in the style element:*
```html
<style>
div {
  background-image: url('img_girl.jpg');
}
</style>
```

*the entire page to have a background image**
specify the background image on the `<body>` element:
```html
<style>
body {
  background-image: url('img_girl.jpg');
}
</style>
```

#### Background Repeat
- If the background image is smaller than the element,
- defalut, the image will repeat itself, horizontally and vertically, until it reaches the end of the element:
- avoid the repeating: `background-repeat: no-repeat;`


#### Background Cover
- want the background image cover the entire element, set the property `background-size: cover`. 拉伸至全屏
- make sure the entire element is always covered, set the property `background-attachment: fixed`:


#### Background Stretch
- want the background image stretch to fit the entire image in the element, you can set the property `background-size: 100% 100%`:


### HTML Picture Element
- display different pictures for different devices or screen sizes.
- the browser can choose the image that best fits the current view and/or device.

```html
<picture>
  <source media="(min-width: 650px)" srcset="img_food.jpg">
  <source media="(min-width: 465px)" srcset="img_car.jpg">
  <img src="img_girl.jpg">    这个在第一，则只显示他
</picture>
```

**When to use the Picture Element**
2 main purposes for the `<picture>` element:

1. Bandwidth
    - If you have a small screen or device, it is not necessary to load a large image file. The browser will use the first <source> element with matching attribute values, and ignore any of the following elements.

2. Format Support
    - Some browsers or devices may not support all image formats. By using the `<picture>`, add images of all formats, and the browser will use the first format it recognizes and ignore any of the following.


---

## table

An HTML table is defined with the `<table>` tag.

Each table row is defined with the `<tr>` tag.

A table header is defined with the `<th>` tag. `<thead>`

By default, table headings are bold and centered.

A table data/cell is defined with the `<td>` tag.

`<tbody> <tfoot>`


```html
<!DOCTYPE html>
<html>

<head>
<style>

table {
  font-family: arial, sans-serif;
  border-collapse: collapse;      // 只留一条边框
  border-spacing: 5px;            // 留2条边框
  width: 100%;
}

td, th {
  border: 1px solid #dddddd;      // Adding a Border
  text-align: left;               // 字体靠边对齐
  padding: 8px;                   // 字体和边框宽度
}

tr:nth-child(even) {
  background-color: #dddddd;
}
</style>
</head>
<body>

<h2>HTML Table</h2>

<table>
  <tr>
    <th>Company</th>
    <th>Contact</th>
    <th>Country</th>
  </tr>
  <tr>
    <td>Alfreds Futterkiste</td>
    <td>Maria Anders</td>
    <td>Germany</td>
  </tr>
  <tr>
    <td>Centro comercial Moctezuma</td>
    <td>Francisco Chang</td>
    <td>Mexico</td>
  </tr>
</table>


<table style="width:100%">
  <caption>Table name</caption>    // add a caption to a table
  <tr>
    <th>Name</th>
    <th colspan="2">Telephone</th>      // Cells that Span Many Columns
  </tr>
  <tr>
    <td>Bill Gates</td>
    <td>55577854</td>
    <td>55577855</td>
  </tr>
  <tr>
    <th rowspan="2">Telephone:</th>     // Cells that Span Many rows
  </tr>
    <td>1</td>
    <td>1.1</td>    
  </tr>
  <tr>
    <td>2</td>
    <td>2.1</td>
  </tr>
</table>

</body>
</html>

```


### A Special Style for One Table
add an id attribute to the table:

```html

<head>
<style>

table {            
  width:100%;
}

// 主要，可控制全部
table, th, td {               
  border: 1px solid black;
  border-collapse: collapse;
  padding: 15px;
  text-align: left;
}

// 单独的其他样式
table#t01 tr:nth-child(even) {
  background-color: #eee;
}
table#t01 tr:nth-child(odd) {
 background-color: #fff;
}
table#t01 th {
  background-color: black;
  color: white;
}

</style>
</head>


<table id="t01">
  <tr>
    <th>Firstname</th>
    <th>Lastname</th>
    <th>Age</th>
  </tr>
  <tr>
    <td>Eve</td>
    <td>Jackson</td>
    <td>94</td>
  </tr>
</table>

```

---

## HTML lists

```html

<body>

// Unordered HTML List
<ul>
  <li>Coffee</li>
  <li>Tea</li>
  <li>Milk</li>
</ul>  

// Ordered HTML List
<ol>
  <li>Coffee</li>
  <li>Tea</li>
  <li>Milk</li>
</ol>


// Description Lists
<dl>
  <dt>Coffee</dt>
  <dd>- black hot drink</dd>
  <dt>Milk</dt>
  <dd>- white cold drink</dd>
</dl>

</body>
```







































.
