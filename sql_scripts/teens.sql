Drop table if exists teens;

Create table teens(
	id serial primary key,
	name varchar(255),
	gender char(3)
);

insert into teens (name, gender) values ('Катя', 'жен');
insert into teens (name, gender) values ('Таня', 'жен');
insert into teens (name, gender) values ('Аня', 'жен');
insert into teens (name, gender) values ('Маша', 'жен');
insert into teens (name, gender) values ('Лиза', 'жен');
insert into teens (name, gender) values ('Яна', 'жен');

insert into teens (name, gender) values ('Петя', 'муж');
insert into teens (name, gender) values ('Коля', 'муж');
insert into teens (name, gender) values ('Вася', 'муж');
insert into teens (name, gender) values ('Ваня', 'муж');

Select
	t_male.name,
	t_female.name
From
	teens as t_male
	Cross join teens as t_female
Where t_male.gender = 'муж'
	and t_female.gender = 'жен'
		
