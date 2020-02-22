# SQL

[TOC]

# Operator

## GLOB 子句：
```sql
SQL> SELECT column1, column2....columnN
     FROM   table_name
     WHERE  column_name GLOB { PATTERN };
```

## GROUP BY 子句：
```sql
SQL> SELECT SUM(column_name)
     FROM   table_name
     WHERE  CONDITION
     GROUP BY column_name;
```

* AVG(): finds the average value of *numeric attribute*
* MIN(): finds the minimum value of *string/numeric attribute*
* MAX(): finds the maximum value of *string/numeric attribute*
* SUM(): finds the sum total of a *numeric attribute*
* COUNT(): counts the number of rows in a set.


## HAVING 子句：
```sql
SQL> SELECT SUM(column_name)
     FROM   table_name
     WHERE  CONDITION
     GROUP BY column_name
     HAVING (arithematic function condition);
```
The `HAVING` clause can do the same thing as `WHERE` clause

* SELECT FID, Name FROM Faculty
* HAVING Rank = 'Professor';

* SELECT FID, Name FROM Faculty
* WHERE Rank = 'Professor';
/generate the same output,
/but the WHERE clause provides a better performance

`HAVING` clause ually used with GROUP BY, can include aggregate functions (previous page)



## IN 子句：
```sql
SQL> SELECT column1, column2....columnN
     FROM table_name
     WHERE column_name IN (val-1, val-2,...val-N);
```


## JOIN 子句
combine rows from tables based on common field.
```sql
1.
SQL> SELECT columnA1, columnA2, columnB1, columnB2...
     FROM TableA
     (INNER) JOIN TableB
     ON tableA.column_name=tableB.column_name;;

2.
SQL> SELECT column_name(s)
     FROM table1
     LEFT (OUTER) JOIN table2
     ON table1.column_name=table2.column_name;

3.
SQL> SELECT column_name(s)
     FROM table1
     RIGHT (OUTER) JOIN table2
     ON table1.column_name=table2.column_name;

4.
SQL> SELECT column_name(s)
     FROM table1
     FULL (OUTER) JOIN table2
     ON table1.column_name=table2.column_name;
```

1. `INNER JOIN`: 如果表中有至少一个匹配，则返回行
2. `LEFT JOIN`: Return all rows from the left table, and the matched rows from the right table. 即使右表中没有匹配，也从左表返回所有的行
3. `RIGHT JOIN`: Return all rows from the right table, and the matched rows from the left table.即使左表中没有匹配，也从右表返回所有的行
4. `FULL JOIN`: Return all rows when there is a match in ONE of the tables.只要其中一个表中存在匹配，则返回行. 结合了 LEFT JOIN 和 RIGHT JOIN 的结果。


```sql
Websites
+----+--------------+---------------------------+-------+---------+
| id | name         | url                       | alexa | country |
+----+--------------+---------------------------+-------+---------+
| 1  | Google       | https://www.google.cm/    | 1     | USA     |
| 2  | 淘宝          | https://www.taobao.com/   | 13    | CN      |
| 3  | 菜鸟教程      | http://www.runoob.com/    | 4689  | CN      |
| 4  | 微博          | http://weibo.com/         | 20    | CN      |
| 5  | Facebook     | https://www.facebook.com/ | 3     | USA     |
| 7  | stackoverflow | http://stackoverflow.com/ |   0 | IND     |
+----+---------------+---------------------------+-------+---------+

access_log
+-----+---------+-------+------------+
| aid | site_id | count | date       |
+-----+---------+-------+------------+
|   1 |       1 |    45 | 2016-05-10 |
|   2 |       3 |   100 | 2016-05-13 |
|   3 |       1 |   230 | 2016-05-14 |
|   4 |       2 |    10 | 2016-05-14 |
|   5 |       5 |   205 | 2016-05-14 |
|   6 |       4 |    13 | 2016-05-15 |
|   7 |       3 |   220 | 2016-05-15 |
|   8 |       5 |   545 | 2016-05-16 |
|   9 |       3 |   201 | 2016-05-17 |
+-----+---------+-------+------------+
```

```sql
SQL> SELECT Websites.name, access_log.count, access_log.date
     FROM Websites
     LEFT JOIN access_log
     ON Websites.id=access_log.site_id
     ORDER BY access_log.count DESC;

![-w534](media/15361896864552/15590897911253.jpg)

SELECT Websites.name, access_log.count, access_log.date
FROM access_log
RIGHT JOIN Websites
ON access_log.site_id=Websites.id
ORDER BY access_log.count DESC;

![-w523](media/15361896864552/15590898224273.jpg)
```

join more tables:

`SELECT` SID, C.MCode, C.Cno, C.Title
`FROM` Enrollment E, Section S, Course C
`WHERE` E.CallNo = S.CallNo `AND` S.Mcode = C.MCode `AND` S.CNo = C.CNo
`ORDER BY` SID


## Like 子句：
```sql
SQL> SELECT column1, column2....columnN
     FROM table_name
     WHERE column_name LIKE PATTERN%;
```

*case sensetive*

* `LIKE` 'Toyota`%`';      /*start with Toyota*
* `LIKE` '`%`0';           /*end with 0*
* `LIKE` '`%`RX4`%`'       /*contain RX$*
* `NOT LIKE` '`%`RX4`%`'   /*do NOT match the pattern*



## MINUS
返回存在于A表中，但不存在于B表中的数据。
```sql
SQL> SELECT  COL1,COL2
     FROM TABLE_A  [ WHERE conditions ]
     MINUS
     SELECT COL1 , COL2
     FROM TABLE_B [ WHERE conditions]。
```
Oracle 数据库支持 MINUS 用法，SQL Server, PostgreSQL, and SQLite 可以使用Except代替



## NOT IN 子句：
```sql
SQL> SELECT column1, column2....columnN
     FROM   table_name
     WHERE  column_name NOT IN (val-1, val-2,...val-N);
```

## ORDER BY 子句：
```sql
SELECT column1, column2....columnN
FROM   table_name
WHERE  CONDITION
ORDER BY column_name {ASC|DESC};
```


# comparison

## NULL
* To check whether a value is NULL or not in MySQL,
* we can use `IS NULL` or `IS NOT NULL`
```
SELECT * FROM Section
WHERE Room IS NULL;
```


## Relational Algebra - Examples
* A X B X C, 要标注 where 条件 and key一一对应

1. example

    `Π`course.MCode, Course.Cno, Schedule, Room, Credit
(`σ`SID = "625018" (Enrollment `X` Section `X` Course))

```sql
SQL> SELECT C.MCode, C.CNo, Credit, Schedule, Room
     FROM Enrollment E, Section S, Course C
     WHERE E.SID='20000006'
     AND E.CallNo=S.CallNo AND S.MCode=C.MCode AND S.CNo=C.CNo;
```

2. SID

    SID --- `Π`SID(Student) - `Π`SID(Transcript)
`Π`Student.SID,Name (SID   Student))

```sql
SQL> SELECT` SID, Name FROM Student
     WHERE` SID `IN` (
     SELECT SID FROM Student`
     MINUS` `SELECT SID FROM Transcript`;
```

3. group

    SC -- SID`G`sum(Credit)(Transcript `X` Course)
`Π`SC.SID,Name `σ`sum(Credit) >= 6 (SC `X` Student))

```sql
SQL> SELECT S.SID, S.Name, SUM(Credit)
     FROM Student S, Transcript T, Course C
     WHERE S.SID=T.SID AND T.MCode=C.Mcode AND T.CNo= C.Cno
     GROUP BY S.SID
     HAVING SUM(Credit)>=6;
```



# oracle
## oracle修改Table的主键的方法

```sql
第一步：增加列key_no
SQL> ALERT TABLE table_name ADD key_no int;

第二部：给key_no更新值
SQL> UPDATE table_name SET key_no =rownum;
     commit;

第三步：将key_no置为非空
SQL> ALERT TABLE table_name MODIFY key_no   int   not null;           


第四步：查找主键
SQL> select    constraint_name from    user_constraints where constraint_type='P' and   owner=user    and    table_name='TB_ZHAOZHENLONG'

第五步：删除主键
      ALTER TABLE TB_ZHAOZHENLONG DROP CONSTRAINT PK_TB_ZHAOZHENLONG;

第五步：删除主键
      ALTER TABLE TB_ZHAOZHENLONG DROP CONSTRAINT PK_TB_ZHAOZHENLONG;

第六步：增加主键
      ALTER TABLE TB_ZHAOZHENLONG ADD (CONSTRAINT PK_TB_ZHAOZHENLONG PRIMARY KEY(c_1,c_2,c_3);
```


```
SQL> ALERT TABLE table_name ADD CONSTRAIN T1_C1 (PRIMARY KEY(column1, column2...));

SQL> ALERT TABLE table_name MODIFY( column1 PRIMARY KEY);
```
