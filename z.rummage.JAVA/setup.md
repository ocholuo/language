# Java

[toc]

# 安装java

## Mac下如何安装JDK

1. 访问Oracle官网 http://www.oracle.com，
2. 点击“JDK DOWNLOAD”按钮：
3. 选择“Accept Lisence Agreement”同意协议:
4. 点击Mac OS X x64后面的下载链接：下载完成后点击安装包，按提示即可完成安装。
5. 打开Finder／资源库／java／javavirtualmachines／jdk-9.0.1.jdk/contents/home:
6. 其中Contents下的Home文件夹，是该JDK的根目录。
    * bin目录下: 存放JDK用于开发的一些终端命令工具。常见的工具如：
        * “javac”的作用是将java源文件编译为class文件(即自解码文件)；
        * “java”命令的作用是运行class文件。
    * db目录下是java开发的一个开源的关系型数据库；
    * include目录下是一些C语言的头文件；
    * jre目录下JDK所依赖的java运行时；
    * lib目录下存放JDK开发工具所依赖的一些库文件；
    * man目录下存放JDK开发工具的说明文档。

## 配置环境变量
安装好JDK后需要配置JDK的环境变量

1. 在英文输入法的状态下，按键盘“Ctrl + 空格”组合键，调出Spotlight搜索，在这里可以快速启动终端，输入ter,然后回车，即可打开终端：
2. 如果你是第一次配置环境变量，可以使用“touch .bash_profile” 创建一个.bash_profile的隐藏配置文件(如果你是为编辑已存在的配置文件，则使用"open -e .bash_profile"命令)：
3. 输入“open -e .bash_profile”命令：
4. 输入如下配置：

```
JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_40.jdk/Contents/Home
PATH=$JAVA_HOME/bin:$PATH:.
CLASSPATH=$JAVA_HOME/lib/tools.jar:$JAVA_HOME/lib/dt.jar:.
export JAVA_HOME
export PATH
export CLASSPATH
```
然后保存关闭该窗口。

5. 使用"source .bash_profile"使配置生效，然后输入”java -version”，如果看到jdk版本为1.8则说明配置已经生效：

## Mac下安装及配置Eclipse
运行Eclipse,第一次运行时会让你设置工作空间workspace的位置，设置在文稿下即可。至此，已经成功安装了Eclipse。

1. 打开Eclipse的偏好设置，我们需要对Eclipse做一下配置
2. 从左侧栏选择java下面的Installed JREs,然后点击Add按钮，我们要为Eclipse配置JDK：
3. 选择“Standard VM”后点击“Next”按钮：
4. 在Add JRE界面中点击JRE home后面的那个“Directory”按钮，选择安装JDK时的Home文件夹路径(参考 Mac下如何安装JDK 中的第5和6步)：
5. 输入JRE的名称(如： JRE1_8),点击“Finish”按钮：
6. 勾选这个新增的JRE，点击右下角的Apply按钮，应用该配置：
7. 在左侧栏General的Workspace下确定编码为UTF-8，点击Apply应用配置(如果你看到默认的编码就是UTF－8，那就可以不管)：
8. 在左侧栏顶部搜索”spelling”，找到spelling后取消拼写检查的选项(因为Eclipse的拼写检查比较弱智)：
9. 现在我们已经完成了Eclipse的配置！

## Mac下的常用Shell命令
今天介绍一下在Mac的终端中一些常用的Shell命令：

1. `pwd`(print work directiory):查看当前工作目录的完整路径
2. `man` 命令名:查看命令的详细帮助
   * `man pwd`:要看看 pwd 命令的详细用法
3. `ls 参数 目录名` : 列出文件 想看看跟目录下有什么，
    * `ls /`:想看看驱动目录下有什么;
    * `ls /System/Library/Extensions`
    * 参数 -w 显示中文，-l 详细信息， -a 包括隐藏文件
4.  `cd 转换目录`：想到驱动目录下溜达一圈
    * `cd /System/Library/Extensions`
5.  `mkdir 目录名` : 建立新目录
    *  `mkdir /System/Library/Extensions/backup`: 在驱动目录下建一个备份目录
    *  `mkdir /User/用户名/Desktop/backup`: 在桌面上建一个备份目录 backup
6. `cp 参数 源文件 目标文件` (copy): 拷贝文件
    * `cp -R /User/用户名/Desktop/Natit.kext /System/Library/Extensions`: 想把桌面的Natit.kext 拷贝到驱动目录中(参数R表示对目录进行递归操作，kext在图形界面下看起来是个文件，实际上是个文件夹。)
    * `cp -R /System/Library/Extensions/* /User/用户名/Desktop/backup`: 把驱动目录下的所有文件备份到桌面backup
7. `rm 参数 文件`: 删除文件
    * `rm -rf /System/Library/Extensions.kextcache` : 想删除驱动的缓存
    * `rm -rf /System/Library/Extensions.mkext`:
    * 参数－rf 表示递归和强制，千万要小心使用，如果执行了 rm -rf / 你的系统就全没了
8. `mv 现有文件路径 要移动至的路径`(move): 移动文件
    * `mv /System/Library/Extensions/AppleHDA.kext /User/用户名/Desktop`: 想把AppleHDA.Kext 移到桌面
    * `mv /System/Library/Extensions/AppleHDA.kext /System/Library/Extensions/backup` :想把AppleHDA.Kext 移到备份目录中
9. `chmod 参数 权限 文件路径`：更改文件权限
    * `chmod -R 755 /System/Library/Extensions`: 把驱动目录下所有文件设定到root读写，其他用户只读(参数R 表示递归，755表示各用户的权限)
10. `chown 参数 用户:组 文件`： 更改文件属主
    * `chown -R root:wheel /System/Library/Extensions`: 把驱动目录下的所有文件属主改成根用户(参数R 表示递归操作)
11. `diskutil repairpermissions /`:修复整个系统中文件的权限. 严格的说这不是一个unix 命令，而是osx一个软件，记得修改或添加的驱动就执行一次。
12. `nano 文件名`:文本编辑
    * `nano /System/Library/Extensions/Natit.kext/Info.plist`: 编辑natit Info.plist
    * 编辑完成后 用 Ctrl ＋O 存盘，Ctrl＋X 退出
    * 另一个文本编辑软件是 vi，操作有些古怪，熟了是非常好用的，而且在所有类Unix系统中都它，走遍天下都不怕了。
13. `sh 脚本文件名`:运行脚本命令
例 修改驱动后所有需要的操作存成一个脚本，以后修改了驱动后只要运行一次这个脚本就可以了，方便吧
    * 终端中运行nano /clean
    * 把下列代码粘贴到 nano 中
        * rm -rf /System/Library/Extensions.kextcache
        * rm -rf /System/Library/Extensions.mkext
        * chown -R root:wheel /System/Library/Extensions
        * chmod -R 755 /System/Library/Extensions
        * diskutil repairpermissions /
        * kextcache -k /System/Library/Extensions/
    * Ctrl ＋O 存盘，Ctrl＋X 退出
    * 以后只要动了驱动，就在终端中运行一次 sh /clean


## 小技巧

1. 用 `Tab` 键自动补齐命令
比如想到 /System 目录中去，输入 cd /Sy 然后按一下Tab 键，命令就会自动补齐成 cd /System

2. 操作带名字中带有空格的文件和目录
空格在命令中写成 空格， 比如要进入 My Documents，命令为 cd My Documents




# 1st
## dos命令行
图形化界面
命令

```
echo $SHELL: 查看系统使用何种shell命令. 输出为/bin/bash，则为Bourne shell命令，可以通过编辑profile配置环境变量。
dir (directory)
md xx (create xx)
rd xx (delete dir xx)
 del [all the doc in this dir]
 rd xx

d: (enter d:\)
cd xx\cc :(change directory改变路径 enter xx\cc)
cd.. (返回上一层文件夹back)
cd\ (back to the origin)
ls：列出（list）当前文件夹目录下的文件及文件夹。
exit (close the window)

echo abc>1.txt (made a txt with content abc)
del 1.txt (delete 1.txt)

del xx(dir)
 come to a question
xx\*  are you sure<y\n> (* means everything)
del *.txt (delete all txt)
```

### Bourne Shell
Bourne Shell 是交互式命令解释器和命令编程语言。
bsh 命令运行 Bourne Shell。

Bourne Shell 可以作为`登录 Shell 程序`或`登录 Shell 程序下的子 Shell ` 运行。只有 login 命令可以将 Bourne Shell 调用为登录 Shell 程序。它通过使用特殊格式的 bsh 命令名 -bsh 来实现。当调用时带有初始连字符（-），Shell 会首先读取并运行在系统 /etc/profile 文件和用户的 $HOME/.profile 文件中找到的命令（如果有）。/etc/profile 文件设置所有用户需要的变量。最后，Shell 准备好从标准输入读取命令。

如果启动 Bourne Shell 时指定了 File [Parameter] 参数，那么 Shell 会运行由 File 参数标识的脚本文件，包含所有指定的参数。所指定的脚本文件必须具有读许可权；所有 setuid 和 setgid 设置都会被忽略。 然后 Shell 读取命令。如果使用 -c 或 -s 标志，那么不要指定脚本。

## 计算机语言
c, c++, java

# 2nd

## java
language from sun (stanford university network)

* 与平台无关的编程语言 (c++ for windows by microsoft)
* 3 different construction
    * j2ee javaee (java 2 platform enterprise edition) serviet jsp
    * j2se javase (java 2 platform standard edition)
    * j2me javame (java 2 platform micro edition) for cellphone
* 特点: 跨平台性。the software made by java can be used in windows and linux.
    * java 虚拟机(解析器) (jvm java virtual machine)

1. java语言的环境搭建

* jre (java runtime environment): jvm and java程序所需的核心类库。
* jdk (java development kit) 开发工具包，包括jre。 编译工具javac.exe 打包工具jar.exe

* windows 下 记得配置环境变量，属性，高级，path，记得用`；`断开。
    * `%javahome%`c:\shoi\bin
    * path=`%javahome%`\bin;%systemroot%....
* 临时配置方式：set查看或者设置环境变量的值，只在当前dos内有效。
    * set path
    * set path=地址
    * set path=haha;%path% (haha+path)

2. java程序开发体验-hello world

```
class demo(any name)
<>
```
nosuchmethoderror: main (not a program that can be run)



# order

[toc]


## cd xx\cc :(change directory改变路径 enter xx\cc)
## cd.. (返回上一层文件夹back)
## cd\ (back to the origin)

## d: (enter d:\)
## del 1.txt (delete 1.txt)
## del [all the doc in this dir]
 rd xx
## del xx(dir)
```
 come to a question
xx\*  are you sure<y\n> (* means everything)
del *.txt (delete all txt)
```

## dir (directory)

## echo $SHELL:
查看系统使用何种shell命令. 输出为/bin/bash，则为Bourne shell命令，可以通过编辑profile配置环境变量。
## echo abc>1.txt
(made a txt with content abc)
## exit (close the window)
## ls：列出（list）
当前文件夹目录下的文件及文件夹。


## md xx (create xx)
## rd xx (delete dir xx)

## which
安装目录，如果不知道安装到了哪里，使用which命令查看，例如 “which java ”
