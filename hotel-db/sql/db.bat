@echo off

set PROJECT_PATH=%CD%

set MYSQL_PATH="C:\Program Files\MySQL\MySQL Workbench 8.0\mysql.exe"
set DB_HOST=localhost
set DB_PORT=3306
set DB_USER=hoteladmin
set DB_PASSWORD=abcd1234!
set DB_NAME=hotel

%MYSQL_PATH% -h %DB_HOST% -P %DB_PORT% -u %DB_USER% -p%DB_PASSWORD% -e "DROP DATABASE IF EXISTS %DB_NAME%;"

%MYSQL_PATH% -h %DB_HOST% -P %DB_PORT% -u %DB_USER% -p%DB_PASSWORD% -e "CREATE DATABASE IF NOT EXISTS %DB_NAME%;"
%MYSQL_PATH% -h %DB_HOST% -P %DB_PORT% -u %DB_USER% -p%DB_PASSWORD% %DB_NAME% < %PROJECT_PATH%/hotel-db/sql/hotel_db_DDL.sql
%MYSQL_PATH% -h %DB_HOST% -P %DB_PORT% -u %DB_USER% -p%DB_PASSWORD% %DB_NAME% < %PROJECT_PATH%/hotel-db/sql/hotel_db_filling.sql

echo Database created successfully.