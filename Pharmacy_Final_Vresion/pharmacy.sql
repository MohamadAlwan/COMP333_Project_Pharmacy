drop database pharmacy;
create database pharmacy;

use pharmacy;
show tables;

#####################################################################################
CREATE TABLE provide_company (
    company_name VARCHAR(70) PRIMARY KEY NOT NULL,
    phone INT,
    address VARCHAR(150)
);
#####################################################################################
CREATE TABLE categores (
    cat_id INT PRIMARY KEY NOT NULL,
    categores_name VARCHAR(60),
    number_of_item INT
);
#####################################################################################
CREATE TABLE item (
    item_name VARCHAR(80),
    par_code INT NOT NULL,
    quantity INT,
    discription VARCHAR(700),
    sale_price REAL,
    origen_price REAL,
    provide_company_name VARCHAR(70),
    cat_id INT,
    exp_date DATE,
    PRIMARY KEY (par_code , provide_company_name , cat_id),
    FOREIGN KEY (provide_company_name)
        REFERENCES provide_company (company_name)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (cat_id)
        REFERENCES categores (cat_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);
#####################################################################################
CREATE TABLE employee (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    employee_name VARCHAR(60),
    birthday DATE,
    date_of_employment DATE,
    emp_password VARCHAR(30)
);
#####################################################################################
CREATE TABLE hourly_employee (
    id INT PRIMARY KEY,
    work_hours REAL,
    hour_price REAL,
    FOREIGN KEY (id)
        REFERENCES employee (id)
        ON DELETE CASCADE ON UPDATE CASCADE
);
#####################################################################################
CREATE TABLE contrect_employee (
    id INT PRIMARY KEY,
    amount_paid REAL,
    FOREIGN KEY (id)
        REFERENCES employee (id)
        ON DELETE CASCADE ON UPDATE CASCADE
);
#####################################################################################
CREATE TABLE orderes (
    order_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id INT,
    FOREIGN KEY (id)
        REFERENCES employee (id)
        ON DELETE NO ACTION ON UPDATE CASCADE
);
#####################################################################################
CREATE TABLE cashOrder (
    order_id INT PRIMARY KEY,
    order_date DATE,
    FOREIGN KEY (order_id)
        REFERENCES orderes (order_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);
#####################################################################################
CREATE TABLE inshurance_company (
    inshurance_companyName VARCHAR(32) PRIMARY KEY,
    inshurance_companyDiscount INT,
    numberOfCustomer INT DEFAULT 0
);
#####################################################################################
CREATE TABLE inshurance (
    coustumerID INT PRIMARY KEY,
    coustumerName VARCHAR(32),
    inshurance_companyName VARCHAR(32) NOT NULL,
    FOREIGN KEY (inshurance_companyName)
        REFERENCES inshurance_company (inshurance_companyName)
        ON DELETE CASCADE ON UPDATE CASCADE
);
#####################################################################################
CREATE TABLE inshuranceOrder (
    coustumer_inshurance_id INT NOT NULL,
    order_date DATE,
    order_id INT,
    PRIMARY KEY (coustumer_inshurance_id , order_id),
    FOREIGN KEY (coustumer_inshurance_id)
        REFERENCES inshurance (coustumerID)
        ON DELETE NO ACTION ON UPDATE CASCADE,
    FOREIGN KEY (order_id)
        REFERENCES orderes (order_id)
        ON DELETE CASCADE ON UPDATE CASCADE
);
#####################################################################################
CREATE TABLE bill (
    order_id INT PRIMARY KEY NOT NULL,
    order_date DATE,
    full_price REAL,
    profits REAL,
    bill_type VARCHAR(32),
    emp_id INT,
    FOREIGN KEY (emp_id)
        REFERENCES employee (id)
        ON DELETE NO ACTION ON UPDATE CASCADE,
    FOREIGN KEY (order_id)
        REFERENCES orderes (order_id)
        ON DELETE NO ACTION ON UPDATE CASCADE
);
#####################################################################################
CREATE TABLE invoice (
    quantity INT,
    full_sale_price REAL,
    full_original_price REAL,
    par_code INT NOT NULL,
    order_id INT,
    PRIMARY KEY (par_code , order_id),
    FOREIGN KEY (order_id)
        REFERENCES orderes (order_id)
        ON DELETE NO ACTION ON UPDATE CASCADE,
    FOREIGN KEY (par_code)
        REFERENCES item (par_code)
        ON DELETE NO ACTION ON UPDATE CASCADE
);
 #####################################################################################
 
insert into employee (employee_name,birthday,date_of_employment,emp_password) value ("Nathera Alwan",'1985-01-15','2010-05-16',"admin");
show tables; 

/*
 insert into hourly_employee value(3,8,15);
SELECT 
    *
FROM
    employee e,
    hourly_employee h
WHERE
    e.id = h.id;
insert into contrect_employee value (2,4800);
SELECT 
    *
FROM
    employee e,
    contrect_employee c
WHERE
    e.id = c.id;
 insert into employee (employee_name,birthday,date_of_employment,emp_password) values ("Ahlam Eldeen Asfour",'1992-01-15','2018-05-16',"root"),
 ("Mera Ahmad Shekh",'1997-08-26','2020-08-14',"root1"); 
 insert into employee (employee_name,birthday,date_of_employment,emp_password) values ("eyab",'1985-01-15','2010-05-16',"eyab");
  insert into employee (employee_name,birthday,date_of_employment,emp_password) values ("1",'1985-01-15','2010-05-16',"1");
SELECT 
    *
FROM
    employee
WHERE
    employee_name = '1';
SELECT 
    *
FROM
    employee;
 insert into item values ("Acamol",114,15,"Antipyretic and analgesic for pain accompanied by insomnia",25,15,"AL_Quds",110, '2022-04-15'),
      ("acebutolol",115,27,"Beta blockers reduce the heart rate and reduce irregularity",34,22,"Birzeit",120, '2022-03-25');
 
SELECT 
    *
FROM
    item;
SELECT 
    *
FROM
    item
WHERE
    item_name = 'acebutolol'
        OR par_code = 114;
 insert into categores values (110,"sedatives",2),(120,"heart medications",2);
SELECT 
    *
FROM
    categores;
insert into provide_company values ("AL_Quds",022406550,"Nablus Street - Al Baloua - Al-Bireh - Ramallah / Palestine"),
("Birzeit",022987572,"Shatilla Street - Ramallah / Palestine");
SELECT 
    *
FROM
    provide_company;

insert into orderes(id) value(2);
insert into orderes(id) value(2);
SELECT 
    *
FROM
    orderes;
SELECT 
    MAX(order_id)
FROM
    orderes;
SELECT 
    COUNT(order_id)
FROM
    orderes;

SELECT 
    *
FROM
    bill
ORDER BY order_id;
SELECT 
    COUNT(*)
FROM
    bill;
SELECT 
    SUM(profits)
FROM
    bill;
SELECT 
    SUM(full_price)
FROM
    bill;
SELECT 
    SUM(profits)
FROM
    bill
WHERE
    (order_date BETWEEN '2020-10-10' AND '2022-10-12'); 
*/
