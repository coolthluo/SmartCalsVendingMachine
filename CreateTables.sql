DROP TABLE IF EXISTS cards;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS sales;
DROP TABLE IF EXISTS machine_item;
DROP TABLE IF EXISTS machines;
DROP TABLE IF EXISTS items;

CREATE TABLE items (
ID INT NOT NULL PRIMARY KEY,
name VARCHAR(30),
type VARCHAR(30),
info VARCHAR(30),
picture VARCHAR(30),
price DECIMAL(10, 2),
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

insert into items values (1, "coke", "drink", "itemsinfo/item_1.html", "itemspic/item_1.jpeg", 1, "2015-07-30 00:00:00");
insert into items values (2, "sprite", "drink", "itemsinfo/item_2.html", "itemspic/item_2.jpeg", 1, "2015-07-30 00:00:00");
insert into items values (3, "lemonade", "drink", "itemsinfo/item_3.html", "itemspic/item_3.jpeg", 1.5, "2015-07-30 00:00:00");
insert into items values (4, "orange juice", "drink", "itemsinfo/item_4.html", "itemspic/item_4.jpeg", 1.5, "2015-07-30 00:00:00");
insert into items values (5, "water", "drink", "itemsinfo/item_5.html", "itemspic/item_5.jpeg", 1, "2015-07-30 00:00:00");
insert into items values (6, "diet coke", "drink", "itemsinfo/item_6.html", "itemspic/item_6.jpeg", 1, "2015-07-30 00:00:00");
insert into items values (7, "oreo", "snack", "itemsinfo/item_7.html", "itemspic/item_7.jpeg", 1, "2015-07-30 00:00:00");
insert into items values (8, "candy", "snack", "itemsinfo/item_8.html", "itemspic/item_8.jpeg", 1, "2015-07-30 00:00:00");
insert into items values (9, "chips", "snack", "itemsinfo/item_9.html", "itemspic/item_9.jpeg", 1, "2015-07-30 00:00:00");
insert into items values (10, "energy bar", "snack", "itemsinfo/item_10.html", "itemspic/item_10.jpeg", 1, "2015-07-30 00:00:00");
insert into items values (11, "chocolate", "snack", "itemsinfo/item_11.html", "itemspic/item_11.jpeg", 1, "2015-07-30 00:00:00");

insert into employees values (1, "Sam", "one", 1);
insert into employees values (2, "Tom", "two", 0);

