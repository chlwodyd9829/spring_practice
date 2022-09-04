create table member(
id varchar(20) primary key,
password varchar(20) not null,
name varchar(10) not null,
address varchar(50)
);

create table item(
id bigint primary key auto_increment,
name varchar(10) not null,
price integer not null,
quantity integer not null,
info varchar(100)
);
