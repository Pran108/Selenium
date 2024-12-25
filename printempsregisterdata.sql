create database printemps;
use printemps;
create table registration(email varchar(50), password varchar(100));
insert into registration values('mijic76079456@skrak1.com', 'SuperSecretPassword!123?');
select * from registration where email = 'mijic76079456@skrak1.com';
ALTER TABLE registration
ADD username varchar(100),
ADD userlastname varchar(100);
DELETE FROM registration
WHERE email = 'mijic76079456@skrak1.com';
select * from registration;
insert into registration values('mijic76079456@skrak1.com', 'SuperSecretPassword!123?', 'Test', 'Test');
select * from registration where email = 'mijic76079456@skrak1.com';




