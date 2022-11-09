-- 데이터베이스 생성
create database myshop1;
-- 데이터베이스 선택
use myshop1;
-- 고객 테이블 생성
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

-- 공지사항 테이블 생성
create table notice(
	notiNo int primary key auto_increment,
	title varchar(200) not null,
	content varchar(1000) not null,
	author varchar(20) not null,
	resDate datetime default now()
);
-- 공지사항 테이블 구조 보기
desc notice;

-- 공지사항의 방문횟수 컬럼 추가
alter table notice add column visited int default 0;

-- 공지사항의 방문횟수 컬럼 제거
alter table notice drop column visited;

-- 공지사항의 더미 데이터 추가
insert into notice(title, content, author) values ("테스트 제목1", "테스트1 내용입니다.", "admin");
insert into notice(title, content, author) values ("테스트 제목2", "테스트2 내용입니다.", "admin");
insert into notice(title, content, author) values ("테스트 제목3", "테스트3 내용입니다.", "admin");
insert into notice(title, content, author) values ("테스트 제목4", "테스트4 내용입니다.", "admin");
insert into notice(title, content, author) values ("테스트 제목5", "테스트5 내용입니다.", "admin");
commit;

-- 공지사항의 글번호의 내림차순 조회
select * from notice order by notiNo desc;

-- 고객 테이블 조회
select * from custom;
-- 고객 비밀번호의 암호화
update custom set cuspw="ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f" where cusid="kkt09072";
update custom set cuspw="03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4" where cusid="admin";
-- 관리자의 등급 변경
update custom set level=9 where cusid="admin";
commit;

-- 카테고리 테이블 생성
create table category(
	cateNo int primary key auto_increment,
	cateName varchar(50)
);

-- 카테고리 데이터 등록
insert into category(cateName) values ("SUIT");
insert into category(cateName) values ("OUTER");
insert into category(cateName) values ("TOP");
insert into category(cateName) values ("PANTS");
insert into category(cateName) values ("SHOES");
insert into category(cateName) values ("BAG");

select * from category;
commit;

-- 제품 테이블 생성
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
alter table product add column scnt int default 0; 
alter table product add column regdate datetime default now();
update product set regdate="2022-11-08 15:27:28" where prono>=15 and prono<=16;
commit;
select * from product;
delete from product;
drop table product;

-- 입고 테이블 생성
create table wearing(
	proNo int primary key,
    amount int
);

desc wearing;

select * from wearing;

-- 판매 테이블 생성
create table sales(
	saleNo int primary key auto_increment,
    cusId varchar(13) not null,
    proNo int not null,
    amount int not null,
    saleDate datetime default now(),
    parselNo int,
    salePayNo int
);

desc sales;
select * from sales;
select * from product where prono in (select prono from sales group by prono order by sum(amount) desc limit 4);
-- select prono from sales group by prono order by sum(amount) desc limit 4;
select * from product where prono in (select * from (select prono from sales group by prono order by sum(amount) desc limit 4) as tot);

-- 결제 테이블 생성
create table payment(
	salePayNo int primary key auto_increment,
    payMethod varchar(20),
    payCom varchar(50),
    cardNum varchar(40),
    payAmount int
);

desc payment;
select * from payment;

-- 배송 테이블 생성
create table parsel(
	parselNo int primary key auto_increment,
    parselAddr varchar(500),
    cusTel varchar(14),
    parselCompany varchar(50),
    parselTel varchar(14),
    parselState int default 0
);    
alter table parsel add column baleCode varchar(24); 

desc parsel;
select * from parsel;
drop table parsel;

create table cart(
	cartNo int primary key auto_increment,
    proNo int,
    cusId varchar(13)
);

commit;

create table qnaa(
    no int primary key auto_increment,
    title varchar(100) not null,
    content varchar(1000) not null,
    author varchar(20) not null,
    resdate datetime default now(),
    lev int default 0,            -- 깊이
    parno int not null,          -- 부모글 번호
    sec char(1)                     -- 비밀글 여부
);
insert into qnaa(title, content, author, lev, parno, sec)  values ("문의 합니다. 테스트 글1", "문의 합니다. 테스트 글1의 내용입니다.", "kkt09072", 0, 1, "N");
alter table qnaa add column visited int default 0; 
alter table qnaa modify column parno int default ;
select * from qnaa;
drop table qnaa;
select no from qnaa order by no desc limit 1;
select no from qnaa order by resdate desc limit 1;
update qnaa set parno=2 where no=2;
commit;
select no from qnaa order by resdate desc limit 1;
delete from qnaa where no >= 6;
