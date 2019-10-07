# loop

## Python 循环语句

循环类型 |	描述
--- | ---
`while` 循环	| 在给定的判断条件为 true 时执行循环体，否则退出循环体。
`for` 循环	| 重复执行语句

## 循环控制语句

控制语句 |	描述
--- | ---
`break` 语句	| 在语句块执行过程中终止循环，并且跳出整个循环
`continue` 语句	| 在语句块执行过程中终止当前循环，跳出该次循环，执行下一次循环。
`pass` 语句	| pass是空语句，是为了保持程序结构的完整性。

## `while`

执行语句可以是单个语句或语句块。
判断条件可以是任何表达式:
* 任何 非零、或非空（null）的值 均为 *true* 。
* 当判断条件假 *false* 时，循环结束。


#!/usr/bin/python

```
count = 0
while (count < 9):
   print 'The count is:', count
   count = count + 1

print "Good bye!"
```
以上代码执行输出结果:

> The count is: 0
> The count is: 1
> T> he count is: 2
> The count is: 3
> The count is: 4
> The count is: 5
> The count is: 6
> The count is: 7
> The count is: 8
> Good bye!
