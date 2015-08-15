DROP TABLE IF EXISTS cards;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS sales;
DROP TABLE IF EXISTS machine_item;
DROP TABLE IF EXISTS machines;
DROP TABLE IF EXISTS items;

CREATE TABLE items (
ID INT NOT NULL PRIMARY KEY,
name VARCHAR(30),
price DECIMAL(10, 2),
type VARCHAR(30),
calories INT,
sugar INT,
info VARCHAR(30),
picture VARCHAR(30),
lastmod DATETIME
);

CREATE TABLE machines (
ID INT NOT NULL PRIMARY KEY,
address VARCHAR(30),
startdate DATETIME,
lastsync DATETIME
);

CREATE TABLE machine_item (
machineid INT NOT NULL,
itemid INT NOT NULL, 
capacity INT,
quantity INT,
PRIMARY KEY (machineid, itemid),
FOREIGN KEY (machineid) REFERENCES machines(ID) ON DELETE CASCADE,
FOREIGN KEY (itemid) REFERENCES items(ID) ON DELETE CASCADE
);

CREATE TABLE sales (
ID INT NOT NULL AUTO_INCREMENT,
machineid INT,
itemid INT,
profit DECIMAL(10, 2),
date DATETIME,
PRIMARY KEY (ID),
FOREIGN KEY (machineid) REFERENCES machines(ID) ON DELETE CASCADE,
FOREIGN KEY (itemid) REFERENCES items(ID) ON DELETE CASCADE
);

CREATE TABLE employees (
ID INT NOT NULL PRIMARY KEY,
name VARCHAR(30),
password VARCHAR(15),
isManager INT
);

CREATE TABLE cards (
ID INT NOT NULL PRIMARY KEY,
balance DECIMAL(10, 2)
);

insert into items values (1, "Coke", 1, "drink", 182, 44, "itemsinfo/item_1.html", "itemspic/item_1.jpeg", "2015-07-30 00:00:00");
insert into items values (2, "Sprite", 1, "drink", 192, 44, "itemsinfo/item_2.html", "itemspic/item_2.jpeg", "2015-07-30 00:00:00");
insert into items values (3, "Lemonade", 1.5, "drink", 99, 25, "itemsinfo/item_3.html", "itemspic/item_3.jpeg", "2015-07-30 00:00:00");
insert into items values (4, "Orange juice", 1.5, "drink", 39, 7, "itemsinfo/item_4.html", "itemspic/item_4.jpeg", "2015-07-30 00:00:00");
insert into items values (5, "Water", 1, "drink", 0, 0, "itemsinfo/item_5.html", "itemspic/item_5.jpeg", "2015-07-30 00:00:00");
insert into items values (6, "Diet coke", 1, "drink", 1, 0, "itemsinfo/item_6.html", "itemspic/item_6.jpeg", "2015-07-30 00:00:00");
insert into items values (7, "Oreo", 1, "snack", 270, 23, "itemsinfo/item_7.html", "itemspic/item_7.jpeg", "2015-07-30 00:00:00");
insert into items values (8, "Candy", 1, "snack", 234, 24, "itemsinfo/item_8.html", "itemspic/item_8.jpeg", "2015-07-30 00:00:00");
insert into items values (9, "Chips", 1, "snack", 160, 1, "itemsinfo/item_9.html", "itemspic/item_9.jpeg", "2015-07-30 00:00:00");
insert into items values (10, "Energy bar", 1, "snack", 235, 22, "itemsinfo/item_10.html", "itemspic/item_10.jpeg", "2015-07-30 00:00:00");
insert into items values (11, "Chocolate", 1, "snack", 155, 14, "itemsinfo/item_11.html", "itemspic/item_11.jpeg", "2015-07-30 00:00:00");

insert into employees values (1, "Sam", "one", 1);
insert into employees values (2, "Tom", "two", 0);

