# C++


[toc]


## C++
一种中级语言，它是由 Bjarne Stroustrup 于 1979 年在贝尔实验室开始设计开发的。C++ 进一步扩充和完善了 C 语言，是一种面向对象的程序设计语言。C++ 可运行于多种平台上，如 Windows、MAC 操作系统以及 UNIX 的各种版本。

### 面向对象程序设计
C++ 完全支持面向对象的程序设计，包括面向对象开发的四大特性：
- 封装
- 抽象
- 继承
- 多态

### 标准库
标准的 C++ 由三个重要部分组成：
- `核心语言` : 提供了所有构件块，包括变量、数据类型和常量，等等。
- `C++ 标准库` : 提供了大量的函数，用于操作文件、字符串等。
- `标准模板库（STL）`: 提供了大量的方法，用于操作数据结构等。

---

## C++ 环境设置
### 本地环境设置
如果您想要设置 C++ 语言环境，您需要确保电脑上有以下两款可用的软件，文本编辑器和 C++ 编译器。

### 文本编辑器
- 用于输入您的程序。
- 文本编辑器包括 Windows Notepad、OS Edit command、Brief、Epsilon、EMACS 和 vim/vi。
  - Notepad 通常用于 Windows 操作系统上
  - vim/vi 可用于 Windows 和 Linux/UNIX 操作系统上。

通过编辑器创建的文件通常称为源文件，源文件包含程序源代码。C++ 程序的源文件通常使用扩展名 `.cpp`、`.cp` 或 `.c`。


### C++ 编译器
写在源文件中的源代码是人类可读的源。它需要"编译"，转为机器语言，这样 CPU 可以按给定指令执行程序。

C++ 编译器用于把源代码编译成最终的可执行程序。

大多数的 C++ 编译器并不在乎源文件的扩展名，但是如果您未指定扩展名，则默认使用 `.cpp`。

最常用的免费可用的编译器是 GNU 的 C/C++ 编译器，如果您使用的是 HP 或 Solaris，则可以使用各自操作系统上的编译器。

安装 GNU 的 C/C++ 编译器。
- 这里同时提到 C/C++，主要是因为 GNU 的 gcc 编译器适合于 C 和 C++ 编程语言。

#### 安装 GNU 的 C/C++ 编译器
UNIX/Linux 上的安装
- 检查您的系统上是否安装了 GCC: `$ g++ -v`
- 如果您的计算机上已经安装了 GNU 编译器，则会显示如下消息：
```
Using built-in specs.
Target: i386-redhat-linux
Configured with: ../configure --prefix=/usr .......
Thread model: posix
gcc version 4.1.2 20080704 (Red Hat 4.1.2-46)
```

使用 Visual Studio (Graphical Interface) 编译
1. 选择 C++ File 然后设置文件名为 main.cpp，然后点击 Add：
2. 拷贝以下代码到 main.cpp 中：
```
#include <iostream>
int main()
{
    std::cout << "Hello World!\n";
    return 0;
}
```
3. 点击菜单上的 Debug -> Start Without Debugging (或按下 ctrl + F5) :
4. 完成以上操作后，你可以看到以下输出

#### g++ 应用说明
程序 g++ 是将 gcc 默认语言设为 C++ 的一个特殊的版本，链接时它自动使用 C++ 标准库而不用 C 标准库。

```py
- 通过遵循源码的命名规范并指定对应库的名字，用 gcc 来编译链接 C++ 程序是可行的，如下例所示：
$ gcc main.cpp -lstdc++ -o main

- 一个保存在文件 helloworld.cpp 中的 C++ 程序的代码：
#include <iostream>
using namespace std;
int main()
{
    cout << "Hello, world!" << endl;
    return 0;
}

# .cpp文件放在桌面
$ cd Desktop
# 最简单的编译方式
$ g++ helloworld.cpp
- 未指定exe文件名，默认 a.out。

# 运行程序
$ ./a.out
Hello, world!

- 使用 -o 选项指定可执行程序的文件名
$ g++ helloworld.cpp -o helloworld
$ ./helloworld
Hello, world!


- 多个 C++ 代码文件，runoob1.cpp、runoob2.cpp 生成一个 runoob 可执行文件。
$ g++ runoob1.cpp runoob2.cpp -o runoob

- g++ 有些系统默认是使用 C++98，我们可以指定使用 C++11 来编译 main.cpp 文件：
$ g++ -g -Wall -std=c++11 main.cpp
```

g++ 常用命令选项
选项	            | 解释
---|---
-ansi	           | 只支持 ANSI 标准的 C 语法。这一选项将禁止 GNU C 的某些特色， 例如 asm 或 typeof 关键词。
-c	             | 只编译并生成目标文件。
-DMACRO	         | 以字符串"1"定义 MACRO 宏。
-DMACRO=DEFN	   | 以字符串"DEFN"定义 MACRO 宏。
-E	             | 只运行 C 预编译器。
-g	             | 生成调试信息。GNU 调试器可利用该信息。
-IDIRECTORY	     | 指定额外的头文件搜索路径DIRECTORY。
-LDIRECTORY	     | 指定额外的函数库搜索路径DIRECTORY。
-lLIBRARY	       | 连接时搜索指定的函数库LIBRARY。
-m486	           | 针对 486 进行代码优化。
-o	             | FILE 生成指定的输出文件。用在生成可执行文件时。
-O0	             | 不进行优化处理。
-O	             | 或 -O1 优化生成代码。
-O2	             | 进一步优化。
-O3	             | 比 -O2 更进一步优化，包括 inline 函数。
-shared	         | 生成共享目标文件。通常用在建立共享库时。
-static	         | 禁止使用共享连接。
-UMACRO	         | 取消对 MACRO 宏的定义。
-w	             | 不生成任何警告信息。
-Wall	           | 生成所有警告信息。

#### mac cpp
1. xcode > new project
2. macos > command line tool
3. choose options for your project:
  - Product Name
  - Language选择C++
4. main.cpp

---

## C++ 基本语法
C++ 程序可以定义为对象的集合，这些对象通过调用彼此的方法进行交互。
- `对象` - 对象具有状态和行为。例如：一只狗的状态 - 颜色、名称、品种，行为 - 摇动、叫唤、吃。对象是类的实例。
- `类` - 类可以定义为描述对象行为/状态的模板/蓝图。
- `方法` - 从基本上说，一个方法表示一种行为。一个类可以包含多个方法。可以在方法中写入逻辑、操作数据以及执行所有的动作。
- `即时变量` - 每个对象都有其独特的即时变量。对象的状态是由这些即时变量的值创建的。

### C++ 程序结构

```cpp
#include <iostream>
using namespace std;
// main() 是程序开始执行的地方
int main()
{
   cout << "Hello World";    // 输出 Hello World
   return 0;
}
```

C++ 语言定义了一些`头文件`，这些头文件包含了程序中必需的或有用的信息。
上面这段程序中，包含了头文件 `<iostream>`。
- `using namespace std`; 告诉编译器使用 `std` `命名空间`。
- `// main()` 是程序开始执行的地方 是一个单行注释。单行注释以 // 开头，在行末结束。
- `int main()` 是主函数，程序从这里开始执行。
- `cout << "Hello World";` 会在屏幕上显示消息 "Hello World"。
- `return 0`; 终止 `main( )函数`，并向调用进程返回值 0。


### 编译 & 执行 C++ 程序
把源代码保存在一个文件中，编译并运行步骤：

- 打开一个文本编辑器，添加代码。
- 保存文件为 hello.cpp。
- `$ cd` 进入到保存文件所在的目录。
- 键入 `$ g++ hello.cpp` ，输入回车，编译代码。
  - 如果代码中没有错误，命令提示符会跳到下一行，并生成 `a.out` 可执行文件。
- 现在，键入 `$ a.out` 来运行程序。
- 您可以看到屏幕上显示 `Hello World`。


### C++ 中的分号 & 语句块
`分号`是语句结束符。每个语句必须以分号结束。表明一个逻辑实体的结束。

    下面是三个不同的语句：
    x = y;
    y = y+1;
    add(x, y);

`语句块`是一组使用大括号括起来的按逻辑连接的语句。

    {
       cout << "Hello World"; // 输出 Hello World
       return 0;
    }

C++ 不以行末作为结束符的标识，因此可以在一行上放置多个语句。例如：

    x = y;
    y = y+1;
    add(x, y);

等同于

    x = y; y = y+1; add(x, y);


### C++ 标识符
`标识符`是用来标识变量、函数、类、模块，或任何其他`用户自定义项目`的名称。
- 一个标识符以字母 A-Z 或 a-z 或下划线 _ 开始，后跟零个或多个字母、下划线和数字（0-9）。
- 标识符内不允许出现标点字符，比如 @、& 和 %。C++ 是区分大小写的编程语言。
- 在 C++ 中，`Manpower` 和 `manpower` 是两个不同的标识符。


### C++ 关键字
C++ 中的保留字不能作为常量名、变量名或其他标识符名称。

asm	else	new	this
auto	enum	operator	throw
bool	explicit	private	true
break	export	protected	try
case	extern	public	typedef
catch	false	register	typeid
char	float	reinterpret_cast	typename
class	for	return	union
const	friend	short	unsigned
const_cast	goto	signed	using
continue	if	sizeof	virtual
default	inline	static	void
delete	int	static_cast	volatile
do	long	struct	wchar_t
double	mutable	switch	while
dynamic_cast	namespace	template	 


### 三字符组
- 三字符组就是用于表示另一个字符的三个字符序列，又称为三字符序列。
- 三字符序列总是以两个问号开头。
- 三字符序列不太常见，但 C++ 标准允许把某些字符指定为三字符序列。以前为了表示键盘上没有的字符，这是必不可少的一种方法。
- 三字符序列可以出现在任何地方，包括字符串、字符序列、注释和预处理指令。

下面列出了最常用的三字符序列：

三字符组  | 替换
---|---
??=	    |   #
??/	    |   \
??'	    |   ^
??(	    |   [
??)	    |   ]
??!	    |   |
??<	    |   {
??>	    |   }
??-	    |   ~

如果希望在源程序中有两个连续的问号，且不希望被预处理器替换，这种情况出现在字符常量、字符串字面值或者是程序注释中，
- 可选办法是用字符串的自动连接：`"...?""?..."`或者转义序列：`"...?\?..."`。

- 从Microsoft Visual C++ 2010版开始，该编译器默认不再自动替换三字符组。如果需要使用三字符组替换（如为了兼容古老的软件代码），需要设置编译器命令行选项/Zc:trigraphs
- g++仍默认支持三字符组，但会给出编译警告。


### C++ 中的空格
只包含空格的行，被称为`空白行`，可能带有注释，C++ 编译器会完全忽略它。
- 在 C++ 中，空格用于描述空白符、制表符、换行符和注释。空格分隔语句的各个部分，让编译器能识别语句中的某个元素（比如 int）在哪里结束，下一个元素在哪里开始。因此，在下面的语句中：

`int age;`

在这里，int 和 age 之间必须至少有一个空格字符（一个空白符），这样编译器才能够区分它们。

`fruit = apples + oranges;   // 获取水果的总数`

- fruit 和 =，或者 = 和 apples 之间的空格字符不是必需的，但是为了增强可读性，您可以根据需要适当增加一些空格。

---

## C++ 变量类型
变量其实只不过是程序可操作的存储区的名称。C++ 中每个变量都有指定的类型，类型决定了变量存储的大小和布局，该范围内的值都可以存储在内存中，运算符可应用于变量上。

变量的名称可以由字母、数字和下划线字符组成。它必须以字母或下划线开头。大写字母和小写字母是不同的，因为 C++ 是大小写敏感的。

类型	| 描述
---|---
bool	| 存储值 true 或 false。
char	| 通常是一个字符（八位）。这是一个整数类型。
int  	| 对机器而言，整数的最自然的大小。
float | 单精度浮点值。单精度是这样的格式，1位符号，8位指数，23位小数。
---| ![v2-48240f0e1e0dd33ec89100cbe2d30707_hd](https://i.imgur.com/WwRQSfN.png)
double | 双精度浮点值。双精度是1位符号，11位指数，52位小数。
---| ![v2-749cc641eb4d5dafd085e8c23f8826aa_hd](https://i.imgur.com/qggp281.png)
void	| 表示类型的缺失。
wchar_t	| 宽字符类型。


### C++ 中的变量定义
`变量定义`就是告诉编译器在何处创建变量的存储，以及如何创建变量的存储。
`变量定义`指定一个数据类型，并包含了该类型的一个或多个变量的列表，如下所示：

`type variable_list;`

在这里，type 必须是一个有效的 C++ 数据类型，可以是 char、wchar_t、int、float、double、bool 或任何用户自定义的对象，variable_list 可以由一个或多个标识符名称组成，多个标识符之间用逗号分隔。下面列出几个有效的声明：

```cpp
int    i, j, k;
char   c, ch;
float  f, salary;
double d;
```

行 int i, j, k; 声明并定义了变量 i、j 和 k，这指示编译器创建类型为 int 的名为 i、j、k 的变量。

变量可以在声明的时候被`初始化`（指定一个初始值）。初始化器由 `= 后跟一个常量` 表达式组成:

`type variable_name = value;`

```cpp
//实例：
extern int d = 3, f = 5;    // d 和 f 的声明
int d = 3, f = 5;           // 定义并初始化 d 和 f
byte z = 22;                // 定义并初始化 z
char x = 'x';               // 变量 x 的值为 'x'
```

不带初始化的定义：带有静态存储持续时间的变量会被隐式初始化为 NULL（所有字节的值都是 0），其他所有变量的初始值是未定义的。

### C++ 中的变量声明
变量声明向编译器保证变量以给定的类型和名称存在，这样编译器在不需要知道变量完整细节的情况下也能继续进一步的编译。变量声明只在编译时有它的意义，在程序连接时编译器需要实际的变量声明。

当您使用多个文件且只在其中一个文件中定义变量时（定义变量的文件在程序连接时是可用的），变量声明就显得非常有用。

可以使用 `extern` 关键字在 任何地方 声明一个变量。

虽然可以在 C++ 程序中多次`声明`一个变量，但变量只能在某个文件、函数或代码块中被`定义`一次。

其中变量在头部就已经`被声明`，但它们是在主函数内`被定义`和`初始化`的：

```cpp
实例:
#include <iostream>
using namespace std;

// 变量 声明
extern int a, b;
extern int c;
extern float f;

int main ()
{
  // 变量定义
  int a, b;
  int c;
  float f;

  // 实际初始化
  a = 10;
  b = 20;
  c = a + b;

  cout << c << endl ;

  f = 70.0/3.0;
  cout << f << endl ;

  return 0;
}

// 结果：
30
23.3333
```

同样的，在`函数声明`时，提供一个函数名，而函数的实际定义则可以在任何地方进行。

```cpp
// 函数声明
int func();

int main()
{
    // 函数调用
    int i = func();
}

// 函数定义
int func()
{
    return 0;
}
```

### C++ 中的左值（Lvalues）和右值（Rvalues）

C++ 中有两种类型的表达式：
- 左值（lvalue）：指向内存位置的表达式被称为左值（lvalue）表达式。左值可以出现在赋值号的左边或右边。
- 右值（rvalue）：术语右值（rvalue）指的是存储在内存中某些地址的数值。右值是不能对其进行赋值的表达式，也就是说，右值可以出现在赋值号的右边，但不能出现在赋值号的左边。

`变量`是左值，因此出现在赋值号的左边。
`数值型`的字面值是右值，因此不能被赋值，不能出现在赋值号的左边。

```cpp
// 有效的语句：
int g = 20;

// 不是一个有效的语句，会生成编译时错误：
10 = 20;
```

---

## C++ 变量作用域
`作用域`是程序的一个区域，一般来说有三个地方可以定义变量：
- 在 函数或一个代码块 内部声明的变量，称为`局部变量`。
- 在 函数参数的 定义中声明的变量，称为`形式参数`。
- 在 所有函数 外部声明的变量，称为`全局变量`。

### 局部变量
在函数或一个代码块 *内部声明* 的变量，称为局部变量。
- 它们只能被函数内部或者代码块内部的语句使用。

```CPP
// 实例
#include <iostream>
using namespace std;

int main ()
{
  // 局部变量声明
  int a, b;
  int c;

  // 实际初始化
  a = 10;
  b = 20;
  c = a + b;

  cout << c;
  return 0;
}
```

### 全局变量
在所有函数外部定义的变量（通常是在程序的头部），称为 *全局变量*。
- 全局变量的值在程序的整个生命周期内都是有效的。
- 全局变量可以被任何函数访问。也就是说，全局变量一旦声明，在整个程序中都是可用的。

```cpp
// 实例使用了全局变量和局部变量：
#include <iostream>
using namespace std;

// 全局变量声明
int g;

int main ()
{
  // 局部变量声明
  int a, b;

  // 实际初始化
  a = 10;
  b = 20;
  g = a + b;

  cout << g;
  return 0;
}
```

在程序中，`局部变量`和`全局变量`的名称可以相同
但在函数内，`局部变量`的值会覆盖`全局变量`的值。

```cpp
实例
#include <iostream>
using namespace std;

// 全局变量声明
int g = 20;

int main ()
{
  // 局部变量声明
  int g = 10;
  cout << g;
  return 0;
}

//结果：
10
```

### 初始化局部变量和全局变量
当`局部变量`被定义时，系统不会对其初始化，必须自行对其初始化。
定义`全局变量`时，系统会自动初始化为下列值：

数据类型	 | 初始化默认值
---|---
int	      | 0
char	    | '\0'
float	    | 0
double	  | 0
pointer	  | NULL



# if

```cpp
if (x>y) cout << x;
  else if (x=y) cout << 0;
    else if (x<y) cout <<y;

cout << "enter x and y"
cin >> x >> y;
if (x != y)
{
  if ....
}
else
  cout << "x=y" < endl;
```

# `switch` 语句
- 一个 switch 语句允许测试一个变量等于多个值时的情况。每个值称为一个 case，且被测试的变量会对每个 switch case 进行检查。

```CPP
switch(expression)
{
    case constant-expression  :
       statement(s);
       break; // 可选的
    case constant-expression  :
       statement(s);
       break; // 可选的

    // 您可以有任意数量的 case 语句
    default : // 可选的
       statement(s);
}


case 0:
case 1: cout << "1" << endl; break;
defautl:
  cout << "nothing" << endl; break;
}

return 0;

```




# 函数
- 函数是一组一起执行一个任务的语句。每个 C++ 程序都至少有一个函数，即主函数 `main()` ，所有简单的程序都可以定义其他额外的函数。
- 可以把代码划分到不同的函数中。在逻辑上，划分通常是根据每个函数执行一个特定的任务来进行的。
- 函数声明告诉编译器函数的名称、返回类型和参数。函数定义提供了函数的实际主体。
- C++ 标准库提供了大量的程序可以调用的`内置函数`。
  - 例如，
  - 函数 `strcat()` 用来连接两个字符串，
  - 函数 `memcpy()` 用来复制内存到另一个位置。
- 函数还有很多叫法，比如方法、子例程或程序，等等。

## 定义函数

```cpp
return_type function_name( parameter list )
{
   body of the function
}
```

函数由一个`函数头`和一个`函数主体`组成。
`return_type`：一个函数可以返回一个值。return_type 是函数返回的值的数据类型。有些函数执行所需的操作而不返回值，在这种情况下，return_type 是关键字 `void`.
`function_name`：这是函数的实际名称。函数名和参数列表一起构成了函数签名。
`parameter`：参数就像是占位符。当函数被调用时，您向参数传递一个值，这个值被称为实际参数。
`parameter list`: 包括函数参数的`类型、顺序、数量`。参数是可选的，也就是说，函数可能不包含参数。
`body of the function`：函数主体包含一组定义函数执行任务的语句。

```cpp
// 实例
// max() 函数的源代码。
// 两个参数 num1 和 num2，返回这两个数中较大的那个数：
int max(int num1, int num2)
{
   // 局部变量声明
   int result;

   if (num1 > num2)
      result = num1;
   else
      result = num2;

   return result;
}
```

## 函数声明
`函数声明`会告诉编译器函数名称及如何调用函数。函数的实际主体可以单独定义。
- 函数声明: `return_type function_name( parameter list );`

针对上面定义的函数 `max()`，函数声明：`int max(int num1, int num2);`

- 在函数声明中，参数的`名称`并不重要，只有参数的`类型`是必需的
- 因此下面也是有效的声明：`int max(int, int);`

当您在一个源文件中定义函数且在另一个文件中调用函数时，函数声明是必需的。在这种情况下，您应该在调用函数的文件顶部声明函数。


## 调用函数
- 创建 C++ 函数时，会定义函数做什么，然后通过调用函数来完成已定义的任务。
- 当程序调用函数时，程序控制权会转移给被调用的函数。被调用的函数执行已定义的任务，当函数的返回语句被执行时，或到达函数的结束括号时，会把程序控制权交还给主程序。
- 调用函数时，传递所需参数，如果函数返回一个值，则可以存储返回值。例如：

```cpp
// 实例
#include <iostream>
using namespace std;

// 函数声明
int max(int num1, int num2);

int main ()
{
   // 局部变量声明
   int a = 100;
   int b = 200;
   int ret;

   // 调用函数来获取最大值
   ret = max(a, b);

   cout << "Max value is : " << ret << endl;

   return 0;
}

// 函数返回两个数中较大的那个数
int max(int num1, int num2)
{
   // 局部变量声明
   int result;

   if (num1 > num2)
      result = num1;
   else
      result = num2;

   return result;
}

把 max() 函数和 main() 函数放一块，编译源代码。当运行最后的可执行文件时，会产生下列结果：

Max value is : 200
```

# 循环

## `while` 循环
- 只要给定的条件为真，while 循环语句会重复执行一个目标语句。
- `condition` 可以是任意的表达式，当为任意非零值时都为真。it need to be fault in the end.
  - 当条件为真时,执行循环。
  - 当条件为假时,程序流将继续执行紧接着循环的下一条语句。
- `statement(s)` 可以是一个单独的语句，也可以是几个语句组成的代码块。

```cpp
while(condition)
{
   statement(s);
}
```

```cpp
// 实例
#include <iostream>
using namespace std;

int main ()
{
   // 局部变量声明
   int a = 10;

   while(a<20)
   {
       cout << "a 的值：" << a << endl;
       a++;
   }
   return 0;
}

//结果：
a 的值： 10
a 的值： 11
a 的值： 12
a 的值： 13
a 的值： 14
a 的值： 15
a 的值： 16
a 的值： 17
a 的值： 18
a 的值： 19
```

---

## `for` 循环
`for` 循环允许您编写一个执行特定次数的循环的重复控制结构。

```cpp
for ( init; condition; increment )
{
   statement(s);
}
```

for 循环的控制流：
- `init` 会首先被执行，且只会执行一次。这一步声明并初始化任何循环控制变量。也可以不在这里写任何语句，只要有一个分号出现即可。
- 接下来，会判断 `condition`。
  - 如果为真，则执行循环主体。
  - 如果为假，则不执行循环主体，且控制流会跳转到紧接着 for 循环的下一条语句。
- 在执行完 for 循环主体后，控制流会跳回上面的 `increment` 语句。该语句允许您更新循环控制变量。该语句可以留空，只要在条件后有一个分号出现即可。
- 条件再次被判断。如果为真，则执行循环，这个过程会不断重复（循环主体，然后增加步值，再然后重新判断条件）。在条件变为假时，for 循环终止。

```cpp
// 实例
#include <iostream>
using namespace std;

int main ()
{
   for( int a = 10; a < 20; a = a + 1 )
   {
       cout << "a 的值：" << a << endl;
   }
   return 0;
}
// 结果：
a 的值： 10
a 的值： 11
a 的值： 12
a 的值： 13
a 的值： 14
a 的值： 15
a 的值： 16
a 的值： 17
a 的值： 18
a 的值： 19
```

---

## 嵌套循环
- 一个循环内可以嵌套另一个循环。
- C++ 允许至少 256 个嵌套层次。


```CPP
// 嵌套 for 循环
for ( init; condition; increment )
{
   for ( init; condition; increment )
   {
      statement(s);
   }
   statement(s); // 可以放置更多的语句
}


// 嵌套 while 循环
while(condition)
{
   while(condition)
   {
      statement(s);
   }
   statement(s); // 可以放置更多的语句
}


// do...while 循环 语句的语法：
do
{
   statement(s); // 可以放置更多的语句
   do
   {
      statement(s);
   }while( condition );

}while( condition );
```


关于嵌套循环有一点值得注意，您可以在任何类型的循环内嵌套其他任何类型的循环。比如，一个 for 循环可以嵌套在一个 while 循环内，反之亦然。


```CPP
// 实例
// 嵌套的 for 循环来查找 2 到 100 中的质数：
#include <iostream>
using namespace std;

int main ()
{
    int i, j;
    for(i=2; i<100; i++) {
        for(j=2; j <= (i/j); j++) {
            if(!(i%j)) {
                break; // 如果找到，则不是质数
            }
        }
        if(j > (i/j)) {
            cout << i << " 是质数\n";
        }
    }
    return 0;
}
// 结果：

2 是质数
3 是质数
5 是质数
7 是质数
11 是质数
13 是质数
17 是质数
19 是质数
23 是质数
29 是质数
31 是质数
37 是质数
41 是质数
43 是质数
47 是质数
53 是质数
59 是质数
61 是质数
67 是质数
71 是质数
73 是质数
79 是质数
83 是质数
89 是质数
97 是质数
```






















.
