-- Task: 1
-- Find the model number, speed and size of the hard drive for all PCs that cost less than $500. Output: model, speed and hd
SELECT MODEL, SPEED, HD
FROM PC
WHERE PRICE < 500;
-- Task: 2
-- Find the printer manufacturers. Output: maker
SELECT DISTINCT MAKER
FROM PRODUCT
WHERE TYPE = 'printer';
-- Task: 3
-- Find the model number, memory capacity and screen sizes of Laptops, the price of which exceeds $ 1,000.
SELECT MODEL, RAM, SCREEN
FROM LAPTOP
WHERE PRICE > 1000;
-- Task: 4
-- Find all the Printer table entries for Color printers.
SELECT *
FROM PRINTER
WHERE COLOR = 'C';
-- Task: 5
-- Find the model number, speed and size of the hard disk of a PC with a 12x or 24x CD and a price of less than $600.
SELECT MODEL, SPEED, HD
FROM PC
WHERE CD IN ('12x', '24x')
  AND PRICE < 600;
-- Task: 6
-- Specify the manufacturer and speed for those Laptops that have a hard disk of at least 100 GB.
SELECT P.MAKER, L.SPEED
FROM LAPTOP L
         INNER JOIN PRODUCT P ON L.MODEL = P.MODEL
WHERE L.HD >= 100;
-- Task: 7
-- Find the model numbers and prices of all products (of any type) produced by manufacturer B.
SELECT P.MODEL, PRICE
FROM PRODUCT P
         JOIN LAPTOP L ON P.MODEL = L.MODEL
WHERE P.MAKER = 'HP'
UNION
SELECT P.MODEL, PRICE
FROM PRODUCT P
         JOIN PC C ON P.MODEL = C.MODEL
WHERE P.MAKER = 'HP'
UNION
SELECT P.MODEL, PRICE
FROM PRODUCT P
         JOIN PRINTER R ON P.MODEL = R.MODEL
WHERE P.MAKER = 'HP';
-- Task: 8
-- Find a manufacturer that produces PCs, but not Laptops.
SELECT DISTINCT MAKER
FROM PRODUCT
WHERE TYPE = 'pc'
  AND MAKER NOT IN (SELECT MAKER FROM PRODUCT WHERE TYPE = 'laptop');
-- Task: 9
-- Find manufacturers of PCs with a processor of at least 450 Mhz. Output: Maker
SELECT DISTINCT MAKER
FROM PRODUCT
         join PC P2 on PRODUCT.MODEL = P2.MODEL
WHERE SPEED >= 450;
-- Task: 10
-- Find the printers that have the highest price. Output: model, price
SELECT MODEL, PRICE
FROM PRINTER
WHERE PRICE = (SELECT MAX(PRICE) FROM PRINTER);
-- Task: 11
-- Find the average PC speed.
SELECT AVG(SPEED)
FROM PC;
-- Task: 12
-- Find the average speed of laptops whose price exceeds $ 1,000.
SELECT AVG(SPEED)
FROM LAPTOP
WHERE PRICE > 1000;
-- Task: 13
-- Find the average speed of PCs released by manufacturer A.
SELECT AVG(SPEED)
FROM PRODUCT
         join PC P2 on PRODUCT.MODEL = P2.MODEL
WHERE MAKER = 'DELL';
-- Task: 14
-- For each speed value, find the average cost of a PC with the same processor speed. Bring out: speed, average price
SELECT SPEED, AVG(PRICE) AS AVERAGE_PRICE
FROM PC
GROUP BY SPEED;
-- Task: 15
-- Find the sizes of hard drives that match two or more PCs. Output: HD
SELECT HD
FROM PC
GROUP BY HD
HAVING COUNT(*) >= 2;
-- Task: 16
-- Find pairs of PC models that have the same speed and RAM. As a result, each pair is specified only once, i.e. (i,j),
--     but not (j,i), the order of output: model with a larger number, model with a smaller number, speed and RAM.
SELECT DISTINCT A.MODEL, B.MODEL, A.SPEED, A.RAM
FROM PC A,
     PC B
WHERE A.MODEL > B.MODEL
  AND A.SPEED = B.SPEED
  AND A.RAM = B.RAM;
-- Task: 17
-- Find laptop models whose speed is less than the speed of any of the PCs. Output: type, model, speed
SELECT TYPE, PRODUCT.MODEL, SPEED
FROM PRODUCT
         join LAPTOP L on PRODUCT.MODEL = L.MODEL
WHERE SPEED < ALL (SELECT SPEED FROM PC);
-- Task: 18
-- Find the manufacturers of the cheapest color printers. Output: maker, price
SELECT DISTINCT MAKER, MIN(PRICE) AS MIN_PRICE
FROM PRINTER
         join PRODUCT P2 on P2.MODEL = PRINTER.MODEL
WHERE COLOR = 'C'
GROUP BY MAKER;
-- Task: 19
-- For each manufacturer, find the average screen size of the laptops they produce. Output: maker, average screen size.
SELECT MAKER, AVG(SCREEN) AS AVERAGE_SCREEN_SIZE
FROM LAPTOP
         join PRODUCT P2 on P2.MODEL = LAPTOP.MODEL
GROUP BY MAKER;
-- Task: 20
-- Find manufacturers that produce at least three different PC models. Output: Maker, number of models
SELECT P.MAKER, COUNT(DISTINCT P.MODEL) AS NUMBER_OF_MODELS
FROM PRODUCT P
WHERE P.TYPE = 'pc'
GROUP BY P.MAKER
HAVING COUNT(DISTINCT P.MODEL) >= 3;
-- Task: 21
-- Find the maximum price of PCs produced by each manufacturer. Output: maker, maximum price.
SELECT MAKER, MAX(P.PRICE) AS MAX_PRICE
FROM PC P
         JOIN PRODUCT PR ON P.MODEL = PR.MODEL
GROUP BY MAKER;
-- Task: 22
-- For each PC speed value exceeding 600 MHz, determine the average price of a PC with the same speed. Output: speed,
-- average price.
SELECT SPEED, AVG(PRICE) AS AVERAGE_PRICE
FROM PC
WHERE SPEED > 600
GROUP BY SPEED;
-- Task: 23
-- Find manufacturers that would produce both PCs with a speed of at least 750 MHz and laptops with a speed of
-- at least 750 MHz. Output: Maker
SELECT DISTINCT p1.maker AS Maker
FROM Product p1
         INNER JOIN PC pc1 ON p1.model = pc1.model
WHERE pc1.speed >= 750
  AND p1.maker IN (
    SELECT p2.maker
    FROM Product p2
             INNER JOIN Laptop lt ON p2.model = lt.model
    WHERE lt.speed >= 750
    );
-- Task: 24
-- List the model numbers of any types that have the highest price for all products available in the database.
WITH MaxPricePerType AS (SELECT 'PC'       AS ProductType,
                                MAX(PRICE) AS MaxPrice
                         FROM PC
                         UNION ALL
                         SELECT 'PRINTER'  AS ProductType,
                                MAX(PRICE) AS MaxPrice
                         FROM PRINTER
                         UNION ALL
                         SELECT 'LAPTOP'   AS ProductType,
                                MAX(PRICE) AS MaxPrice
                         FROM LAPTOP)
SELECT M.ProductType,
       P.MODEL AS MaxPriceModel
FROM MaxPricePerType M
         INNER JOIN (SELECT 'PC' AS ProductType,
                            MODEL,
                            PRICE
                     FROM PC
                     UNION ALL
                     SELECT 'PRINTER' AS ProductType,
                            MODEL,
                            PRICE
                     FROM PRINTER
                     UNION ALL
                     SELECT 'LAPTOP' AS ProductType,
                            MODEL,
                            PRICE
                     FROM LAPTOP) P
                    ON
                                M.ProductType = P.ProductType
                            AND M.MaxPrice = P.PRICE;
-- Task: 25
-- Find the printer manufacturers that produce PCs with the least amount of RAM and with the fastest processor among
-- all PCs with the least amount of RAM. Output: Maker
SELECT DISTINCT P1.maker
FROM Product P1
         JOIN PC ON P1.model = PC.model
WHERE PC.ram = (
    SELECT MIN(ram) FROM PC
    )
  AND PC.speed = (
    SELECT MAX(speed) FROM PC WHERE ram = (
        SELECT MIN(ram) FROM PC
        )
    )
  AND P1.maker IN (
    SELECT maker FROM Product WHERE type = 'printer'
    );