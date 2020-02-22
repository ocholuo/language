# SQL

[TOC]

# basic SQL


## SQL

SQL statements usually are divided into two categories:

* Data Definition Language (DDL)
    * define relation/table structures including the schema for each relation, the domain of values associated with each attribute, and integrity constraints.
    * Example, CREATE DATABASE, ALTER DATABASE, DROP DATABASE, CREATE TABLE, ALTER TABLE, DROP TABLE, TRUNCATE TABLE, and so on.
    * DDL statements do `COMMIT` automatically
* Data Manipulation Language (DML)
    * used to *retrieve, insert, update, and delete* data in database.
    * Example, SELECT, INSERT, UPDATE, DELETE, and MERGE
    * DML may not do a COMMIT automatically in some RDBMS, like Oracle.
    * have to explicitly issue the `COMMIT` statement



## database security
http://www.aseatw.com/html/Present.aspx?id=DatabaseFundamentals&num=26

the first line of database for a database:

* **change the default user password** immediately
* **lock** unused user account.
* **enforce** stronger passwords.
* **remove** public accounts, or all access from all accounts.
* **choose** *domain authentication* or *database authentication* for your database users, and stick with it.
* **Examine** roles and groups closely.
* **Protect** administrative functions from users.
* **divide** database admin duties.



# mysql
table - database - server

## for database

### choose database:

mysql> `show databases`;
+--------------------+
| Database           |
+--------------------+
| information_schema |
| first              |
| mysql              |
| performance_schema |
| sys                |
+--------------------+
5 rows in set (0.02 sec)

mysql> `use` test1;
database changed

mysql> `show tables`;
+-----------------+
| Tables_in_first |
+-----------------+
| parrots         |
| student         |
+-----------------+
2 rows in set (0.00 sec)

### create database
mysql> `create database` test0 `charset utf8`;
Query OK, 1 row affected (0.01 sec)

mysql> `show tables`;
Empty set (0.00 sec)

### drop database
mysql> `drop database` test0;
Query OK, 0 rows affected (0.03 sec)

### change name?
mysql数据库不能改名
只能呢该表，列名字

## for table
### create table
建表其实就是声明表头列的过程

mysql> `create table` stu(
id `int`,
name `varchar(10)`)
`engine myisam charset utf8`;
Query OK, 0 rows affected (0.04 sec)

mysql> `show tables`;
+-----------------+
| Tables_in_test1 |
+-----------------+
| stu             |
+-----------------+
1 row in set (0.00 sec)

#### 三大列类型

* 数值类型
    * 整型: 字节越多，存的范围越大。
        * int默认是有符号的。
            * *unsigned*：无符号, 无正负[0,255]
            * *M*: 必须跟zerofill才有意义，单独使用无意义。表示补0的宽度
            * *zerofill*: 补0,默认unsigned。
        * **tinyint**：1字节，正负[-128,+127] 或 无正负[0,255]
            * 1 byte = 8 bits, 00000000 - 111111111
            * 计算机为了表示正负数，最高位最左侧的0/1当成符号。
            * 用补码规则
            * 0 0000000 = 0
            * 0 1111111 = 127
            * 1 0000000 = -1
            * 1 1111111 = 128
            * [-2^7,2^7-1]
        * **smallint**：2字节,16bits，3万
            * [-2^15,2^15-1]
        * **mediumint**：3字节，800+万
        * **int**：4字节，40+亿
        * **bigint**：8字节
            * XX `int` not null default 0;
            * XX `int` `(5)` `zerofill` not null default 0;

    * 浮点数:
        * **float (M,D)**:
        * **decimal (M,D)**:定点
        * M:精度，总位数, D: 标度，小数点后面
        * 正负的9999.99
            * `XX decimal(6,2)` 总共6位数，小数点后1位，正负都可以。
        * float 能存10^38，10^-38.
        * M<=24 4bytes, xor 8 bytes
        * 定点是把整数部分和小数部分分开存的，比float精确。float取出时有可能不一样！！像账户银行敏感的，建议用decimal。

mysql> `insert into` account values
    -> (1, 1234567.23,1234567.23);
Query OK, 1 row affected (0.00 sec)

mysql> `select` * `from` account;
+----+------------+------------+
| id | acc1       | acc2       |
+----+------------+------------+
|  1 | 1234567.25 | 1234567.23 |
+----+------------+------------+
2 rows in set (0.00 sec)

* 字符串型
    * *M 限制的是字符数不是字节，6个utf8或其他任何都是6个*。
    * **char(M)** 定长字符串 M,[0,255]
        * 存储定长，容易计算文件指针的移动量，*速度更快*
        * 不论够不够长，实际都占据N个长度
        * char(N),如果不够N个长度，用空格在末尾补齐长度
        * 取出时再把右侧空格去掉（*字符串本身右侧有空格将会丢失*）
        * 宽度M，可存字符M，实存字符i(i<=M),
        * 实占空间：M
        * 定长的利用率：M<=可能达到100%
        * 会有浪费
    * **varchar(M)** 变长字符串 M,[0,65535]
        * 不用空格补气，但是数据前面有1或2个字节来记录开头
        * 实占空间：i+(1或2个字节)
        * 变长的利用率：i+(1或2个字节)<100%, 不可能100%
        * 和text差不多，但是比他慢一点
    * **text**：
        * 不用加默认值，存较大的文本段，搜索速度慢。
        * 一万以内可以用varchar
    * **mediumblob**
    * **mediumtext**：一千多万
    * **longblob**
    * **longtext**
    * **blob**:
        * 是二进制类型，用来储存图像音频等二进制信息，0-255都有可能出现。
        * 意义在于防止因为字符集的问题，导致信息丢失
        * 比如一张图片中有0xFF字节，这个在ascii字符集中人文非法，在入库是被过滤了。如果是二进制，就是原原本本存进去，拿出来，隐形防范字符集的问题导致数据流失

```
//char varchar 区别
mysql> create table test(
    -> char(6) not null default'',
    -> varchar(6) not null default'')
    -> engine myisam charset utf8;

mysql> insert into test2 values ('aa ','aa ');
mysql> select concat(ca,'!'),concat(vca,'!') from test2;
+----------------+-----------------+
| concat(ca,'!') | concat(vca,'!') |
+----------------+-----------------+
| hello!         | hello!          |
| aa!            | aa !            |
+----------------+-----------------+
2 rows in set (0.01 sec)
```

```
//text 不需要默认值
mysql> create table test3(
    -> artice **text** not null default''
    -> )engine myisam charset utf8;
ERROR 1101 (42000): BLOB, TEXT, GEOMETRY or JSON column 'artice' can't have a default value

mysql> create table test3(
    -> artice text);
Query OK, 0 rows affected (0.05 sec)

mysql> alter table test3 add img blob;
Query OK, 0 rows affected (0.04 sec)
Records: 0  Duplicates: 0  Warnings: 0
```

```
//blob
mysql> desc test3;
+--------+------+------+-----+---------+-------+
| Field  | Type | Null | Key | Default | Extra |
+--------+------+------+-----+---------+-------+
| artice | text | YES  |     | NULL    |       |
| img    | blob | YES  |     | NULL    |       |
+--------+------+------+-----+---------+-------+

mysql> insert into test3
    -> values('qingqiongmaima','zhangfeiganlu');

mysql> select * from test3;
+----------------+---------------+
| artice         | img           |
+----------------+---------------+
| qingqiongmaima | zhangfeiganlu |
+----------------+---------------+

```

* 时间类型
    * 比起用char来使用各省时间空间。
    * **date**：3个字节
        * 1934-04-12
        * 范围：1000-01-01到9999-12-31
    * **datetime**:  8个字节
        * YYYY-mm-dd HH:ii:ss
    * **time**: 3个字节
        * 20:20:20
    * **timestamp**：4个字节
        * 可以取当前的时间
    * **year**: 1个字节
        * [0000, 1901,2155]
        * 可以简化成两位数 year(2)

```
mysql> create table test4(
    -> sname varchar(20) not null default'',
    -> logintime datetime not null,
    -> ts timestamp default current_timestamp
    -> )engine myisam charset utf8;
```

`primery key`
`auto_increment`
`not null`
`default '' `
`engine myisam/innodb/bdb charset utf8/gbk/latin1...`

```
create table test5(
id int unsigned primary key not null default,
username char(10) not null default 'admimn',
gender char(1) not null,
weight tinyint unsigned not null,
birth date not null,
salary decimal(8,2) unsigned not null,
lastlogin datetime not null,
intro char(1500)not null

//除username和intro之外都是定长
//都是定长的话 搜索会快很多
//*优化：就是空间换时间*
//username varchar(10) 可以有优化 char(10)
//intro varchar(1500) 变 char(1500)就浪费太多了
//*优化：把常用到的信息，优先考虑效率，把不常用比较占空间的信息，放到附表*
//把intro单独拿出来，改变次数也很少

create table intro(
id int unsigned primary key not null default,
username char(10) not null default 'admimn',
lastlogin datetime not null,
intro char(1500)not null

create table member(
id int unsigned auto_increment primary key,
username char(20) not null default '',
gender char(1) not null default '',
weight tinyint unsigned not null default 0,
birth date not null,
salary decimal(8,2) not null default 0.00,
lastlogin int unsigned not null default 0)
engine myisam charset utf8;
```

### 删除表 `drop table table_A`
mysql> `drop table` stu;
//表就不在了

### 改名 `rename table table_A to table_B`
mysql> `rename table` stu `to` newstu;


### 修改表
#### 添加列 `alter table table_A add Z (after/first) X`
//加在最后
mysql> `alter table` class1 `add` score2 tinyint unsigned not null default 0;

//加在指定位置
mysql> `alter table` class1 `add` score1 tinyint unsigned not null default 0 `after` id;

//加在第一位
mysql> `alter table` class1 `add` score1 tinyint unsigned not null default 0 `first`;

#### 删除列 `alter table table_A drop X`
mysql> `alter table` class1 `drop` score2；

#### 修改列参数 `alter table table_A modify X .../ Change X TO Y...) `
//不能改列名
mysql> `alter table` class1 `modify` score2 int unsigned not null default 100;

//可以修改列名
mysql> `alter table` class1 `change` score2 `to` score234 int unsigned not null default 100;

//如果列类型改变了，导致数据保存不下来
//一般会往大了该
//1. 丢数据
//2. 严格模式下，不能改


### 查找 `desc table_A`
mysql> `desc` table_name;
+---------+--------------+------+-----+---------+----------------+
| Field   | Type         | Null | Key | Default | Extra          |
+---------+--------------+------+-----+---------+----------------+
| id      | int(11)      | NO   | PRI | NULL    | auto_increment |
| sname   | varchar(10)  | NO   |     |         |                |
| gender  | varchar(1)   | NO   |     |         |                |
| company | varchar(20)  | NO   |     |         |                |
| salary  | decimal(6,2) | NO   |     | 0.00    |                |
| fanbu   | smallint(6)  | NO   |     | 0       |                |
+---------+--------------+------+-----+---------+----------------+
6 rows in set (0.00 sec)


### add date `insert into table_A (X,Y,Z) values (X,1), (Y,2), (Z,3)`
mysql> `insert into` newstu (X,Y,Z) `values`(
    -> (1,'a'),
    -> (2,'b'),
    -> (3,'c'));

### 修改data
mysql> `update` table_name
    -> `set` X = 100;
//X栏全部都改了

mysql> `update` * `from` table_name
    -> `set` X = X+2;
    -> `where` Y = 6;

### 删除data
删除就是整行
一个data属于修改
mysql> `delete from` stu `where` id=2;
mysql> `delete` * `from` stu `where` id=2;
//都是删除整行 不需要 *


### 清空表数据
mysql> `truncate` newstu;
Query OK, 0 rows affected (0.01 sec)
//删除表，扔了重写，（全删的情况下更快）

mysql> `delete` `from` newstu;
//delete把数据删除重写

### data 没并行
set name utf8;

`\c` 退出继续打



# SQLite
## SQLite Mac使用
$ sqlite3
SQLite version 3.19.3 2017-06-27 16:48:08
Enter ".help" for usage hints.
Connected to a transient in-memory database.
Use ".open FILENAME" to reopen on a persistent database.

### Mac终端创建sqlite表
* 打开terminal.
* 想在文档下的sqlite文件夹下创建数据库，我们需要先装载该目录。
    * 首先进入到你要创建数据库的位置, 或者你现有数据库所在的位置.
    * 输入：cd 将文件夹地址拖到终端  然后enter
    * `cd` **/Users/wer/Documents** 
* 创建一个文件夹（存放数据库）
    * `mkdir`  **testSqlist（文件夹名）**
* 创建数据库
    * 使用sqlite3 ＋ 数据库名 可以打开数据库，我们同样也可以用该方法创建数据库
    * 例如，我们想创建名为test的数据库，需执行如下命令
        * `sqlite3` **test.db** or **test.sqlite3**
    * 需要注意，此时在sqlite文件夹下还没有我们创建的数据库文件。
* 创建表
    * 此时，实际上我们已经打开了test数据库，
    * 我们执行 `create table` teacher (name text) ; 为数据库添加一张表，
    * 再看sqlite文件夹，我们便会找到刚刚创建的数据库文

* 创建类别
luo$
`create table` table_name (
id `integer` `primary key` `not null` `default '0'`,
name `varchar`(20),
age `integer` `not null default '10'`
sex  `VARCHAR`(4)  ,
birth  `YEAR`,
department  `VARCHAR`(20) ,
address  `VARCHAR`(50)
);

* 数据类型
**varchar()**：字符(需要“”)大小写敏感
**integer**：数字
**year**：年份
**datetime**:日期

* 数据属性
**NOT NULL** 约束强制字段始终包含值。这意味着，如果不向字段添加值，就无法插入新记录或者更新记录。


* 输入数据

sqlite>`insert into` table (id, name, age, sex, birth, department, address) `values` (901,'B.I','M','1990','write song','korea');

**integer**：数字
**year**：年份(需要“”)
**varchar**：字符(需要“”)大小写敏感

* 此时我们的数据库已经创建完毕.

 * 输入：sqlite3 upload.sqlite3  
    * 如果存在文件名为“upload.sqlite3”的文件，这个操作就是打开“upload.sqlite3”文件。
    * 如果不存在“upload.sqlite3”文件，系统就会去创建它。
    * 然后输入：“;”（对，就是输入分号） 然后enter
 
 
### 输入:   ".quit"可以退出sqlite
ctrl+d
### 输入:   “.help”可以查看更多命令
 
### 常用命令

## SQLite 简介
### SQLite 局限性
在 SQLite 中，SQL92 不支持的特性如下所示：

特性|描述
---|---
**RIGHT OUTER JOIN**	| 只实现了 LEFT OUTER JOIN。
**FULL OUTER JOIN**	| 只实现了 LEFT OUTER JOIN。
**ALTER TABLE**	| 支持 RENAME TABLE 和 ALTER TABLE 的 ADD COLUMN variants 命令，不支持 DROP COLUMN、ALTER COLUMN、ADD CONSTRAINT。
**Trigger 支持**	| 支持 FOR EACH ROW 触发器，但不支持 FOR EACH STATEMENT 触发器。
**VIEWs**	| 在 SQLite 中，视图是只读的。您不可以在视图上执行 DELETE、INSERT 或 UPDATE 语句。
**GRANT 和 REVOKE**	| 可以应用的唯一的访问权限是底层操作系统的正常文件访问权限。

#### SQLite 命令
与关系数据库进行交互的标准 SQLite 命令类似于 SQL。命令包括 CREATE、SELECT、INSERT、UPDATE、DELETE 和 DROP。这些命令基于它们的操作性质可分为以下几种：

* data query language **DQL** - 数据查询语言
* data manipulation language **DML** - 数据操作语言 (change data)
* data definition language **DDL** - 数据定义语言 (change table column)
* transactional control language **TCL** 事务控制语言
* data control language **DCL** 事务控制语言


### data definition language DDL - 数据定义语言
命令	| 描述
---|---
**CREATE**	| 创建一个新的表，一个表的视图，或者数据库中的其他对象。
**ALTER**	| 修改数据库中的某个已有的数据库对象，比如一个表。
**DROP**	| 删除整个表，或者表的视图，或者数据库中的其他对象。

1.修改表名称 `ALTER TABLE` 旧表名 `RENAME TO` 新表名
2.添加字段 `ALTER TABLE` 表名 `ADD COLUMN` 列名 数据类型
3.查询表结构 `PRAGMA TABLE(表名)_INFO`

* 添加字段：
    * `alter table` 表名
    * `Add column` 字段2 int not null default 0 `AFTER` 字段1 (在字段1后面添加)
* 调整字段顺序：
    * `alter table` 表名 `CHANGE` 字段2 int not null default 0 `AFTER` 字段1




### data manipulation language DML - 数据操作语言
命令	| 描述
---|---
**INSERT**	| 创建一条记录。
**UPDATE**	| 修改记录。
**DELETE**	| 删除记录。

### data query language DQL - 数据查询语言 ！！
命令	| 描述
---|---
**SELECT**	| 从一个或多个表中检索某些记录。

#### select 后面几种不同显示column的方法：

```
sqlite>select * from table
//查询表的全部column,所有记录
or
sqlite>select * from table limit 1,3;
//查询表的第2条到4条记录. start from 0
or
sqlite>select column_A, column_B, column_C from table
//从表查询显示所有指定的column_ABC的信息
```

#### 细化column里要显示的信息
**in** ("A", "B");
**between** "A" and "B";
**=** "A" or/and column = "B";

```
sqlite>select * from table
   ...>where column in ("A", "B");
   ...>where column between "A" and "B";
   ...>where column = "A" or/and column = "B";
//从表中查询大类里column为AB的记录
//"字符需引号"，数字不需要引号
or
sqlite>SELECT id, 2013-birth AS age FROM table
   ...>WHERE 2013-birth>=18 AND 2013-birth<=22;
   ...>WHERE 2013-birth BETWEEN 18 AND 22;
//从表中查询符合某一数字区域的信息

```

#### 改变column里要显示的信息

```
sqlite>select 2018-birth as id, 'secret' as id from table
//改变数据
//将数据简单数学，直接显示计算结果，
//或将原数据替换成其他数据
```

#### 分类显示column里要的信息 **group by**

```
sqlite>select count(column_A) from table;
//从表中查询column_A里有几个信息

sqlite>select column_A from table group by column_A;
//从表中按照column_A种类，列出每种column_A
or
sqlite>select column_A, count(column_B) from table group by column_A;
//按照column_A分类，
//从表中查询每个column_A有多少column_B，
//通常查询column里有的人数count(ID)。
```

#### 做计算 **count(),max(),sum(),avg()**

```
sqlite> select column_A, count(column_B) from table group by column_A;
//按照column_A分类，
//从表中查询每个column_A有多少column_B，
//通常查询column里有的人数count(ID)。
or
sqlite> select column_A, max(column_B) from table group by column_A;
//按照column_A分类，
//各个column_A分类中，查询column_B最高分.
or
sqlite> select name, SUM(grade) FROM score
   ...> group by name;
//按照column_A分类，
//各个column_A分类中，查询column_B总和.
//要用group by或者where针对到个体
//例子：计算每个学生的总成绩 select id,name,SUM(grade) FROM student
or
sqlite> select name, avg(grade) FROM score
   ...> group by name;

```
#### sql语句中 **any** 和 **all** 的用法
这两个都是用于子查询的

any 是任意一个
all 是所有

any表示有任何一个满足就返回true，all表示全部都满足才返回true
**感觉这句很清楚

比如
select * from student where 班级=’01’ and age > all (select age from student where 班级=’02’);
就是说，查询出01班中，年龄大于 02班所有人的同学
相当于
select * from student where 班级=’01’ and age > (select max(age) from student where 班级=’02’);

而
select * from student where 班级=’01’ and age > any (select age from student where 班级=’02’);
就是说，查询出01班中，年龄大于 02班任意一个 的 同学
相当于
select * from student where 班级=’01’ and age > (select min(age) from student where 班级=’02’);

#### 一个表内相互对比：
`SELECT` A.* `FROM` SCORE **A**
`WHERE` DEGREE<(`SELECT` `AVG`(DEGREE) `FROM` SCORE **B** `WHERE` A.CNO=B.CNO);


#### 两个表之间有联系：
`SELECT` A.xx B.xx
`FROM` tableA `AS A` `JOIN` tableB `AS B` `ON` A.id=B.id;
or
`SELECT` A.xx B.xx C.xx
`FROM` tableA A `JOIN`(tableB B, tableC C)
`ON` A.SNO=C.SNO `AND` B.CNO =C.CNO;
or
`SELECT` A.sno,A.cno,B.rank `from` SCORE A, grade B
`WHERE` A.degree `BETWEEN` B.low `and` B.upp `ORDER BY` RANK;

```
sqlite> SELECT  c_name, grade FROM score
   ...> WHERE stu_id = "901" or "902"
   or
   ...> WHERE stu_id = (SELECT id FROM student WHERE name='Bobby')
   ...> or stu_id=(select id from student where name ='B.I');
//从score表中查询信息，中间调用student表里的数据
or
select * from student, score where student.id=score.stu_id;
or
SELECT A.SNAME,B.CNO,B.DEGREE FROM STUDENT AS A JOIN SCORE AS B ON A.SNO=B.SNO;
//用连接的方式查询两个表的信息
//SELECT A.xx B.xx FROM tableA AS A JOIN tableB AS B ON A.id=B.id;
or
sqlite> SELECT` student.id, name, SUM(grade) FROM student,score
   ...> WHERE student.id=score.stu_id
   ...> GROUP BY student.id;
```

#### 按高低排序 **order by ASC/DESC**

```
mysql> SELECT stu_id, grade FROM score
    -> WHERE c_name= '计算机'
    -> ORDER BY grade DESC;
    -> ORDER BY grade ASC;
//将计算机考试成绩按从高到低进行排序DESC

```

#### 从student表和score表中查询出学生的学号，然后合并查询结果

```
mysql> SELECT id  FROM student
    -> UNION
    -> SELECT stu_id  FROM score;
```

#### 查询姓张或者姓王的同学的姓名、院系和考试科目及成绩

```
sqlite> select student.id, name, c_name, grade
   ...> from student, score

   ...> where (name like'B%' or name like 'o%')      // % 只要包含此字符
   ...> where (name like'Bobby' or name like 'B.I')  // 包含列出的字符

   ...> and student.id=score.stu_id;
//查询姓张或者姓王的同学的姓名、院系和考试科目及成绩

```

#### select **distinct** cno from score
```
select distinct cno from score where degree in (select degree from score where degree>85);
```


## SQLite 命令
SQLite 简单命令：SQLite 的点命令，这些命令的不同之处在于它们不以分号`;`结束。

---

**sqlite3 命令**: 在 SQLite 命令提示符下，您可以使用各种 SQLite 命令。

```
$ sqlite3
SQLite version 3.3.6
Enter ".help" for instructions
sqlite>
```

### SQLite 点命令的列表：

* **.backup ?DB? FILE**	 | 备份 DB 数据库（默认是 "main"）到 FILE 文件。
**.bail ON|OFF**	| 发生错误后停止。默认为 OFF。
**.databases** |	列出数据库的名称及其所依附的文件。
**.dump ?TABLE?**	以 SQL 文本格式转储数据库。如果指定了 TABLE 表，则只转储匹配 LIKE 模式的 TABLE 表。
**.echo ON|OFF**	| 开启或关闭 echo 命令。
**.exit**	| 退出 SQLite 提示符。
**.explain ON|OFF**	| 开启或关闭适合于 EXPLAIN 的输出模式。如果没有带参数，则为 EXPLAIN on，及开启 EXPLAIN。
#### .header on
* **.header(s) ON|OFF**
    * 开启或关闭头部显示。
    * 显示字段名,就是把column的名字显示在上面
```
sqlite> .header on
sqlite> select * from table
XX,XX,XX,XX
```

* **.help**	| 显示消息。
    * `sqlite>.help`: 获取可用的点命令的清单，可以在任何时候输入 ".help"。

```
sqlite> .help
.auth ON|OFF           Show authorizer callbacks
.backup ?DB? FILE      Backup DB (default "main") to FILE
.bail on|off           Stop after hitting an error.  Default OFF
.binary on|off         Turn binary output on or off.  Default OFF
.cd DIRECTORY          Change the working directory to DIRECTORY
.changes on|off        Show number of rows changed by SQL
.check GLOB            Fail if output since .testcase does not match
.clone NEWDB           Clone data into NEWDB from the existing database
.databases             List names and files of attached databases
.dbinfo ?DB?           Show status information about the database
.dump ?TABLE? ...      Dump the database in an SQL text format
                         If TABLE specified, only dump tables matching
                         LIKE pattern TABLE.
.echo on|off           Turn command echo on or off
.eqp on|off|full       Enable or disable automatic EXPLAIN QUERY PLAN
.exit                  Exit this program
.explain ?on|off|auto? Turn EXPLAIN output mode on or off or to automatic
.fullschema ?--indent? Show schema and the content of sqlite_stat tables
.headers on|off        Turn display of headers on or off
.help                  Show this message
.import FILE TABLE     Import data from FILE into TABLE
.imposter INDEX TABLE  Create imposter table TABLE on index INDEX
.indexes ?TABLE?       Show names of all indexes
                         If TABLE specified, only show indexes for tables
                         matching LIKE pattern TABLE.
.limit ?LIMIT? ?VAL?   Display or change the value of an SQLITE_LIMIT
.lint OPTIONS          Report potential schema issues. Options:
                         fkey-indexes     Find missing foreign key indexes
.log FILE|off          Turn logging on or off.  FILE can be stderr/stdout
.mode MODE ?TABLE?     Set output mode where MODE is one of:
                         ascii    Columns/rows delimited by 0x1F and 0x1E
                         csv      Comma-separated values
                         column   Left-aligned columns.  (See .width)
                         html     HTML <table> code
                         insert   SQL insert statements for TABLE
                         line     One value per line
                         list     Values delimited by "|"
                         quote    Escape answers as for SQL
                         tabs     Tab-separated values
                         tcl      TCL list elements
.nullvalue STRING      Use STRING in place of NULL values
.once FILENAME         Output for the next SQL command only to FILENAME
.open ?OPTIONS? ?FILE? Close existing database and reopen FILE
                         The --new option starts with an empty file
.output ?FILENAME?     Send output to FILENAME or stdout
.print STRING...       Print literal STRING
.prompt MAIN CONTINUE  Replace the standard prompts
.quit                  Exit this program
.read FILENAME         Execute SQL in FILENAME
.restore ?DB? FILE     Restore content of DB (default "main") from FILE
.save FILE             Write in-memory database into FILE
.scanstats on|off      Turn sqlite3_stmt_scanstatus() metrics on or off
.schema ?PATTERN?      Show the CREATE statements matching PATTERN
                          Add --indent for pretty-printing
.selftest ?--init?     Run tests defined in the SELFTEST table
.separator COL ?ROW?   Change the column separator and optionally the row
                         separator for both the output mode and .import
.session CMD ...       Create or control sessions
.sha3sum ?OPTIONS...?  Compute a SHA3 hash of database content
.shell CMD ARGS...     Run CMD ARGS... in a system shell
.show                  Show the current values for various settings
.stats ?on|off?        Show stats or turn stats on or off
.system CMD ARGS...    Run CMD ARGS... in a system shell
.tables ?TABLE?        List names of tables
                         If TABLE specified, only list tables matching
                         LIKE pattern TABLE.
.testcase NAME         Begin redirecting output to 'testcase-out.txt'
.timeout MS            Try opening locked tables for MS milliseconds
.timer on|off          Turn SQL timer on or off
.trace FILE|off        Output each SQL statement as it is run
.vfsinfo ?AUX?         Information about the top-level VFS
.vfslist               List all available VFSes
.vfsname ?AUX?         Print the name of the VFS stack
.width NUM1 NUM2 ...   Set column widths for "column" mode
                         Negative values right-justify
sqlite>
```

**.import FILE TABLE**	| 导入来自 FILE 文件的数据到 TABLE 表中。
.**indices ?TABLE?**	| 显示所有索引的名称。如果指定了 TABLE 表，则只显示匹配 LIKE 模式的 TABLE 表的索引。
**.load FILE ?ENTRY?**	| 加载一个扩展库。
**.log FILE|off**	 | 开启或关闭日志。FILE 文件可以是 stderr（标准错误）/stdout（标准输出）。
#### .mode
* **.mode MODE**
    * 设置输出模式，MODE 可以是下列之一：
    * mode默认是list
* list: 由 .separator 字符串`|`分隔的值
* line: 每行一个值
* column: 左对齐的列

* csv 逗号分隔的值
* html HTML 的 <table> 代码
* insert TABLE 表的 SQL 插入（insert）语句
* tabs 由 Tab 分隔的值
* tcl TCL 列表元素

**.nullvalue STRING** | 在 NULL 值的地方输出 STRING 字符串。
**.output FILENAME** | 发送输出到 FILENAME 文件。
**.output stdout**	| 发送输出到屏幕。
**.print STRING...**	| 逐字地输出 STRING 字符串。
**.prompt MAIN CONTINUE**	| 替换标准提示符。
**.quit**	| 退出 SQLite 提示符。
**.read FILENAME**	| 执行 FILENAME 文件中的 SQL。

* **.schema ?TABLE?**:
    * 显示 CREATE 语句。
`sqlite>` `.schema`
`create table` table_name ( id `integer` `primary key` `not null default '0'`, name `verchar`(20), age `integer` `not null default '10'`);


* **.schema ?TABLE?**:
    * 显示 CREATE 语句。如果指定了 TABLE 表，则只显示匹配 LIKE 模式的 TABLE 表。
    * `sqlite>.schema` : 得到一张表的完整信息.

#### .separator
* **.separator "STRING"**
    * 改变输出模式和 .import 所使用的分隔符。

```
sqlite> .separator ","
sqlite> select * from table
XX,XX,XX,XX
```


* **.show**
    * 显示各种设置的当前值。
    * `sqlite>.show`:查看 SQLite 命令提示符的默认设置。

```
sqlite>.show
     echo: off
  explain: off
  headers: off
     mode: column
nullvalue: ""
   output: stdout
separator: "|"
    width:
sqlite>

//确保 sqlite> 提示符与点命令之间没有空格，否则将无法正常工作。
```

**.stats ON|OFF**	 | 开启或关闭统计。

* **sqlite>.tables**:
    * 验证表是否已成功创建，该命令用于列出附加数据库中的所有表。

* **sqlite>.tables ?PATTERN?**:
    * 列出匹配 LIKE 模式的表的名称。

* **.tables**: 显示此数据库中的所有表,我这个库中只有一个表


* **.timeout MS** | 尝试打开锁定的表MS 毫秒。
* **.width NUM NUM**	| 为 "column" 模式设置列宽度。
* **.timer ON|OFF**	 | 开启或关闭 CPU 定时器。


### 格式化输出
使用下列的点命令来格式化输出为本教程下面所列出的格式：
**.header on
.mode column
.timer on**

```
sqlite>.header on
sqlite>.mode column
sqlite>.timer on
sqlite>
```

上面设置将产生如下格式的输出：

```
ID          NAME        AGE         ADDRESS     SALARY
----------  ----------  ----------  ----------  ----------
1           Paul        32          California  20000.0
2           Allen       25          Texas       15000.0
3           Teddy       23          Norway      20000.0
4           Mark        25          Rich-Mond   65000.0
5           David       27          Texas       85000.0
6           Kim         22          South-Hall  45000.0
7           James       24          Houston     10000.0
CPU Time: user 0.000000 sys 0.000000
```

#### sqlite_master 表格
主表中保存数据库表的关键信息，并把它命名为 sqlite_master。
**.schema sqlite_master**: 查看表概要.

```
sqlite>.schema sqlite_master
```
这将产生如下结果：

```
CREATE TABLE sqlite_master (
  type text,
  name text,
  tbl_name text,
  rootpage integer,
  sql text
);
```


### SQL UNION 语法
* **UNION** 操作符
    * UNION 操作符用于合并两个或多个 SELECT 语句的结果集。
    * 注意:
    * UNION 内部的 SELECT 语句必须拥有相同数量的列。
    * 列也必须拥有相似的数据类型。
    * 同时，每条 SELECT 语句中的列的顺序必须相同。
注释：默认地，UNION 操作符选取不同的值。如果允许重复的值，请使用 UNION ALL。

`SELECT` NameA `as` name sexA `as` sex `FROM` tableA
`UNION`
`SELECT` NameB `as` name sexB `as` sex `FROM` tableB

```
Employees_China:
E_ID	          E_Name
01	            Zhang, Hua
02	            Wang, Wei
03	            Carter, Thomas
04	            Yang, Ming

Employees_USA:
E_ID	          E_Name
01	            Adams, John
02	            Bush, George
03	            Carter, Thomas
04	            Gates, Bill

SELECT E_Name FROM Employees_China
UNION
SELECT E_Name FROM Employees_USA

结果:
E_Name
Zhang, Hua
Wang, Wei
Carter, Thomas
Yang, Ming
Adams, John
Bush, George
Gates, Bill
//注释：这个命令无法列出在中国和美国的所有雇员。
//在上面的例子中，我们有两个名字相同的雇员，他们当中只有一个人被列出来了。
//UNION 命令只会选取不同的值。
```


### SQL UNION ALL 语法
SELECT column_name(s) FROM table_name1
UNION ALL
SELECT column_name(s) FROM table_name2
另外，UNION 结果集中的列名总是等于 UNION 中第一个 SELECT 语句中的列名。

#### SQLite UPDATE 语句：
```
UPDATE table_name
SET column1 = value1, column2 = value2....columnN=valueN
[ WHERE  CONDITION ];
```

#### SQLite VACUUM 语句：
```
VACUUM;
```

#### SQLite WHERE 子句：
```
SELECT column1, column2....columnN
FROM   table_name
WHERE  CONDITION;
```

## SQLite 数据类型
**SQLite 数据类型**: 是一个用来指定任何对象的数据类型的属性。
SQLite 中的每一列，每个变量和表达式都有相关的数据类型。
您可以在创建表的同时使用这些数据类型。SQLite 使用一个更普遍的动态类型系统。
在 SQLite 中，值的数据类型与值本身是相关的，而不是与它的容器相关。

### SQLite 存储类
每个存储在 SQLite 数据库中的值都具有以下存储类之一：

存储类	| 描述
---|---
**NULL**	| 值是一个 NULL 值。
**INTEGER** | 	值是一个带符号的整数，根据值的大小存储在 1、2、3、4、6 或 8 字节中。
**REAL**	 | 值是一个浮点值，存储为 8 字节的 IEEE 浮点数字。
**TEXT**	 | 值是一个文本字符串，使用数据库编码（UTF-8、UTF-16BE 或 UTF-16LE）存储。
**BLOB**	| 值是一个 blob 数据，完全根据它的输入存储。

SQLite 的**存储类**稍微比数据类型更普遍。INTEGER 存储类，例如，包含 6 种不同的不同长度的整数数据类型。

### SQLite 亲和(Affinity)类型
SQLite支持列的亲和类型概念。任何列仍然可以存储任何类型的数据，当数据插入时，该字段的数据将会优先采用亲缘类型作为该值的存储方式。SQLite目前的版本支持以下五种亲缘类型：

亲和类型	| 描述
---|---
**TEXT** |	数值型数据在被插入之前，需要先被转换为文本格式，之后再插入到目标字段中。
**NUMERIC** | 	当文本数据被插入到亲缘性为`NUMERIC`的字段中时，如果转换操作不会导致数据信息丢失以及完全可逆，那么SQLite就会将该文本数据转换为`INTEGER`或`REAL`类型的数据，如果转换失败，SQLite仍会以`TEXT`方式存储该数据。对于`NULL`或`BLOB`类型的新数据，SQLite将不做任何转换，直接以NULL或BLOB的方式存储该数据。需要额外说明的是，对于浮点格式的常量文本，如"30000.0"，如果该值可以转换为INTEGER同时又不会丢失数值信息，那么SQLite就会将其转换为INTEGER的存储方式。
**INTEGER** | 	对于亲缘类型为`INTEGER`的字段，其规则等同于`NUMERIC`，唯一差别是在执行CAST表达式时。
**REAL**	 | 其规则基本等同于`NUMERIC`，唯一的差别是不会将"30000.0"这样的文本数据转换为INTEGER存储方式。
**NONE** | 	不做任何的转换，直接以该数据所属的数据类型进行存储。　　

### SQLite 亲和类型(Affinity)及类型名称
下表列出了当创建 SQLite3 表时可使用的各种数据类型名称，同时也显示了相应的亲和类型：

亲和类型: 数据类型

* **INTEGER**:
    * INT
    * INTEGER
    * TINYINT
    * SMALLINT
    * MEDIUMINT
    * BIGINT
    * UNSIGNED BIG INT
    * INT2
    * INT8

* **TEXT**:
    * CHARACTER(20)
    * VARCHAR(255)
    * VARYING CHARACTER(255)
    * NCHAR(55)
    * NATIVE CHARACTER(70)
    * NVARCHAR(100)
    * TEXT
    * CLOB

* **NONE**:
    * BLOB
    * no datatype specified

* **REAL**:
    * REAL
    * DOUBLE
    * DOUBLE PRECISION
    * FLOAT

* **NUMERIC**:
    * NUMERIC
    * DECIMAL(10,5)
    * BOOLEAN
    * DATE
    * DATETIME

### Boolean 数据类型
SQLite 没有单独的 Boolean 存储类。相反，布尔值被存储为整数 0（false）和 1（true）。

### Date 与 Time 数据类型
SQLite 没有一个单独的用于存储日期和/或时间的存储类，但 SQLite 能够把日期和时间存储为 `TEXT`、`REAL` 或 `INTEGER` 值。

存储类 |	日期格式
---|---
**TEXT**	| 格式为 `YYYY-MM-DD` `HH:MM:SS.SSS` 的日期。
**REAL**	| 从公元前 4714 年 11 月 24 日格林尼治时间的正午开始算起的天数。
**INTEGER**	| 从 `1970-01-01 00:00:00 UTC` 算起的秒数。

您可以以任何上述格式来存储日期和时间，并且可以使用内置的日期和时间函数来自由转换不同格式。

## SQLite 创建数据库

#### sqlite3 创建新的 SQLite 数据库
`sqlite3`: 创建新的 SQLite 数据库。您不需要任何特殊的权限即可创建一个数据。

**实例**:

```
$sqlite3 testDB.db      
SQLite version 3.7.15.2 2013-01-09 11:53:05
Enter ".help" for instructions
Enter SQL statements terminated with a ";"
sqlite>
```
通常情况下，数据库名称在 RDBMS 内应该是唯一的。
在当前目录下创建一个文件,一个新的数据库 `testDB.db`。该文件将被 SQLite 引擎用作数据库。
`sqlite3` 命令在成功创建数据库文件之后，将提供一个 `sqlite>` 提示符。

#### .databases 检查它是否在数据库列表中
`.databases`: 一旦数据库被创建，使用 SQLite 的 `.databases` 命令来检查它是否在数据库列表中，如下所示：

```
sqlite>.databases
seq  name             file
---  ---------------  ----------------------
0    main             /home/sqlite/testDB.db
```

#### .quit 退出
`.quit` 命令: 退出 sqlite 提示符，如下所示：

```
sqlite>.quit
$
```

#### .dump 命令 导出完整的数据库
`.dump`: 在命令提示符中使用 SQLite `.dump` 点命令来导出完整的数据库在一个文本文件中，如下所示：

```
$sqlite3 testDB.db .dump > testDB.sql
```
转换整个`testDB.db`数据库的内容到 SQLite 的语句中，并将其转储到 ASCII 文本文件 `testDB.sql` 中。


```
$sqlite3 testDB.db < testDB.sql
```
您可以通过简单的方式从生成的`testDB.sql`恢复，如下所示：
此时的数据库是空的，一旦数据库中有表和数据，您可以尝试上述两个程序。


## SQLite 附加数据库
假设这样一种情况，当在同一时间有多个数据库可用，您想使用其中的任何一个。
`ATTACH DATABASE` 语句: 用来选择一个特定的数据库，使用该命令后，所有的 SQLite 语句将在附加的数据库下执行。

```
ATTACH DATABASE 'DatabaseName' As 'Alias-Name';
```

如果数据库尚未被创建，上面的命令将创建一个数据库，
如果数据库已存在，则把`数据库文件`名称与`逻辑数据库Alias-Name` 绑定在一起。

```
sqlite> ATTACH DATABASE 'testDB.db' as 'TEST';
sqlite> .database         //使用 SQLite .database 命令来显示附加的数据库。
seq  name             file
---  ---------------  ----------------------
0    main             /home/sqlite/testDB.db
2    test             /home/sqlite/testDB.db
```
附加一个现有的数据库 `testDB.db`.
数据库名称 `main` 和 `temp` 被保留用于`主数据库`和存储`临时表`及其他`临时数据对象的数据库`。
这两个数据库名称可用于每个数据库连接，且不应该被用于附加，否则将得到一个警告消息，如下所示：

```
sqlite>  ATTACH DATABASE 'testDB.db' as 'TEMP';
Error: database TEMP is already in use
sqlite>  ATTACH DATABASE 'testDB.db' as 'main';
Error: database main is already in use；
```

## SQLite 分离数据库
`DETACH DTABASE` 语句: 用来把命名数据库从一个数据库连接分离和游离出来，连接是之前使用 ATTACH 语句附加的。
如果同一个数据库文件已经被附加上多个别名，`DETACH` 命令将只断开给定名称的连接，而其余的仍然有效。您无法分离 `main` 或 `temp` 数据库。
如果数据库是在内存中或者是临时数据库，则该数据库将被摧毁，且内容将会丢失。

```
DETACH DATABASE 'Alias-Name';  
//Alias-Name与之前 ATTACH 语句附加数据库时所用到的别名相同。
```

```
sqlite> ATTACH DATABASE 'testDB.db' as 'test';
sqlite> ATTACH DATABASE 'testDB.db' as 'currentDB';

sqlite>.databases
seq  name             file
---  ---------------  ----------------------
0    main             /home/sqlite/testDB.db
2    test             /home/sqlite/testDB.db
3    currentDB        /home/sqlite/testDB.db

sqlite> DETACH DATABASE 'currentDB';

sqlite>.databases
seq  name             file
---  ---------------  ----------------------
0    main             /home/sqlite/testDB.db
2    test             /home/sqlite/testDB.db

//把 'currentDB' 从 testDB.db 中分离出来
//如果检查当前附加的数据库，testDB.db 仍与 'test' 和 'main' 保持连接。
```

## SQLite 创建表
`CREATE TABLE` 语句: 用于在任何给定的数据库创建一个新表。
创建基本表，涉及到命名表、定义列及每一列的数据类型。

**语法**:
```
CREATE TABLE database_name.table_name(
   column1 datatype  PRIMARY KEY(one or more columns),
   column2 datatype,
   column3 datatype,
   .....
   columnN datatype,
);
```
CREATE TABLE 是告诉数据库系统创建一个新表的关键字。
CREATE TABLE 语句后跟着表的唯一的名称或标识。
您也可以选择指定带有 `table_name` 的 `database_name`。


```
//创建一个 `COMPANY 表`，`ID` 作为主键，`NOT NULL` 的约束表示在表中创建纪录时这些字段不能为 `NULL`：

sqlite> CREATE TABLE COMPANY(
   ID INT PRIMARY KEY     NOT NULL,
   NAME           TEXT    NOT NULL,
   AGE            INT     NOT NULL,
   ADDRESS        CHAR(50),
   SALARY         REAL
);

//让我们再创建一个表，我们将在随后章节的练习中使用：

sqlite> CREATE TABLE DEPARTMENT(
   ID INT PRIMARY KEY      NOT NULL,
   DEPT           CHAR(50) NOT NULL,
   EMP_ID         INT      NOT NULL
);

sqlite>.tables
COMPANY     DEPARTMENT
//这里可以看到我们刚创建的两张表 COMPANY、 DEPARTMENT。

//使用 SQLite .schema 命令得到表的完整信息
sqlite>.schema COMPANY
CREATE TABLE COMPANY(
   ID INT PRIMARY KEY     NOT NULL,
   NAME           TEXT    NOT NULL,
   AGE            INT     NOT NULL,
   ADDRESS        CHAR(50),
   SALARY         REAL
);
```

## SQLite 运算符
SQLite 运算符: 运算符是一个保留字或字符，主要用于 SQLite 语句的 WHERE 子句中执行操作，如比较和算术运算。运算符用于指定 SQLite 语句中的条件，并在语句中连接多个条件。

* 算术运算符
* 比较运算符
* 逻辑运算符
* 位运算符

### SQLite 算术运算符
假设变量 a=10，变量 b=20，则：

运算符 | 描述 | 实例
---|---|---
`+`	| 加法 - 把运算符两边的值相加 | a + b 将得到 30
`-`	| 减法 - 左操作数减去右操作数 | a - b 将得到 -10
`*`	| 乘法 - 把运算符两边的值相乘 | a * b 将得到 200
`/`	| 除法 - 左操作数除以右操作数 | b / a 将得到 2
`%`	| 取模 - 左操作数除以右操作数后得到的余数 | b % a will give 0

```
sqlite> .mode line
sqlite> select 10 + 20;
10 + 20 = 30


sqlite> select 10 - 20;
10 - 20 = -10


sqlite> select 10 * 20;
10 * 20 = 200


sqlite> select 10 / 5;
10 / 5 = 2


sqlite> select 12 %  5;
12 %  5 = 2
```

### SQLite 比较运算符
假设变量 a=10，变量 b=20，则：

运算符 | 描述 | 实例
---|---|---
`==`	| 检查两个操作数的值是否相等，如果相等则条件为真。 | (a == b) 不为真。
`=	`  | 检查两个操作数的值是否相等，如果相等则条件为真。 | (a = b) 不为真。
`!=`	| 检查两个操作数的值是否不相等，如果不相等则条件为真。 | (a != b) 为真。
`<>`	| 检查两个操作数的值是否不相等，如果不相等则条件为真。 | (a <> b) 为真。
---|---|---
`>` | 检查左操作数的值是否大于右操作数的值，如果是则条件为真。| (a > b) 不为真。
`<` | 检查左操作数的值是否小于右操作数的值，如果是则条件为真。| (a < b) 为真。
`>=` | 检查左操作数的值是否大于等于右操作数的值，如果是则条件为真。| (a >= b) 不为真。
`<=` | 检查左操作数的值是否小于等于右操作数的值，如果是则条件为真。| (a <= b) 为真。
---|---|---
`!<` | 检查左操作数的值是否不小于右操作数的值，如果不小于则条件为真。 | (10 !< 20) 为假。
`!>` | 检查左操作数的值是否不大于右操作数的值，如果不大于则条件为真。| (a !> b) 为真。

**实例**

```
//假设 COMPANY 表有以下记录：

ID          NAME        AGE         ADDRESS     SALARY
----------  ----------  ----------  ----------  ----------
1           Paul        32          California  20000.0
2           Allen       25          Texas       15000.0
3           Teddy       23          Norway      20000.0
4           Mark        25          Rich-Mond   65000.0
5           David       27          Texas       85000.0
6           Kim         22          South-Hall  45000.0
7           James       24          Houston     10000.0
```

下面的实例演示了各种 SQLite 比较运算符的用法。

```
sqlite> SELECT * FROM COMPANY WHERE SALARY > 50000;
//SELECT 语句列出了 SALARY 大于 50,000.00 的所有记录：

ID          NAME        AGE         ADDRESS     SALARY
----------  ----------  ----------  ----------  ----------
4           Mark        25          Rich-Mond   65000.0
5           David       27          Texas       85000.0

sqlite>  SELECT * FROM COMPANY WHERE SALARY = 20000;
//SELECT 语句列出了 SALARY 等于 20,000.00 的所有记录：

ID          NAME        AGE         ADDRESS     SALARY
----------  ----------  ----------  ----------  ----------
1           Paul        32          California  20000.0
3           Teddy       23          Norway      20000.0

sqlite>  SELECT * FROM COMPANY WHERE SALARY != 20000;
//下面的 SELECT 语句列出了 SALARY 不等于 20,000.00 的所有记录：

ID          NAME        AGE         ADDRESS     SALARY
----------  ----------  ----------  ----------  ----------
2           Allen       25          Texas       15000.0
4           Mark        25          Rich-Mond   65000.0
5           David       27          Texas       85000.0
6           Kim         22          South-Hall  45000.0
7           James       24          Houston     10000.0

sqlite> SELECT * FROM COMPANY WHERE SALARY <> 20000;
//下面的 SELECT 语句列出了 SALARY 不等于 20,000.00 的所有记录：

ID          NAME        AGE         ADDRESS     SALARY
----------  ----------  ----------  ----------  ----------
2           Allen       25          Texas       15000.0
4           Mark        25          Rich-Mond   65000.0
5           David       27          Texas       85000.0
6           Kim         22          South-Hall  45000.0
7           James       24          Houston     10000.0

sqlite> SELECT * FROM COMPANY WHERE SALARY >= 65000;
//下面的 SELECT 语句列出了 SALARY 大于等于 65,000.00 的所有记录：

ID          NAME        AGE         ADDRESS     SALARY
----------  ----------  ----------  ----------  ----------
4           Mark        25          Rich-Mond   65000.0
5           David       27          Texas       85000.0
```

### SQLite 逻辑运算符
下面是 SQLite 中所有的逻辑运算符列表。

**运算符**	| 描述
---|---
`AND`	| 允许在一个 SQL 语句的 WHERE 子句中的多个条件的存在。
`EXISTS` | 用于在满足一定条件的指定表中搜索行的存在。
---|---
`IN`	| 用于把某个值与一系列指定列表的值进行比较。
`NOT IN`	| IN 运算符的对立面，用于把某个值与不在一系列指定列表的值进行比较。
`BETWEEN`	|  用于在给定最小值和最大值范围内的一系列值中搜索值。
---|---
`LIKE`	| 用于把某个值与使用`通配符运算符`的相似值进行比较。
`GLOB`	| 用于把某个值与使用`通配符运算符`的相似值进行比较。
-- | **GLOB 与 LIKE 不同之处在于，它是大小写敏感的。**
NOT | 是所用的逻辑运算符的对立面。比如 NOT EXISTS、NOT BETWEEN、NOT IN，等等。它是否定运算符。
OR	| 用于结合一个 SQL 语句的 WHERE 子句中的多个条件。
IS NULL	| NULL 运算符用于把某个值与 NULL 值进行比较。
IS| 与 = 相似。
IS NOT	| 与 != 相似。
`||`	| 连接两个不同的字符串，得到一个新的字符串。
UNIQUE	| 搜索指定表中的每一行，确保唯一性（无重复）。

实例

```
假设 COMPANY 表有以下记录：

ID          NAME        AGE         ADDRESS     SALARY
----------  ----------  ----------  ----------  ----------
1           Paul        32          California  20000.0
2           Allen       25          Texas       15000.0
3           Teddy       23          Norway      20000.0
4           Mark        25          Rich-Mond   65000.0
5           David       27          Texas       85000.0
6           Kim         22          South-Hall  45000.0
7           James       24          Houston     10000.0



sqlite> SELECT * FROM COMPANY WHERE AGE >= 25 AND SALARY >= 65000;
// SELECT 语句列出了 AGE 大于等于 25 且工资大于等于 65000.00 的所有记录：
ID          NAME        AGE         ADDRESS     SALARY
----------  ----------  ----------  ----------  ----------
4           Mark        25          Rich-Mond   65000.0
5           David       27          Texas       85000.0

sqlite> SELECT * FROM COMPANY WHERE AGE >= 25 OR SALARY >= 65000;
// SELECT 语句列出了 AGE 大于等于 25 或工资大于等于 65000.00 的所有记录：
ID          NAME        AGE         ADDRESS     SALARY
----------  ----------  ----------  ----------  ----------
1           Paul        32          California  20000.0
2           Allen       25          Texas       15000.0
4           Mark        25          Rich-Mond   65000.0
5           David       27          Texas       85000.0

sqlite>  SELECT * FROM COMPANY WHERE AGE IS NOT NULL;
//SELECT 语句列出了 AGE 不为 NULL 的所有记录，
//结果显示所有的记录，意味着没有一个记录的 AGE 等于 NULL：
ID          NAME        AGE         ADDRESS     SALARY
----------  ----------  ----------  ----------  ----------
1           Paul        32          California  20000.0
2           Allen       25          Texas       15000.0
3           Teddy       23          Norway      20000.0
4           Mark        25          Rich-Mond   65000.0
5           David       27          Texas       85000.0
6           Kim         22          South-Hall  45000.0
7           James       24          Houston     10000.0

sqlite> SELECT * FROM COMPANY WHERE NAME LIKE 'Ki%';
//SELECT 语句列出了 NAME 以 'Ki' 开始的所有记录，'Ki' 之后的字符不做限制：
ID          NAME        AGE         ADDRESS     SALARY
----------  ----------  ----------  ----------  ----------
6           Kim         22          South-Hall  45000.0

sqlite> SELECT * FROM COMPANY WHERE NAME GLOB 'Ki*';
//SELECT 语句列出了 NAME 以 'Ki' 开始的所有记录，'Ki' 之后的字符不做限制：
ID          NAME        AGE         ADDRESS     SALARY
----------  ----------  ----------  ----------  ----------
6           Kim         22          South-Hall  45000.0

sqlite> SELECT * FROM COMPANY WHERE AGE IN ( 25, 27 );
//SELECT 语句列出了 AGE 的值为 25 或 27 的所有记录：
ID          NAME        AGE         ADDRESS     SALARY
----------  ----------  ----------  ----------  ----------
2           Allen       25          Texas       15000.0
4           Mark        25          Rich-Mond   65000.0
5           David       27          Texas       85000.0


sqlite> SELECT * FROM COMPANY WHERE AGE NOT IN ( 25, 27 );
//SELECT 语句列出了 AGE 的值既不是 25 也不是 27 的所有记录：
ID          NAME        AGE         ADDRESS     SALARY
----------  ----------  ----------  ----------  ----------
1           Paul        32          California  20000.0
3           Teddy       23          Norway      20000.0
6           Kim         22          South-Hall  45000.0
7           James       24          Houston     10000.0

sqlite> SELECT * FROM COMPANY WHERE AGE BETWEEN 25 AND 27;
//SELECT 语句列出了 AGE 的值在 25 与 27 之间的所有记录：
ID          NAME        AGE         ADDRESS     SALARY
----------  ----------  ----------  ----------  ----------
2           Allen       25          Texas       15000.0
4           Mark        25          Rich-Mond   65000.0
5           David       27          Texas       85000.0

sqlite> SELECT AGE FROM COMPANY
        WHERE EXISTS (SELECT AGE FROM COMPANY WHERE SALARY > 65000);
//SELECT 语句使用 SQL 子查询，
//子查询查找 SALARY > 65000 的带有 AGE 字段的所有记录，
//后边的 WHERE 子句与 EXISTS 运算符一起使用，列出了外查询中的 AGE 存在于子查询返回的结果中的所有记录：
AGE
----------
32
25
23
25
27
22
24


sqlite> SELECT * FROM COMPANY
        WHERE AGE > (SELECT AGE FROM COMPANY WHERE SALARY > 65000);
//SELECT 语句使用 SQL 子查询，
//子查询查找 SALARY > 65000 的带有 AGE 字段的所有记录，后边的 WHERE 子句与 > 运算符一起使用，列出了外查询中的 AGE 大于子查询返回的结果中的年龄的所有记录：

ID          NAME        AGE         ADDRESS     SALARY
----------  ----------  ----------  ----------  ----------
1           Paul        32          California  20000.0
```

### SQLite 位运算符
位运算符作用于位，并逐位执行操作。真值表 `&`, `| `, `~` ：

```
0&0=0
1&0=0
0&1=0
1&1=1

0|0=0
1|0=1
0|1=1
1|1=1

~0=1
~1=0

假设如果 A = 60，且 B = 13，现在以二进制格式，它们如下所示：
A = 0011 1100
B = 0000 1101
-----------------
A&B = 0000 1100
A|B = 0011 1101
~A  = 1100 0011
```

下表中列出了 SQLite 语言支持的位运算符。假设变量 A=60，变量 B=13，则：

运算符	| 描述
---|---
**& 二进制 AND 运算符**	| 如果同时存在于两个操作数中，二进制 AND 运算符复制一位到结果中
---| (A & B) 将得到 12，即为 0000 1100
**| 二进制 OR 运算符**	| 如果存在于任一操作数中，二进制 OR 运算符复制一位到结果中
---| (A | B) 将得到 61，即为 0011 1101
**~ 二进制补码运算符** |	一元运算符，具有"翻转"位效应，即0变成1，1变成0。
---| A = 0011 1100
---| (~A ) 将得到 -61，即为 1100 0011，一个有符号二进制数的补码形式。
**<< 二进制左移运算符** |	二进制左移运算符。左操作数的值向左移动右操作数指定的位数。|
---| A = 0011 1100
---| A << 2 将得到 240，即为 1111 0000
**>> 二进制右移运算符** |	左操作数的值向右移动右操作数指定的位数。
---| A = 0011 1100
---| A >> 2 将得到 15，即为 0000 1111

```
实例

sqlite> .mode line
sqlite> select 60 | 13;
60 | 13 = 61

sqlite> select 60 & 13;
60 & 13 = 12

sqlite>  select  (~60);
(~60) = -61

sqlite>  select  (60 << 2);
(60 << 2) = 240

sqlite>  select  (60 >> 2);
(60 >> 2) = 15
```


## SQLite 高级教程
SQLite PRAGMA
### SQLite 约束
* **约束**:
    * 在表的数据列上强制执行的规则。
    * 用来限制可以插入到表中的数据类型。这确保了数据库中数据的准确性和可靠性。
    * 约束可以是列级或表级。*列级*约束仅适用于列，*表级*约束被应用到整个表。
以下是在 SQLite 中常用的约束。
##### SQL **Not null** 约束
* `NOT NULL` 约束
    * 默认情况下，*列*可以保存 NULL 值。
    * 强制*列*不接受 NULL 值。
    * 强制字段始终包含值。如果不向字段添加值，就无法插入新记录或者更新记录。
    * NULL 与没有数据是不一样的，它代表着未知的数据。
#### SQL Primary KEY 约束
* **PRIMARY KEY**
    * 约束唯一标识数据库表中的每个记录。
    * 在一个表中可以有多个 UNIQUE 列，但只能有一个主键。在设计数据库表时，主键是很重要的。主键是唯一的 ID。
    * 我们使用主键来引用表中的行。可通过把主键设置为其他表的外键，来创建表之间的关系。
    * 由于"长期存在编码监督"，在 SQLite 中，主键可以是 NULL，这是与其他数据库不同的地方。
    * 主键是表中的一个字段，唯一标识数据库表中的各行/记录。主键必须包含唯一值。主键列不能有 NULL 值。
    * 一个表只能有一个主键，它可以由一个或多个字段组成。当多个字段作为主键，它们被称为复合键。
    * 如果一个表在任何字段上定义了一个主键，那么在这些字段上不能有两个记录具有相同的值。


#### SQL Foreign KEY 约束
#### DEFAULT 约束：当某列没有指定值时，为该列提供默认值。
#### UNIQUE 约束：确保某列中的所有值是不同的。
* **UNIQUE**
    * 约束防止在一个特定的列存在两个记录具有相同的值。
    * 在 COMPANY 表中，例如，您可能要防止两个或两个以上的人具有相同的年龄。


#### CHECK 约束：CHECK 约束确保某列中的所有值满足一定条件。
SQLite Join
SQLite Unions 子句
SQLite 别名
SQLite 触发器
SQLite 索引
SQLite Indexed By
SQLite Alter 命令
SQLite Truncate Table
SQLite 视图
SQLite 事务
SQLite 子查询
SQLite Autoincrement
SQLite 注入
SQLite Explain
SQLite Vacuum
SQLite 日期 & 时间
SQLite 常用函数

## SQLite 接口
SQLite - C/C++
SQLite - Java
SQLite - PHP
SQLite - Perl
SQLite - Python

# SQL 安装问题

## Mac安装mysql问题之-bash: mysql: command not found
mysql -u root -p
-bash: mysql: command not found

解决方法：

* 在你的Mac终端,输入： `cd ~` //进入~文件夹
* 然后输入：`touch .bash_profile`
* 回车执行后，
* 再输入：`open -e .bash_profile`
* 这时候会出现一个TextEdit，如果以前没有配置过环境变量，呈现在你眼前的就是一个空白文档，你需要在这个空白文档里输入：`export PATH=$PATH:/usr/local/mysql/bin`
* 然后关闭这个TextEdit
* 回到终端面板，输入：`source ~/.bash_profile`

以上，问题解决

再输入：mysql -u root -p
回车后就会显示：Enter password:
正确输入你的密码
