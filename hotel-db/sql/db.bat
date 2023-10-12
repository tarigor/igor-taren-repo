@echo off

set PROJECT_PATH=%CD%
echo %PROJECT_PATH%
set MYSQL_PATH="c:\Program Files\MySQL\MySQL Server 8.1\bin\mysql.exe"
set DB_HOST=localhost
set DB_PORT=3306
set DB_USER=hoteladmin
set DB_PASSWORD=abcd1234!
set DB_NAME=hotel
echo before drop
%MYSQL_PATH% -h %DB_HOST% -P %DB_PORT% -u %DB_USER% -p%DB_PASSWORD% -e "DROP DATABASE IF EXISTS %DB_NAME%;"
%MYSQL_PATH% -h %DB_HOST% -P %DB_PORT% -u %DB_USER% -p%DB_PASSWORD% -e "CREATE DATABASE IF NOT EXISTS %DB_NAME%;"
echo db created

echo %PROJECT_PATH%\hotel_db_DDL.sql
%MYSQL_PATH% -h %DB_HOST% -P %DB_PORT% -u %DB_USER% -p%DB_PASSWORD% %DB_NAME% < %PROJECT_PATH%\hotel-db\sql\hotel_db_DDL.sql
%MYSQL_PATH% -h %DB_HOST% -P %DB_PORT% -u %DB_USER% -p%DB_PASSWORD% %DB_NAME% < %PROJECT_PATH%\hotel-db\sql\hotel_db_filling.sql

echo Database created successfully.