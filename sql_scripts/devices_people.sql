DROP table IF EXISTS devices_people;
DROP table IF EXISTS devices;
DROP table IF EXISTS people;

create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('ноутбук', 60000);
insert into devices(name, price) values ('телефон', 90000);
insert into devices(name, price) values ('компьютер', 40000);
insert into devices(name, price) values ('телевизор', 10000);
insert into devices(name, price) values ('приставка', 77000);

insert into people(name) values('Ivan'), ('Петр'), ('Егор'), ('Роман');

INSERT INTO devices_people(people_id, device_id) VALUES (1,1), (1, 3), (1, 5);
INSERT INTO devices_people(people_id, device_id) VALUES (2, 2), (2, 4), (2, 1), (2, 5);
INSERT INTO devices_people(people_id, device_id) VALUES (3, 1), (3, 4), (3, 5);
INSERT INTO devices_people(people_id, device_id) VALUES (4, 1), (4, 5);

select avg(price) from devices;

select 
	p.name,
	avg(d.price)
from
	devices_people as dp
	join devices as d
		on dp.device_id = d.id
	join people as p
		on dp.people_id = p.id
GROUP BY
	p.name;

select 
	p.name,
	avg(d.price)
from
	devices_people as dp
	join devices as d
		on dp.device_id = d.id
	join people as p
		on dp.people_id = p.id
GROUP BY
	p.name
HAVING avg(d.price) > 50000;

