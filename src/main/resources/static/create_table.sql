create table member(
id varchar(20) primary key,
password varchar(20) not null,
name varchar(10) not null,
address varchar(50),
classification varchar(15) not null
);

create table item(
id varchar(30) primary key,
name varchar(10) not null,
price integer not null,
quantity integer not null,
info varchar(100),
storefileName varchar(200),
uploadfileName varchar(200)
);

create table orderVO(
id varchar(20) primary key,
orderDate varchar(20) not null,
memberId varchar(20) not null,
address varchar(50) not null,
orderState varchar(10) not null
);

create table orderDetail(
id varchar(40) primary key,
orderId varchar(20) not null,
itemId varchar(30) not null,
itemPrice integer not null,
itemQuantity integer not null,
storefileName varchar(200),
uploadfileName varchar(200)
);

create table uploadFiles(
    id varchar(30),
    uploadFileName varchar(200),
    storeFileName varchar(200)
);