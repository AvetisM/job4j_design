create table students (
	id serial primary key,
	name varchar(255),
	age integer,
	specialty text
);
insert into students(name, age, specialty) values ('Ivanov Ivan', '18', 'dantist');

update students set age = '21';

delete from students;





