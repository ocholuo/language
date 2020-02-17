# git

[toc]

# git 常用命令详解

ref
https://www.jianshu.com/p/d220c88bb516
https://www.yiibai.com/git/git_basic_concepts.html
https://www.softwhy.com/article-8699-1.html
https://www.yiibai.com/git/git_commit.html

## 前提条件

![683090701_88630](https://i.imgur.com/uXbs4JQ.png)

- `Workspace`：工作区
  - 通过 `git init` 创建的代码库的所有文件, 但是不包括 `.git文件(版本库)`
- `Index/Stage`：暂存区/索引
  - 通过 `git add ./*/*Xxx/Xxxx*` 添加的修改,都是进入到暂存区了,肉眼不可见 通过 `git status` 可以看到修改的状态。
- `Repository`：仓库区（或本地仓库）/存储库
- `Remote`：远程仓库


## 版本控制
版本控制是一种记录一个或若干文件内容变化，以便将来查阅特定版本修订情况的系统。在本书所展示的例子中，我们对保存着软件源代码的文件作版本控制，但实际上，可以对任何类型的文件进行版本控制。

- 本地版本控制系统
  - 人们很久以前就开发了许多种本地版本控制系统，大多都是采用某种简单的数据库来记录文件的历次更新差异。
  - 其中最流行的一种叫做 `RCS`，现今许多计算机系统上都还看得到它的踪影。 甚至在流行的 Mac OS X 系统上安装了开发者工具包之后，也可以使用 rcs 命令。
  - 它的工作原理是在硬盘上保存补丁集(补丁是指文件修订前后的变化)；通过应用所有的补丁，可以重新计算出各个版本的文件内容。

![121150723_18943](https://i.imgur.com/enYJT8r.png)

- 集中化的版本控制系统
  - 如何让在不同系统上的开发者协同工作？
  - 于是，集中化的版本控制系统 `CVCS (Centralized Version Control Systems)`应运而生。 这类系统，诸如 CVS、Subversion(SVN) 以及 Perforce 等，都有一个单一的集中管理的服务器，保存所有文件的修订版本，而协同工作的人们都通过客户端连到这台服务器，取出最新的文件或者提交更新。多年以来，这已成为版本控制系统的标准做法。

![323150725_63776](https://i.imgur.com/PqLFgAt.png)

  - 这种做法带来了许多好处，特别是相较于老式的本地 VCS 来说。 现在，每个人都可以在一定程度上看到项目中的其他人正在做些什么。 而管理员也可以轻松掌控每个开发者的权限，并且管理一个 CVCS 要远比在各个客户端上维护本地数据库来得轻松容易。
  - 事分两面，有好有坏。 最显而易见的缺点是中央服务器的单点故障。 如果宕机一小时，那么在这一小时内，谁都无法提交更新，也就无法协同工作。 如果中心数据库所在的磁盘发生损坏，又没有做恰当备份，毫无疑问您将丢失所有数据——包括项目的整个变更历史，只剩下人们在各自机器上保留的单独快照。本地版本控制系统也存在类似问题，只要整个项目的历史记录被保存在单一位置，就有丢失所有历史更新记录的风险。

- 分布式版本控制系统
  - 分布式版本控制系统 `DVCS(Distributed Version Control System)`面世了。
  - 在这类系统中，像 Git、Mercurial、Bazaar 以及 Darcs 等，客户端并不只提取最新版本的文件快照，而是把代码仓库完整地镜像下来。 这么一来，任何一处协同工作用的服务器发生故障，事后都可以用任何一个镜像出来的本地仓库恢复。 因为每一次的克隆操作，实际上都是一次对代码仓库的完整备份。

![227150731_13573](https://i.imgur.com/VYBz9ws.png)

  - 更进一步，许多这类系统都可以指定和若干不同的远端代码仓库进行交互。藉此，你就可以在同一个项目中，分别和不同工作小组的人相互协作。可以根据需要设定不同的协作流程，比如层次模型式的工作流，而这在以前的集中式系统中是无法实现的。


## Git基础和原理
Git 在保存和对待各种信息的时候与其它版本控制系统有很大差异，尽管操作起来的命令形式非常相近

## 直接记录快照，非差异比较
- 和其它版本控制系统(包括 Subversion 和近似工具)的主要差别在于 Git 对待数据的方法。
  - 概念: 其它大部分系统以`文件变更列表`的方式存储信息。 这类系统(CVS、Subversion、Perforce、Bazaar 等等)将它们保存的信息看作是一组基本文件和每个文件随时间逐步累积的差异。存储每个文件与初始版本的差异.

![919150744_33189](https://i.imgur.com/yzw0nkm.png)

  - Git 不按照以上方式对待或保存数据。 Git 更像是把数据看作是对小型文件系统的一组快照。
    - 每次提交更新，或在 Git 中保存项目状态时，它主要对当时的全部文件制作一个快照并保存这个快照的索引。
    - 为了高效，如果文件没有修改，Git 不再重新存储该文件，而是只保留一个链接指向之前存储的文件。
    - Git 对待数据更像是一个 快照流。
    - 这是 Git 与几乎所有其它版本控制系统的重要区别。 因此 Git 重新考虑了以前每一代版本控制系统延续下来的诸多方面。 Git 更像是一个小型的文件系统，提供了许多以此为基础构建的超强工具，而不只是一个简单的 VCS。在 Git 分支讨论 Git 分支管理时，将探究这种方式对待数据所能获得的益处。

![480150745_86943](https://i.imgur.com/z7SWxnE.png)

### 近乎所有操作都是本地执行
- 在 Git 中的绝大多数操作都只需要访问本地文件和资源，一般不需要来自网络上其它计算机的信息。因为你在本地磁盘上就有项目的完整历史，所以大部分操作看起来瞬间完成。
  - 例子
  - 浏览项目历史，Git 不需外连到服务器去获取历史，然后再显示出来, 它只需直接从本地数据库中读取, 能立即看到项目历史。
  - 查看当前版本与一个月前的版本之间引入的修改，Git 会查找到一个月前的文件做一次本地的差异计算，而不是由远程服务器处理或从远程服务器拉回旧版本文件再来本地处理。
- 意味着你离线时，几乎可以进行任何操作。使用其它系统，做到如此是不可能或很费力的。比如 Perforce，你没有连接服务器时几乎不能做什么事；Subversion 和 CVS，你能修改文件，但不能向数据库提交修改(因为你的本地数据库离线了)。 这

### Git 保证完整性
- Git 中所有数据在存储前都计算校验和，然后以校验和来引用。
- 这意味着不可能在 Git 不知情时更改任何文件内容或目录内容。 这个功能建构在 Git 底层，是构成 Git 哲学不可或缺的部分。 若你在传送过程中丢失信息或损坏文件，Git 就能发现。
- Git 用以计算校验和的机制叫做 SHA-1 hash。由 40 个十六进制字符(0-9,a-f)组成，基于 Git 中文件的内容或目录结构计算出来。
- SHA-1 哈希看起来是这样：24b9da6552252987aa493b52f8696cd6d3b0037
Shell
- 实际上，Git 数据库中保存的信息都是以文件内容的哈希值来索引，而不是文件名。

### Git 一般只添加数据
- 执行的 Git 操作，几乎只往 Git 数据库中增加数据。
- 很难让 Git 执行任何不可逆操作，或者让它以任何方式清除数据。
- 同别的 VCS 一样，未提交更新时有可能丢失或弄乱修改的内容；但是一旦你提交快照到 Git 中，就难以再丢失数据，特别是如果你定期的推送数据库到其它仓库的话。

### 三种状态
Git 有三种状态：
- 已提交(committed) : 表示数据已经安全的保存在本地数据库中
- 已修改(modified) : 表示修改了文件，但还没保存到数据库中。
- 已暂存(staged) : 表示对一个已修改文件的当前版本做了标记，使之包含在下次提交的快照中

Git 项目的三个工作区域：

- `working directory 工作目录 ` : 对项目的某个版本独立提取出来的内容。
  - 这些从 Git 仓库的压缩数据库中提取出来的文件，放在磁盘上供你使用或修改。
- `staging area 暂存区域` : 暂存区域是一个文件，保存了下次将提交的文件列表信息，一般在 Git 仓库目录中。
  - 有时候也被称作‘索引’
- `.git directory(respository) 仓库目录` : 用来保存项目的元数据和对象数据库的地方。
  - Git 中最重要的部分，从其它计算机克隆仓库时拷贝的就是这里的数据。

![744160702_48164](https://i.imgur.com/1q6k8GZ.png)

基本的 Git 工作流程如下：
- 在工作目录中修改文件。
- 暂存文件，将文件的快照放入暂存区域。
- 提交更新，找到暂存区域的文件，将快照永久性存储到 Git 仓库目录。

如果 Git 目录中保存着的特定版本文件，就属于已提交状态。 如果作了修改并已放入暂存区域，就属于已暂存状态。 如果自上次取出后，作了修改但还没有放到暂存区域，就是已修改状态。


## Git安装设置
Linux 上安装
- 用二进制安装程序来安装 Git，可以使用发行版包含的基础软件包管理工具来安装。
- Fedora 上，用 yum：`$ sudo yum install git`
- 在 Debian 的发行版上，用 apt-get：`$ sudo apt-get install git`
- 安装完成后，查看当前安装的 Git 的版本: `$ git version`

在 Mac 上安装
- 最简单的方法是安装 Xcode Command Line Tools。 Mavericks (10.9) 或更高版本的系统中，在 Terminal 里尝试首次运行 git 命令即可。 如果没有安装过命令行开发者工具，将会提示你安装。
- 可以使用二进制安装程序。OSX Git 安装程序可以在 Git 官方网站下载，网址为 http://git-scm.com/download/mac。
- Git OS X 安装程序.你也可以将它作为 GitHub for Mac 的一部分来安装。 它们的图形化 Git 工具有一个安装命令行工具的选项。 你可以从 GitHub for Mac 网站下载该工具，网址为 http://mac.github.com。

在 Windows 上安装
- 官方版本可以在 Git 官方网站下载。 打开 http://git-scm.com/download/win ，下载会自动开始。 要注意这是一个名为 Git for Windows的项目(也叫做 msysGit)，和 Git 是分别独立的项目；
- 另一个简单的方法是安装 GitHub for Windows。 该安装程序包含图形化和命令行版本的 Git。 它也能支持 Powershell，提供了稳定的凭证缓存和健全的 CRLF 设置

从源代码安装
- 从源码安装 Git 更实用，因为你能得到最新的版本。 二进制安装程序倾向于有一些滞后，当然近几年 Git 已经成熟，这个差异不再显著。
注意：从源码安装 Git 依懒包会比较，可能比较费时。
- 从源码安装 Git，需要安装 Git 依赖的库：curl、zlib、openssl、expat，还有libiconv。
- 如果你的系统上有 yum (如 Fedora)或者 apt-get(如基于 Debian 的系统)，可以使用以下命令之一来安装最小化的依赖包来编译和安装 Git 的二进制版：

      $ sudo yum install curl-devel expat-devel gettext-devel
          openssl-devel zlib-devel

      $ sudo apt-get install libcurl4-gnutls-dev libexpat1-dev gettext
          libz-dev libssl-dev

为了能够添加更多格式的文档(如 doc, html, info)，你需要安装以下的依赖包：

      $ sudo yum install asciidoc xmlto docbook2x
      $ sudo apt-get install asciidoc xmlto docbook2x

当你安装好所有的必要依赖，你可以继续从几个地方来取得最新发布版本的 tar 包。
- 从 Kernel.org 网站获取，网址为 http://www.kernel.org/pub/software/scm/git，
- 从 GitHub 网站上的镜像来获得，网址为 http://github.com/git/git/releases。 通常在 GitHub 上的是最新版本，但 kernel.org 上包含有文件下载签名，如果你想验证下载正确性的话会用到。
- 接着，编译并安装：

       $ tar -zxf git-2.0.0.tar.gz
       $ cd git-2.0.0
       $ make configure
       $ ./configure --prefix=/usr
       $ make all doc info
       $ sudo make install install-doc install-html install-info

完成后，使用 Git 来获取 Git 的升级：`$ git clone git://git.kernel.org/pub/scm/git/git.git`

---

...

---

## 从Git 版本库的初始化

通常两种方式：

1. `git clone` : 当已有一个远程的Git版本库，只需要在本地克隆一份
    - `git clone` git://github.com/someone/some_project.git some_project
    - 将'git://github.com/someone/some_project.git'这个URL地址的远程版本库，完全克隆到本地some_project目录下

2. `git init` 和 `git remote`：
    - 当你本地创建了一个工作目录，你可以进入这个目录，使用'git init'命令进行初始化；Git以后就会对该目录下的文件进行版本控制，这时候如果你需要将它放到远程服务器上，可以在远程服务器上创建一个目录，并把可访问的URL记录下来，此时你就可以利用'git remote add'命令来增加一个远程服务器端，
    - `git remote` `add` origin git://github.com/someone/another_project.git
      - 增加URL地址为'git://github.com/someone/another_project.git'，名称为origin的远程服务器
      - 以后提交代码的时候只需要使用 origin别名即可

## Git 常用命令
### 远程仓库相关命令
- 检出仓库：$ `git clone` git://github.com/jquery/jquery.git
- 查看远程仓库：$ git remote `-v`
- 添加远程仓库：$ git remote `add [name] [url]`
- 删除远程仓库：$ git remote `rm [name]`
- 修改远程仓库：$ git remote `set-url --push [name] [newUrl]`
- 拉取远程仓库：$ `git pull` [remoteName] [localBranchName]
- 推送远程仓库：$ `git push` [remoteName] [localBranchName]

如果想把本地某分支test提交到远程仓库，并作为远程仓库的master分支，或者作为另外一个名叫test的分支
- $ git push origin test:master // 提交本地test分支作为远程的master分支
- $ git push origin test:test   // 提交本地test分支作为远程的test分支

### 分支(branch)操作相关命令

查看本地分支：`$ git branch`
查看远程分支：$ git branch -r
创建本地分支：$ git branch [name] ----注意新分支创建后不会自动切换为当前分支

#### 切换分支：`$ git checkout [name]`

创建新分支并立即切换到新分支：$ git checkout -b [name]

删除分支：$ git branch -d [name]
  - -d选项只能删除已经参与了合并的分支，对于未有合并的分支是无法删除的。如果想强制删除一个分支，可以使用-D选项

合并分支：$ git merge [name] ----将名称为[name]的分支与当前分支合并

创建远程分支(本地分支push到远程)：$ git push origin [name]

删除远程分支：$ git push origin :heads/[name] 或 $ git push origin :[name]

创建空的分支：(执行命令之前记得先提交你当前分支的修改，否则会被强制删干净没得后悔)

$git symbolic-ref HEAD refs/heads/[name]

$rm .git/index

$git clean -fdx

### 版本(tag)操作相关命令

查看版本：`$ git tag`

创建版本：$ git tag [name]

删除版本：$ git tag -d [name]

查看远程版本：`$ git tag -r`

创建远程版本(本地版本push到远程)：$ git push origin [name]

删除远程版本：$ git push origin :refs/tags/[name]

合并远程仓库的tag到本地：$ git pull origin --tags

上传本地tag到远程仓库：$ git push origin --tags

创建带注释的tag：$ git tag -a [name] -m 'yourMessage'

### 子模块(submodule)相关操作命令

添加子模块：$ git submodule add [url] [path]

如：$git submodule add git://github.com/soberh/ui-libs.git src/main/webapp/ui-libs

初始化子模块：$ git submodule init ----只在首次检出仓库时运行一次就行

更新子模块：$ git submodule update ----每次更新或切换分支后都需要运行一下

删除子模块：（分4步走哦）

$ git rm --cached [path]

编辑“.gitmodules”文件，将子模块的相关配置节点删除掉

编辑“ .git/config”文件，将子模块的相关配置节点删除掉

手动删除子模块残留的目录

5、忽略一些文件、文件夹不提交

在仓库根目录下创建名称为“.gitignore”的文件，写入不需要的文件夹名或文件，每个元素占一行即可，如
```
target

bin

*.db
```

附： Git 常用命令速查
git branch 查看本地所有分支

`git status` 查看当前状态

git commit 提交

`git branch -a` 查看所有的分支

`git branch -r` 查看远程所有分支

git commit -am "init" 提交并且加注释

git remote add origin git@192.168.1.119:ndshow

git push origin master 将文件给推到服务器上

git remote show origin 显示远程库origin里的资源

git push origin master:develop

git push origin master:hb-dev 将本地库与服务器上的库进行关联

git checkout --track origin/dev 切换到远程dev分支

git branch -D master develop 删除本地库develop

git checkout -b dev 建立一个新的本地分支dev

git merge origin/dev 将分支dev与当前分支进行合并

git checkout dev 切换到本地dev分支

git remote show 查看远程库

### `git add`

git add [--verbose | -v] [--dry-run | -n] [--force | -f] [--interactive | -i] [--patch | -p]
      [--edit | -e] [--[no-]all | --[no-]ignore-removal | [--update | -u]]
      [--intent-to-add | -N] [--refresh] [--ignore-errors] [--ignore-missing]
      [--chmod=(+|-)x] [--] [<pathspec>…​]

将文件内容添加到索引(将修改添加到暂存区)。将要提交的文件的信息添加到索引库中。
- 将要提交的文件的信息添加到索引库中(将修改添加到暂存区)，以准备为下一次提交分段的内容。
- 它通常将现有路径的当前内容作为一个整体添加，但是通过一些选项，它也可以用于添加内容，只对所应用的工作树文件进行一些更改，或删除工作树中不存在的路径了。
- “索引”保存工作树内容的快照，并且将该快照作为下一个提交的内容。 因此，在任何更改之后，并且在运行`git commit`命令之前，必须使用`git add`命令将任何新的或修改的文件添加到索引。
- 该命令可以在提交之前多次执行。它只在运行git add命令时添加指定文件的内容;
- 如果希望随后的更改包含在下一个提交中，那么必须再次运行git add将新的内容添加到索引。

`git status` 可用于获取哪些文件具有为下一次提交分段的更改的摘要。

    $ git status
    On branch master
    Your branch is up-to-date with 'origin/master'.
    Changes to be committed:
      (use "git reset HEAD <file>..." to unstage)
    	new file:   key.txt

默认情况下，`git add`命令不会添加忽略的文件。
- 如果在命令行上显式指定了任何忽略的文件，git add命令都将失败，并显示一个忽略的文件列表。
- 由Git执行的目录递归或文件名遍历所导致的忽略文件将被默认忽略。
- `git add-f(force)`选项添加被忽略的文件。

```py
示例
添加documentation目录及其子目录下所有*.txt文件的内容：
$ git add documentation/*.txt
# 星号*是从shell引用的;
# 允许命令包含来自 Documentation/目录和子目录的文件。

将所有 git-*.sh 脚本内容添加：
$ git add git-*.sh
# 这个例子让shell扩展星号(即明确列出文件)，所以它不考虑子目录中的文件，
# subdir/git-foo.sh 这样的文件不会被添加。
```

基本用法 `git add <path>`
- 把<path>添加到索引库中，<path>可以是文件也可以是目录。
- git不仅能判断出<path>中，修改(不包括已删除)的文件，还能判断出新添的文件，并把它们的信息添加到索引库中。

```py
$ git add .             # 将所有修改添加到暂存区
$ git add *             # Ant风格添加修改
$ git add *Controller   # 将以Controller结尾的文件的所有修改添加到暂存区

$ git add Hello*        # 将所有以Hello开头的文件的修改添加到暂存区
                        # HelloWorld.txt,Hello.java,HelloGit.txt ...

$ git add Hello?        # 将以Hello开头后面只有一位的文件的修改提交到暂存区
                        # 如: Hello1.txt, HelloA.java
                        # HelloGit.txt, Hello.java是不会被添加的
```

- `git add -u [<path>]`: 把<path>中所有`跟踪文件中被修改过或已删除文件的信息`添加到索引库。
  - 它不会处理那些不被跟踪的文件。
  - 省略<path>表示 . ,即当前目录。
- `git add -A`: 把中所有`跟踪文件中被修改过或已删除文件`和`所有未跟踪的文件信息`添加到索引库。
  - 省略<path>表示 . ,即当前目录。
- `git add -i`
  - 通过git add -i [<path>]: 查看中被所有修改过或已删除文件但没有提交的文件，并通过其revert子命令可以查看<path>中所有未跟踪的文件，同时进入一个子命令系统。

        $ git add -i
                   staged     unstaged path
          1:        +0/-0      nothing branch/t.txt
          2:        +0/-0      nothing branch/t2.txt
          3:    unchanged        +1/-0 readme.txt

        *** Commands ***
          1: [s]tatus     2: [u]pdate     3: [r]evert     4: [a]dd untracked
          5: [p]atch      6: [d]iff       7: [q]uit       8: [h]elp

        这里的t.txt和t2.txt表示已经被执行了git add，待提交。即已经添加到索引库中。
        readme.txt表示已经处于tracked下，它被修改了，但是还没有执行git add。即还没添加到索引库中。


---

git rm 文件名(包括路径) 从git中删除指定文件

git clone git://github.com/schacon/grit.git 从服务器上将代码给拉下来

git config --list 看所有用户

git ls-files 看已经被提交的

git rm [file name] 删除一个文件


### `git commit -a` 提交当前repos的所有的改变

git commit [-a | --interactive | --patch] [-s] [-v] [-u<mode>] [--amend]
       [--dry-run] [(-c | -C | --fixup | --squash) <commit>]
       [-F <file> | -m <msg>] [--reset-author] [--allow-empty]
       [--allow-empty-message] [--no-verify] [-e] [--author=<author>]
       [--date=<date>] [--cleanup=<mode>] [--[no-]status]
       [-i | -o] [-S[<keyid>]] [--] [<file>…​]

用于将更改记录(提交)到存储库。
- 将索引的当前内容与描述更改的用户和日志消息一起存储在新的提交中。

要添加的内容可以通过以下几种方式指定：
- 用`git commit`之前，用`git add`对索引进行递增的“添加”更改(注意：修改后的文件的状态必须为“added”);
- 用`git rm`从工作树和索引中删除文件，再次用`git commit`命令;
- 通过将文件作为参数列出到`git commit`命令(不使用--interactive或--patch选项)，在这种情况下，提交将忽略索引中分段的更改，而是记录列出的文件的当前内容(必须已知到Git的内容) ;
- 通过使用带有`-a`选项的`git commit`命令来自动从所有已知文件(即所有已经在索引中列出的文件)中添加“更改”，并自动从已从工作树中删除索引中的“rm”文件 ，然后执行实际提交;
- 通过使用`--interactive`或`--patch`选项与`git commit`命令一起确定除了索引中的内容之外哪些文件或hunks应该是提交的一部分，然后才能完成操作。

`--dry-run`选项可用于通过提供相同的参数集(选项和路径)来获取上一个任何内容包含的下一个提交的摘要。
如果您提交，然后立即发现错误，可以使用 `git reset` 命令恢复。

示例
提交已经被git add进来的改动。

    $ git add .
    $ # 或者 ~
    $ git add newfile.txt
    $ git commit -m "the commit message" #
    $ git commit -a
    # 会先把所有已经track的文件的改动`git add`进来，然后提交(有点像svn的一次提交,不用先暂存)。
    # 对于没有track的文件,还是需要执行`git add <file>` 命令。
    $ git commit --amend
    # 增补提交，会使用与当前提交节点相同的父节点进行一次新的提交，旧的提交将会被取消。

录制自己的工作时，工作树中修改后的文件的内容将临时存储到使用git add命名为“索引”的暂存区域。 一个文件只能在索引中恢复，而不是在工作树中，使用git reset HEAD - <file>进行上一次提交的文件，这有效地恢复了git的添加，并阻止了对该文件的更改，以参与下一个提交在使用这些命令构建状态之后，git commit(没有任何pathname参数)用于记录到目前为止已经进行了什么更改。 这是命令的最基本形式。
例子：
$ vi hello.c
$ git rm goodbye.c
$ git add hello.c
$ git commit


可以在每次更改后暂存文件，而不是在git commit中关注工作树中跟踪内容的文件的更改，可使用相应的git add和git rm。 也就是说，如果工作树中没有其他更改(hello.c文件内容不变)，则该示例与前面的示例相同：
$ vi hello.c
$ rm goodbye.c
$ git commit -a

`git commit -a`首先查看您的工作树，注意您已修改hello.c并删除了goodbye.c，并执行必要的`git add`和`git rm`


在更改许多文件之后，可以通过给出`git commit`的路径名来更改记录更改的顺序。
当给定路径名时，该命令提交只记录对命名路径所做的更改：


$ edit hello.c hello.h # 修改了这两个文件的内容
$ git add hello.c hello.h
$ edit Makefile
$ git commit Makefile

这提供了一个记录Makefile修改的提交。
在hello.c和hello.h中升级的更改不会包含在生成的提交中。
然而，它们的变化并没有消失 - 他们仍然有更改，只是被阻止。
按照上述顺序执行：
$ git commit
这个第二个提交将按照预期记录更改为hello.c和hello.h。
合并后(由`git merge`或`git pull`发起)由于冲突而停止，干净合并的路径已经被暂存为提交，并且冲突的路径保持在未加载状态。必须先检查哪些路径与git状态冲突，并在手工将其固定在工作树中之后，要像往常一样使用`git add`：
$ git status | grep unmerged
unmerged: hello.c
$ edit hello.c
$ git add hello.c
解决冲突和暂存结果后，git ls-files -u将停止提及冲突的路径。完成后，运行git commit最后记录合并：
$ git commit


---

git add [file name] 添加一个文件到git index

git commit -v 当你用－v参数的时候可以看commit的差异

git commit -m "This is the message describing the commit" 添加commit信息

git commit -a -a是代表add，把所有的change加到git index里然后再commit

git commit -a -v 一般提交命令

`git log` 看你commit的日志

`git diff` 查看尚未暂存的更新

git rm a.a 移除文件(从暂存区和工作区中删除)

git rm --cached a.a 移除文件(只从暂存区中删除)

`git commit -m` "remove" 移除文件(从Git中删除)

git rm -f a.a 强行移除修改后文件(从暂存区和工作区中删除)

git diff --cached 或 $ git diff --staged 查看尚未提交的更新

git stash push 将文件给push到一个临时空间中

git stash pop 将文件从临时空间pop下来

－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－

git remote add origin git@github.com:username/Hello-World.git

git push origin master 将本地项目给提交到服务器中

－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－

git pull 本地与服务器端同步

－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－

git push (远程仓库名) (分支名) 将本地分支推送到服务器上去。

git push origin server fix:awesome branch

－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－

git fetch 相当于是从远程获取最新版本到本地，不会自动merge

git commit -a -m "log_message" (-a是提交所有改动，-m是加入log信息) 本地修改同步至服务器端 ：

git branch branch_0.1 master 从主分支master创建branch_0.1分支

git branch -m branch_0.1 branch_1.0 将branch_0.1重命名为branch_1.0

git checkout branch_1.0/master 切换到branch_1.0/master分支

du -hs

git branch 删除远程branch

git push origin:branch_remote_name

git branch -r -d branch_remote_name

－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－

初始化版本库，并提交到远程服务器端

mkdir WebApp

cd WebApp

git init本地初始化

touch README

git add README添加文件

git commit -m 'first commit'

git remote add origin git@github.com:daixu/WebApp.git增加一个远程服务器端

上面的命令会增加URL地址为'git@github.com:daixu/WebApp.git'，名称为origin的远程服务器库，以后提交代码的时候只需要使用 origin别名即可


### `git show` 显示各种类型的对象。
`git show [options] <object>…​`

- 描述显示一个或多个对象(blobs，树，标签和提交)。
- 对于提交，它显示日志消息和文本差异。 还以 `git diff-tree --cc` 生成的特殊格式呈现合并提交。
- 对于标签，它显示标签消息和引用对象。
- 对于树，它显示的名称(相当于使用git ls-tree和--name-only选项)。
- 对于简单的blobs，它显示了普通的内容。
- 该命令采用适用于 `git diff-tree` 命令的选项来控制如何显示提交引入的更改。

```py
1. 显示标签v1，以及标签指向的对象
$ git show v1

2. 显示标签v1指向的树
$ git show v1^{tree}

3. 显示标签v1指向的提交的主题
$ git show -s --format=%s v1^{commit}

4. 显示 Documentation/README 文件的内容，它们是 next 分支的第10次最后一次提交的内容
$ git show next~10:Documentation/README

5. 将Makefile的内容连接到分支主控的头部
$ git show master:Makefile master:t/Makefile
```
git show-ref
- 可以现实本地存储库的所有可用的引用以及关联的提交ID

      $ git show-ref
      3aa4c239f729b07deb99a52f125893e162daac9e refs/heads/master
      3aa4c239f729b07deb99a52f125893e162daac9e refs/remotes/origin/HEAD
      3aa4c239f729b07deb99a52f125893e162daac9e refs/remotes/origin/master
      f17132340e8ee6c159e0a4a6bc6f80e1da3b1aea refs/tags/secret



查看文本内容（blob对象)
- 知道一个文本对象的sha-1值，那么查看方式如下：
$ git show 215ded5
蚂蚁部落

查看tree对象：
- 显示当前tree对象的目录结构，代码如下：
$ git show fac4ee5^{tree}
![015709g5zlfblo4r5ukug4](https://i.imgur.com/FhXEdD9.jpg)


查看tag标签：
- 看一下当前项目的提交历史，
$ git log --oneline
![015752py6e09yp66j5f9lp](https://i.imgur.com/DfZqlnr.jpg)

- 查看tagLearn标签
$ git show tagLearn
上面是一个轻量级标签，输出信息展示了它所指向的commit提交和所指向提交与上一次提交之间的差异。
![015831oufzo699dyudiiu9](https://i.imgur.com/WIcXoYc.jpg)

- 查看一下有附注标签信息
$ git show annotatedTag
除了显示轻量级标签相同的信息外，还显示有附注标签对象的一些信息，打标签这，打标签的时间等。
![015921x31au3liia0iz636](https://i.imgur.com/FfjSJc2.jpg)


查看commit对象：
- 显示commit对象的相关信息（提交者，提交时间和commit对象sha-1值等）和上一个提交对象的差异。
$ git show 5a97a20
![020011v16jwnbtbrpjvwwp](https://i.imgur.com/x0y5bwJ.jpg)


















.
