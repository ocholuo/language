# Natas

[toc]

Natas teaches the basics of serverside web-security.
- Each level of natas consists of its own website located at `http://natasX.natas.labs.overthewire.org`, where X is the level number.
- There is no SSH login. To access a level, enter the username for that level (e.g. natas0 for level 0) and its password.


- many websites such as Facebook, Google, and even Amazon, store information in SQL Databases. These databases are connected to the web servers, allowing them to process user transactions, login requests, and a countless amount of other things!
- These servers also handle user encryption, session keys, etc. One coding mistake; allowing a malicious attacker to inject SQL code into a query, or even inject special characters into a form, or the URL, can be devastating!

---

## Natas Level 0

Start here:
- Username: natas0
- Password: natas0
- URL:      http://natas0.natas.labs.overthewire.org

Right Click > View Page Source.

```
<body>
<h1>natas0</h1>
<div id="content">
You can find the password for the next level on this page.

<!--The password for natas1 is gtVrDuiDfck831PqWsLEZy5gyDz1clto -->
</div>
</body>
```

---

## Natas Level 0 → Level 1
Username: natas1
URL:      http://natas1.natas.labs.overthewire.org

- can’t use Right Click
- bring up the `developer window` > sources > index

访问 DevTools | 在 Windows 上	| 在 Mac 上
---|---|---
打开 Developer Tools              | F12、Ctrl + Shift + I	| Cmd + Opt + I
打开/切换检查元素模式和浏览器窗口	     | Ctrl + Shift + C	      | Cmd + Shift + C
打开 Developer Tools 并聚焦到控制台  | Ctrl + Shift + J	     | Cmd + Opt + J
检查检查器（取消停靠第一个后按）	     | Ctrl + Shift + I	       | Cmd + Opt + I

```py
<html>

<head>
  <!-- This stuff in the header has nothing to do with the level -->
  <link rel="stylesheet" type="text/css" href="http://natas.labs.overthewire.org/css/level.css">
  <link rel="stylesheet" href="http://natas.labs.overthewire.org/css/jquery-ui.css" />
  <link rel="stylesheet" href="http://natas.labs.overthewire.org/css/wechall.css" />
  <script src="http://natas.labs.overthewire.org/js/jquery-1.9.1.js"></script>
  <script src="http://natas.labs.overthewire.org/js/jquery-ui.js"></script>
  <script src=http://natas.labs.overthewire.org/js/wechall-data.js></script><script src="http://natas.labs.overthewire.org/js/wechall.js"></script>
  <script>var wechallinfo = { "level": "natas1", "pass": "gtVrDuiDfck831PqWsLEZy5gyDz1clto" };</script>
</head>

<body oncontextmenu="javascript:alert('right clicking has been blocked!');return false;">
  <h1>natas1</h1>
  <div id="content">
  You can find the password for the
  next level on this page, but rightclicking has been blocked!
  <!--The password for natas2 is ZluruAthQk7Q2MqmDeTiUij2ZvWy2mBi -->
  </div>
</body>

</html>
```

---

# Natas Level 1 → Level 2
Username: natas2
URL:      http://natas2.natas.labs.overthewire.org
ZluruAthQk7Q2MqmDeTiUij2ZvWy2mBi

```py
-------------------------------------------------------------------
<body>
<h1>natas2</h1>
<div id="content">
There is nothing on this page
<img src="files/pixel.png">      # a image file linked in the HTML code.
</div>
</body>
-------------------------------------------------------------------
add /files to the end of the URL.
http://natas2.natas.labs.overthewire.org/files/. see a page displayed;

Index of /files
[ICO]	Name	Last modified	Size	Description
[PARENTDIR]	Parent Directory	 	-	 
[IMG]	pixel.png	2016-12-15 16:07	303	 
[TXT]	users.txt	2016-12-20 05:15	145	 
-------------------------------------------------------------------
open in new Windows
# username:password
alice:BYNdCesZqW
bob:jw2ueICLvT
charlie:G5vCxkVV3m
natas3:sJIJNW6ucpu6HPZ1ZAchaDtwd7oGrD14
eve:zo4mJWyNj2
mallory:9urtcpzBmH
-------------------------------------------------------------------
```
---

## Natas Level 2 → Level 3
Username: natas3
URL:      http://natas3.natas.labs.overthewire.org
sJIJNW6ucpu6HPZ1ZAchaDtwd7oGrD14


```py
-------------------------------------------------------------------
# Page Source
<body>
  <h1>natas3</h1>
  <div id="content">
  There is nothing on this page
  <!-- No more information leaks!! Not even Google will find it this time... -->
  </div>
</body>

“Not even Google will find it this time…” is hint. I
it’s referring to robots.txt.

# http://natas3.natas.labs.overthewire.org/robots.txt
User-agent: *
Disallow: /s3cr3t/

so the robots.txt is disallowing crawlers to find /s3cr3t/.
# http://natas3.natas.labs.overthewire.org/s3cr3t/.
Index of /s3cr3t
[ICO]	Name	Last modified	Size	Description
[PARENTDIR]	Parent Directory	 	-	 
[TXT]	users.txt	2016-12-20 05:15	40	 

natas4:Z9tkRkWmpt9Qr7XrR5jWRkgOU901swEZ
```

---

## Natas Level 3 → Level 4
Username: natas4
URL:      http://natas4.natas.labs.overthewire.org
Z9tkRkWmpt9Qr7XrR5jWRkgOU901swEZ

- Access disallowed. You are visiting from "" while authorized users should come only from "http://natas5.natas.labs.overthewire.org/"
































.