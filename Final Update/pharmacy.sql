create database pharmacy;


use pharmacy;



show tables;

create table provide_company( company_name varchar(70) primary key not null, phone int 
, address varchar(150));
insert into provide_company values ("AL_Quds",022406550,"Nablus Street - Al Baloua - Al-Bireh - Ramallah / Palestine"),
("Birzeit",022987572,"Shatilla Street - Ramallah / Palestine");
select * from provide_company;





create table categores( cat_id int primary key not null, categores_name varchar(60),
 number_of_item int);
 insert into categores values (110,"sedatives",2),(120,"heart medications",2);
 select * from categores;
 
 
 
 
 
 
 
create table item(item_name varchar(80),
par_code int not null,
quantity int,
discription varchar(700),
 sale_price real,
 origen_price real,
provide_company_name varchar(70),
 cat_id int, 
 exp_date date, 
 primary key(par_code,provide_company_name,cat_id),
foreign key(provide_company_name) references provide_company(company_name) on delete cascade on update cascade,
foreign key(cat_id) references categores(cat_id) on delete cascade on update cascade);
 
 
 insert into item values ("Acamol",114,15,"Antipyretic and analgesic for pain accompanied by insomnia",25,15,"AL_Quds",110, '2022-04-15'),
      ("acebutolol",115,27,"Beta blockers reduce the heart rate and reduce irregularity",34,22,"Birzeit",120, '2022-03-25');
 
 select * from item;
select * from item where item_name='acebutolol' or par_code=114;


 create table employee(id int primary key not null auto_increment, employee_name varchar(60),
 birthday date, date_of_employment date, emp_password varchar(30));
 insert into employee (employee_name,birthday,date_of_employment,emp_password) values ("Nathera Alwan",'1985-01-15','2010-05-16',"admin"),("Ahlam Eldeen Asfour",'1992-01-15','2018-05-16',"root"),
 ("Mera Ahmad Shekh",'1997-08-26','2020-08-14',"root1"); 
 insert into employee (employee_name,birthday,date_of_employment,emp_password) values ("eyab",'1985-01-15','2010-05-16',"eyab");
  insert into employee (employee_name,birthday,date_of_employment,emp_password) values ("1",'1985-01-15','2010-05-16',"1");
select * from employee where employee_name = "1";
 select * from employee;

  
 create table hourly_employee(id int primary key, work_hours real, hour_price real,
 foreign key(id) references employee(id) on delete cascade
 on update cascade);
 insert into hourly_employee value(3,8,15);
 select * from employee e,hourly_employee h  where e.id=h.id;
 
 create table contrect_employee(id int primary key,amount_paid real,
 foreign key(id) references employee(id) on delete cascade
 on update cascade);
insert into contrect_employee value (2,4800);
 select * from employee e,contrect_employee c  where e.id=c.id;

 create table orderes(
 order_id int primary key not null auto_increment,
 id int ,
 foreign key(id) references employee(id) on delete no action
 on update cascade);
 
insert into orderes(id) value(2);
insert into orderes(id) value(2);


 select * from orderes;
select MAX(order_id) from orderes;
select count(order_id) from orderes;
 
 
 
 create table cashOrder(
 order_id int primary key,
 order_date date,
 foreign key(order_id) references orderes(order_id) on delete cascade
 on update cascade);

 create table inshuranceOrder(
 coustumer_inshurance_id int not null,
order_date date,
 order_id int, primary key(coustumer_inshurance_id,order_id),
  foreign key(coustumer_inshurance_id) references inshurance(coustumerID) 
  on delete no action on update cascade,
  foreign key(order_id) references orderes(order_id) on delete cascade
 on update cascade);
 
 
 drop table inshuranceOrder,inshurance,inshurance_company;
 
 create table inshurance_company(
inshurance_companyName varchar(32) primary key,
inshurance_companyDiscount int,
numberOfCustomer int default 0
);
create table inshurance(
coustumerID int primary key,
coustumerName varchar(32),
inshurance_companyName varchar(32) not null,
foreign key (inshurance_companyName) references inshurance_company(inshurance_companyName)
on delete cascade on update cascade
);
 
 
 
 create table bill(
order_id int  primary key not null,
order_date date,
full_price real,
profits real,
bill_type varchar(32),
 emp_id int ,
 foreign key(emp_id) references employee(id) on delete no action
 on update cascade,
foreign key(order_id) references orderes(order_id) 
 on delete no action on update cascade);
 

 
  
 create table invoice( 
 quantity int, 
 full_sale_price real,
 full_original_price real,
 par_code int not null,
 order_id int,
 primary key(par_code,order_id),
 foreign key(order_id) references orderes(order_id) 
 on delete no action on update cascade,
 foreign key(par_code) references item(par_code) 
 on delete no action on update cascade);
 
 
 # insert into invoice(quantity , full_sale_price ,full_original_price ,par_code,order_id) values (1,2,3,114,30);
  #select * from invoice  where  order_id=101;
  #select * from invoice where order_id=160;
  #delete from invoice where  order_id=95;
  #update  invoice set quantity = 11 where order_id = 160 and par_code =1;
  
  
 # insert into bill(order_id,order_date,full_price,profits,bill_type,emp_id) values(41,'2020-11-10',1200,140,"cash",1),(42,'2020-11-11',1200,555,"cash",1),(43,'2020-11-11',1200,344,"cash",1),(44,'2020-11-11',1200,200,"cash",1);
 #insert into bill(order_id,order_date,full_price,profits,bill_type,emp_id) values(74,'2020-10-10',1200,140,"cash",1);
 #insert into bill(order_id,order_date,full_price,profits,bill_type,emp_id) values(75,'2020-10-11',1200,60,"cash",1);
 #insert into bill(order_id,order_date,full_price,profits,bill_type,emp_id) values(76,'2020-10-12',1200,100,"cash",1);
select * from bill order by order_id;
select count(*) from bill;
select sum(profits) from bill;
select sum(full_price) from bill;
select sum(profits) from bill where (order_date BETWEEN '2020-10-10'and '2022-10-12'); 

