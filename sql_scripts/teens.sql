Drop table if exists teens;

Create table teens(
	id serial primary key,
	name varchar(255),
	gender char(3)
);

insert into teens (name, gender) values ('����', '���');
insert into teens (name, gender) values ('����', '���');
insert into teens (name, gender) values ('���', '���');
insert into teens (name, gender) values ('����', '���');
insert into teens (name, gender) values ('����', '���');
insert into teens (name, gender) values ('���', '���');

insert into teens (name, gender) values ('����', '���');
insert into teens (name, gender) values ('����', '���');
insert into teens (name, gender) values ('����', '���');
insert into teens (name, gender) values ('����', '���');

Select
	t_male.name,
	t_female.name
From
	teens as t_male
	Cross join teens as t_female
Where t_male.gender = '���'
	and t_female.gender = '���'
		
