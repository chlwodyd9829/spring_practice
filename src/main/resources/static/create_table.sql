create table member(
id varchar(20) primary key,
password varchar(20) not null,
name varchar(10) not null,
address varchar(50),
classification varchar(15) not null
);

create table item(
id bigint primary key auto_increment,
name varchar(10) not null,
price integer not null,
quantity integer not null,
info varchar(100)
);

create table orderVO(
id varchar(20) primary key,
orderDate Date not null,
memberId varchar(20) not null,
address varchar(50) not null,
orderState varchar(10) not null
);

create table orderDetail(
id varchar(20) primary key,
orderId varchar(20) not null,
itemId bigint not null,
itemPrice integer not null,
itemQuantity integer not null
);
