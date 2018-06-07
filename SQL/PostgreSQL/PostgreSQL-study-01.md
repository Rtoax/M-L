# PostgreSQL的使用（1）
## 一.如何使用PostgreSQL
* 安装-略
* 登陆PostgreSQL
```shell
$ su
# su - postgres
$ 
```
这时就进入了PostgreSQL的安装目录/opt/PostgreSQL/10/
* 创建一个数据库，并连接数据库
```shell
$ createdb testdb
$ psql testdb
testdb=#
```
之后你就会一直在testdb=#下操作，当一个语句没有写完时为“testdb-#”，写完时为“testdb=#”，写完的标志为语句以“;”结束。
## 二.简单的数据库操作
* 创建一个TABLE
```sql
create table t(
    name text not null,
    age integer,
    sex text,
    score numeric check (score < 100.0)
);
create table t2(
    name text primary key,
    age integer,
    sex text,
    score numeric check(score < 100.0)
);
create table t3(
    name text not null primary key,
    age integer,
    sex text,
    score numeric check(score < 100.0)
);
create table t4(
    name text,
    age integer default 18,
    sex text,
    score numeric check(score < 100.0),
    primary key(name, age)
);

```
上面的not null、primary key和check为约束，default为缺省值。
* 显示一个TABLE
```sql
table t;
 name | age | sex | score 
------+-----+-----+-------
(0 rows)
```
* 删除一个TEBLE
```sql
drop table t;
```
* 增加字段
```sql
alter table t4 add column degree text;
alter table t4 drop score;
table t4;
```
输出为：
```
 name | age | sex | degree 
------+-----+-----+--------
(0 rows)
```
* 修改字段名称、表名
```sql
alter table t4 rename column name to rong;
alter table t4 rename to t5;
table t5;
```
输出为：
```
 rong | age | sex | degree 
------+-----+-----+--------
(0 rows)
```
* 选择一个列
```sql
select rong from t5;
 rong 
------
(0 rows)

select rong,sex from t5;
 rong | sex 
------+-----
(0 rows)
``
