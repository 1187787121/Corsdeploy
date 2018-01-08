@echo off
echo ######该脚本仅支持DB2数据库脚本###### 
if "%1"=="" (set/p dbname=请输入数据库名并按回车:) else (set dbname=%1%)
echo ''>db2log.txt
echo ''>db2error.txt
db2 connect to %dbname% >>db2log.txt 2>>db2error.txt
db2 -tvf crt_table.sql >>db2log.txt 2>>db2error.txt
db2 -tvf ch_channel.sql >>db2log.txt 2>>db2error.txt
db2 -tvf ch_channel_srvg_priv.sql >>db2log.txt 2>>db2error.txt
db2 -tvf cm_seq.sql >>db2log.txt 2>>db2error.txt
db2 -tvf dc_dict.sql >>db2log.txt 2>>db2error.txt
db2 -tvf dc_dict_enu.sql >>db2log.txt 2>>db2error.txt
db2 -tvf rs_res.sql >>db2log.txt 2>>db2error.txt
db2 -tvf sv_srv.sql >>db2log.txt 2>>db2error.txt
db2 -tvf sv_srvg.sql >>db2log.txt 2>>db2error.txt
db2 -tvf tm_term.sql >>db2log.txt 2>>db2error.txt
db2 -tvf us_role.sql >>db2log.txt 2>>db2error.txt
db2 -tvf us_user.sql >>db2log.txt 2>>db2error.txt
db2 -tvf us_user_role.sql >>db2log.txt 2>>db2error.txt
db2 -tvf rs_opt.sql >>db2log.txt 2>>db2error.txt
db2 -tvf us_dept_role.sql >>db2log.txt 2>>db2error.txt
db2 -tvf dp_dept.sql >>db2log.txt 2>>db2error.txt
db2 -tvf priv.sql >>db2log.txt 2>>db2error.txt
echo 执行结束
pause
