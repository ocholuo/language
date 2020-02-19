# The Web Robots Pages

[toc]

## Web Robots

ref
https://ahrefs.com/blog/zh/robots-txt/


- Web Robots (Web Wanderers, Crawlers, or Spiders)
- programs that traverse the Web automatically.
- Search engines such as Google use them to index the web content,
- spammers use them to scan for email addresses, and they have many other uses.

搜索引擎通过程序robot（spider），自动访问互联网上的网页并获取网页信息。

可以在网站中创建一个纯文本文件robots.txt，在这个文件中声明该网站中不想被robot访问的部分，这样，该网站的部分或全部内容就可以不被搜索引擎收录了，或者指定搜索引擎只收录指定的内容。

User-agents（用户代理）
- 每个搜索引擎都有一个特定的用户代理。
- 可以在robots.txt文件中针对不同的用户代理分配抓取规则。
- 总共大约有上百种用户代理，以下是一些对SEO有用的用户代理：

      Google: Googlebot
      Google Images: Googlebot-Image
      Bing: Bingbot
      Yahoo: Slurp
      Baidu: Baiduspider
      DuckDuckGo: DuckDuckBot

robots.txt中的所有用户代理均严格区分大小写。
也可以使用通配符`（*）`来一次性为所有的用户代理制定规则。

    举例，想屏蔽除了谷歌以外的搜索引擎蜘蛛：
    User-agent: *
    Disallow: /

    User-agent: Googlebot
    Allow: /

在 robots.txt文件中，可以指定无数个用户代理。
- 虽然这么说，每当你指定一个新的用户代理时，它都是独立的。
- 如果你陆续为多一个用户代理制定了规则，那么第一个用户代理的规则并不适用于第二个，或者时第三个。
- 有一个例外就是，如果你针对同一个用户代理制定了多次规则，那么这些规则则会被放在一起执行。

重要提示
- 蜘蛛只会遵循准确表明详细用户代理的指令。所以上方的 robots.txt文件只会排除除谷歌蜘蛛（以及其它类型的谷歌蜘蛛）以外的搜索引擎爬虫。谷歌蜘蛛会忽略一些不太具体的用户代理声明。


## About /robots.txt
Web site owners use the `/robots.txt` file to give instructions about their site to web robots; this is called The `Robots Exclusion Protocol`.

It works likes this:
- a robot wants to vists a Web site URL, say http://www.example.com/welcome.html.
- Before it does so, it firsts checks for http://www.example.com/robots.txt, and finds:

      User-agent: *
      Disallow: /

- `User-agent: \*` : this section applies to all robots.
  - 该项的值用于描述搜索引擎robot的名字
  - 在"robots.txt"文件中，如果有多条User-agent记录说明有多个robot会受到该协议的限制，对该文件来说，至少要有一条User-agent记录。
  - 如果该项的值设为*，则该协议对任何机器人均有效，在"robots.txt"文件中，`"User-agent：*"`只能有一条。

- `Disallow: /` : tells the robot that it should not visit any pages on the site.
  - 该项的值用于描述不希望被访问到的一个URL，这个URL可以是一条完整的路径，也可以是部分的，任何以Disallow 开头的URL均不会被robot访问到。
  - 例如
  - `"Disallow: /help"` : `/help.html` 和 `/help/index.html` 都不允许搜索引擎访问
  - `"Disallow: /help/"` : 则允许robot访问`/help.html`，而不能访问`/help/index.html`。
  - Disallow记录为空，说明该网站的所有部分都允许被访问
  - 在"/robots.txt"文件中，至少要有一条Disallow记录。

- `Allow`: 定义允许搜索引擎收录的地址

- `Crawl-delay`:支持Crawl-delay参数，设置为多少秒，以等待同服务器之间连续请求(网络爬虫的礼貌策略)

- 如果"/robots.txt"是一个空文件，则对于所有的搜索引擎robot，该网站都是开放的。


There are two important considerations when using `/robots.txt`:
- robots can ignore your `/robots.txt`. Especially malware robots that scan the web for security vulnerabilities, and email address harvesters used by spammers will pay no attention.
- the `/robots.txt` file is a publicly available file. Anyone can see what sections of your server you don't want robots to use.
- So don't use `/robots.txt` to hide information.


Can I block just bad robots?
>   In theory yes, in practice, no.
    If the bad robot obeys /robots.txt, and you know the name it scans for in the User-Agent field. then you can create a section in your /robotst.txt to exclude it specifically.
    But almost all bad robots ignore /robots.txt, making that pointless.
    If the bad robot operates from a single IP address
    - block its access to your web server through `server configuration` or with a `network firewall`.
    If robots operate at lots of different IP addresses, such as hijacked PCs that are part of a large Botnet
    - becomes more difficult. The best option, use `advanced firewall rules configuration` that automatically block access to IP addresses that make many connections;
    - but that can hit good robots as well your bad robots.


Why did this robot ignore my /robots.txt?
> these days it's more likely that the robot is explicitly written to scan your site for information to abuse: it might be collecting email addresses to send email spam, look for forms to post links ("spamdexing"), or security holes to exploit.


What are the security implications of /robots.txt?
> There is no law stating that /robots.txt must be obeyed, nor does it constitute a binding contract between site owner and user, but having a /robots.txt can be relevant in legal cases.


Surely listing sensitive files is asking for trouble?
> listing pages or directories in the /robots.txt file may invite unintended access. There are two answers to this.
The first answer is a workaround: You could put all the files you don't want robots to visit in a separate sub directory, make that directory un-listable on the web (by configuring your server), then place your files in there, and list only the directory name in the /robots.txt. Now an ill-willed robot won't traverse that directory unless you or someone else puts a direct link on the web to one of your files, and then it's not /robots.txt fault.
For example, rather than:
`User-Agent: *`
`Disallow: /foo.html`
`Disallow: /bar.html`
do:
`User-Agent: *`
`Disallow: /norobots/`
make a "norobots" directory, put foo.html and bar.html into it, and configure your server to not generate a directory listing for that directory. attacker won't be able to list the files in there
However, in practice this is a bad idea -- it's too fragile. Someone may publish a link to your files on their site. Or it may turn up in a publicly accessible log file, say of you user's proxy server, or maybe it will show up in someone's web server log as a Referer. Or someone may misconfigure your server at some future date, "fixing" it to show a directory listing. Which leads me to the real answer:
The real answer is that /robots.txt is not intended for access control, so don't try to use it as such. Think of it as a "No Entry" sign, not a locked door. If you have files on your web site that you don't want unauthorized people to access, then configure your server to do authentication, and configure appropriate authorization. Basic Authentication has been around since the early days of the web (and in e.g. Apache on UNIX is trivial to configure). Modern content management systems support access controls on individual pages and collections of resources.


## How to create a /robots.txt file

- The /robots.txt is a de-facto standard, and is not owned by any standards body.
- The /robots.txt standard is not actively developed.

### Where to put it
in the top-level directory of your web server.
- When a robot looks for the "/robots.txt" file for URL, it strips the path component from the URL (everything from the first single slash), and puts "/robots.txt" in its place.
  - For example
  - for http://www.example.com/shop/index.html
  - http://www.example.com/robots.txt
- Usually the same place where put your web site's main "index.html" welcome page.

- how to put the file there, depends on your web server software.
- Remember to use all lower case for the filename: "robots.txt", not "Robots.TXT.

See also:

What program should I use to create /robots.txt
> You can use anything that produces a text file.
> - On Microsoft Windows, use notepad.exe, wordpad.exe (Save as Text Document), or Microsoft Word (Save as Plain Text)
> - On the Macintosh, use TextEdit (Format->Make Plain Text, then Save as Western)
> - On Linux, vi or emacs

How do I use /robots.txt on a virtual host?
> The term "virtual host" is sometimes use to mean various different things:
A "virtual host" web server uses the HTTP Host Header to distinguish requests to different domain names on the same IP address. In this case the fact that the domain is on a shared host makes no difference to a visitng robot, and you can put a /robots.txt file in the directory dedicated to your domain.
A "virtual server" runs a separate operating system on a virtual machine, like VMWare or Xen. Again, to a robot that's a separate computer.

How do I use /robots.txt on a shared host?
> If you share a host with other people, and you have a URL like http://www.example.com/~username/ or http://www.example.com/username, then you can't have your own /robots.txt file. If you want to use /robots.txt you'll have to ask the host administrator to help you. If you want more control, switch to a provider with a virtual host.


### What to put in it
The "/robots.txt" file is a `text` file, with one or more records. Usually contains a single record looking like this:

    User-agent: *
    Disallow: /cgi-bin/
    Disallow: /tmp/
    Disallow: /~joe/

- need a separate "Disallow" line for every URL prefix to exclude -- cannot say "Disallow: /cgi-bin/ /tmp/" on a single line.
- Also, you may not have blank lines in a record, as they are used to delimit multiple records.
- globbing and regular expression are not supported in either the User-agent or Disallow lines.
- The `'*'` in the User-agent field is a special value meaning "any robot".
- Specifically, you cannot have lines like `"User-agent: *bot*"`, `"Disallow: /tmp/*" or "Disallow: *.gif"`.


### examples:

```py
# To exclude all robots from the entire server
User-agent: *
Disallow: /

# To allow all robots complete access
User-agent: *
Disallow:
(or just create an empty "/robots.txt" file,
or dont use one at all)

# To exclude all robots from part of the server
User-agent: *
Disallow: /cgi-bin/
Disallow: /tmp/
Disallow: /junk/

# To exclude a single robot
User-agent: BadBot
Disallow: /

# To allow a single robot
User-agent: Google
Disallow:

User-agent: *
Disallow: /

# To exclude all files except one
as there is no "Allow" field.
The easy way is to put all files to be disallowed into a separate directory, say "stuff", and leave the one file in the level above this directory:

User-agent: *
Disallow: /~joe/stuff/

Alternatively you can explicitly disallow all disallowed pages:
User-agent: *
Disallow: /~joe/junk.html
Disallow: /~joe/foo.html
Disallow: /~joe/bar.html


User-agent: *
# 这里的*代表的所有的搜索引擎种类，*是一个通配符

Disallow: /admin/
# 这里定义是禁止爬寻admin目录下面的目录

Disallow: /cgi-bin/*.htm
# 禁止访问/cgi-bin/目录下的所有以".htm"为后缀的URL(包含子目录)。

Disallow: /*?*
# 禁止访问网站中所有的动态页面

Disallow: /jpg$
# 禁止抓取网页所有的.jpg格式的图片

Disallow:/ab/adc.html
# 禁止爬去ab文件夹下面的adc.html文件。

Allow: /cgi-bin/　
# 这里定义是允许爬寻cgi-bin目录下面的目录

Allow: /tmp
# 这里定义是允许爬寻tmp的整个目录

Allow: .htm$
# 仅允许访问以".htm"为后缀的URL。

Allow: .gif$
# 允许抓取网页和gif格式图片

Crawl-delay: 10


```

## About the Robots `<META>` tag
use a special HTML <META> tag to tell robots not to index the content of a page, and/or not scan it for links to follow.

    <html>
    <head>
    <title>...</title>
    `<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">`
    </head>

There are two important considerations when using the robots `<META>` tag:
- robots can ignore your <META> tag. Especially malware robots that scan the web for security vulnerabilities, and email address harvesters used by spammers will pay no attention.
- the `NOFOLLOW` directive only applies to links on this page. It's entirely likely that a robot might find the same links on some other page without a `NOFOLLOW` (perhaps on some other site), and so still arrives at your undesired page.
- Don't confuse this NOFOLLOW with the rel="nofollow" link attribute.


Like the /robots.txt, the robots META tag is a de-facto standard. It originated from a "birds of a feather" meeting at a 1996 distributed indexing workshop, and was described in meeting notes.

The META tag is also described in the HTML 4.01 specification, Appendix B.4.1.

### Where to put it
Like any `<META>` tag it should be placed in the HEAD section of an HTML page

You should put it in every page on your site, because a robot can encounter a deep link to any page on your site.

### What to put into it
The "NAME" attribute must be "ROBOTS".

- Valid values for the "CONTENT" attribute are: "INDEX", "NOINDEX", "FOLLOW", "NOFOLLOW".
- Multiple comma-separated values are allowed, but obviously only some combinations make sense. If there is no robots <META> tag, the default is "INDEX,FOLLOW", so there's no need to spell that out. That leaves:

```
<META NAME="ROBOTS" CONTENT="NOINDEX, FOLLOW">
<META NAME="ROBOTS" CONTENT="INDEX, NOFOLLOW">
<META NAME="ROBOTS" CONTENT="NOINDEX, NOFOLLOW">
```
























.
