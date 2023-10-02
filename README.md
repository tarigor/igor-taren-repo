# igor-taren-repo
Igor Taren's repository

## TASK#10

### _Relational Database_

#### Description:

### Task 10.1 (difficulty 3)

Write DDL scripts to create the database described below. Create a DML script to populate the resulting database 
with test data.

Database schema:

Description of tables:

- Product (maker, model, type)
- PC (code, model, speed, ram, hd, cd, price)
- Laptop (code, model, speed, ram, hd, screen, price)
- Printer (code, model, color, type, price)

The Product table represents the manufacturer (maker), model number (model) and type ('PC' - PC, 'Laptop' - PC-notepad 
or 'Printer' - printer). It is assumed that the model numbers in the Product table are unique for all manufacturers 
and product types.

In the PC table, for each PC, uniquely determined by a unique code – code, the model – model (the foreign key to 
the Product table), speed - speed (processor in megahertz), memory - ram (in megabytes), disk size - hd (in gigabytes), 
the speed of the reader - cd (for example, '4x') and price - price.

The Laptop table is similar to the PC table, except that instead of the CD speed, it contains the screen size 
(in inches). The Printer table for each printer model indicates whether it is color - color ('y' if color), 
printer type - type (laser – 'Laser', inkjet – 'Jet' or matrix – 'Matrix') and price - price.

### Task 10.2 (difficulty 7)

Solve all the tasks on the database created in the first task.

Task: 1

Find the model number, speed and size of the hard drive for all PCs that cost less than $500. Output: model, 
speed and hd

Task: 2

Find the printer manufacturers. Output: maker

Task: 3

Find the model number, memory capacity and screen sizes of Laptops, the price of which exceeds $ 1,000.

Task: 4

Find all the Printer table entries for Color printers.

Task: 5

Find the model number, speed and size of the hard disk of a PC with a 12x or 24x CD and a price of less than $600.

Task: 6

Specify the manufacturer and speed for those Laptops that have a hard disk of at least 100 GB.

Task: 7

Find the model numbers and prices of all products (of any type) produced by manufacturer B.

Task: 8

Find a manufacturer that produces PCs, but not Laptops.

Task: 9

Find manufacturers of PCs with a processor of at least 450 Mhz. Output: Maker

Task: 10

Find the printers that have the highest price. Output: model, price

Task: 11

Find the average PC speed.

Task: 12

Find the average speed of laptops whose price exceeds $ 1,000.

Task: 13

Find the average speed of PCs released by manufacturer A.

Task: 14

For each speed value, find the average cost of a PC with the same processor speed. Bring out: speed, average price

Task: 15

Find the sizes of hard drives that match two or more PCs. Output: HD

Task: 16

Find pairs of PC models that have the same speed and RAM. As a result, each pair is specified only once, i.e. (i,j), 
but not (j,i), the order of output: model with a larger number, model with a smaller number, speed and RAM.

Task: 17

Find laptop models whose speed is less than the speed of any of the PCs. Output: type, model, speed

Task: 18

Find the manufacturers of the cheapest color printers. Output: maker, price

Task: 19

For each manufacturer, find the average screen size of the laptops they produce. Output: maker, average screen size.

Task: 20

Find manufacturers that produce at least three different PC models. Output: Maker, number of models

Task: 21

Find the maximum price of PCs produced by each manufacturer. Output: maker, maximum price.

Task: 22

For each PC speed value exceeding 600 MHz, determine the average price of a PC with the same speed. Output: speed, 
average price.

Task: 23

Find manufacturers that would produce both PCs with a speed of at least 750 MHz and laptops with 
a speed of at least 750 MHz. Output: Maker

Task: 24

List the model numbers of any types that have the highest price for all products available in the database.

Task: 25

Find the printer manufacturers that produce PCs with the least amount of RAM and with the fastest processor among all 
PCs with the least amount of RAM. Output: Maker

### Task Requirements:

a separate branch is created for the task in the repository;

in GIT should be added:

script for creating a database;

script for filling the database with valid data;

script with 25 requests separated by comments with the task number.

the database must be filled in such a way that at least one record corresponds to each sample.