#!/bin/sh
echo "######该脚本仅支持DB2数据库脚本######"
#对变量赋值(数据库名称)
#dbname=$1
#数据库名暂时固定为cvdb
dbname=cd_dep
echo "">db2log.txt
echo "">db2error.txt
db2 connect to $dbname >>db2log.txt 2>>db2error.txt
db2 -tvf crt_table.sql >>db2log.txt 2>>db2error.txt
echo 执行结束
