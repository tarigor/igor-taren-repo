#!/bin/bash

PROJECT_PATH=$(pwd)

MYSQL_PATH="/usr/bin/mysql"
DB_HOST=localhost
DB_PORT=3306
DB_USER=hoteladmin
DB_PASSWORD=abcd1234!
DB_NAME=hotel

$MYSQL_PATH -h $DB_HOST -P $DB_PORT -u $DB_USER -p$DB_PASSWORD -e "DROP DATABASE IF EXISTS $DB_NAME;"

$MYSQL_PATH -h $DB_HOST -P $DB_PORT -u $DB_USER -p$DB_PASSWORD -e "CREATE DATABASE IF NOT EXISTS $DB_NAME;"
$MYSQL_PATH -h $DB_HOST -P $DB_PORT -u $DB_USER -p$DB_PASSWORD $DB_NAME < "$PROJECT_PATH/hotel-db/sql/hotel_db_DDL.sql"
$MYSQL_PATH -h $DB_HOST -P $DB_PORT -u $DB_USER -p$DB_PASSWORD $DB_NAME < "$PROJECT_PATH/hotel-db/sql/hotel_db_filling.sql"

echo "Database created successfully."