create database myshop1;
commit;
use myshop1;
create table custom(
	cusId varchar(13) primary key,	
	cusPw varchar(200) not null,
	cusName varchar(50) not null,
	address varchar(500) not null,
	tel varchar(14) not null,
	regDate datetime default now(),
	point int default 0,
	level int default 0,
	visited int default 0
);

create table notice(
	notiNo int primary key auto_increment,
	title varchar(200) not null,
	content varchar(1000) not null,
	author varchar(20) not null,
	resDate datetime default now()
);

insert into notice(title, content, author) values ("테스트 제목1", "테스트1 내용입니다.", "admin");
insert into notice(title, content, author) values ("테스트 제목2", "테스트2 내용입니다.", "admin");
insert into notice(title, content, author) values ("테스트 제목3", "테스트3 내용입니다.", "admin");
insert into notice(title, content, author) values ("테스트 제목4", "테스트4 내용입니다.", "admin");
insert into notice(title, content, author) values ("테스트 제목5", "테스트5 내용입니다.", "admin");
commit;

select * from notice order by notiNo desc;

select * from custom;

update custom set cuspw="ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f" where cusid="kkt09072";
update custom set cuspw="03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4" where cusid="admin";
update custom set level=9 where cusid="admin";
commit;

create table category(
	cateNo int primary key auto_increment,
	cateName varchar(50)
);

insert into category(cateName) values ("SUIT");
insert into category(cateName) values ("OUTER");
insert into category(cateName) values ("TOP");
insert into category(cateName) values ("PANTS");
insert into category(cateName) values ("SHOES");
insert into category(cateName) values ("BAG");

select * from category;
commit;

create table product(
	proNo int primary key auto_increment,
	cateNo int not null,
	proName varchar(40) not null,
	proSpec varchar(500),
	oriPrice int not null,
	discountRate double not null,
	proPic varchar(200),
	proPic2 varchar(200)
);

commit;

select * from product;

delete from product;

drop table product;