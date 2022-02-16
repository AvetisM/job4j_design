DROP TABLE IF EXISTS  product;
DROP TABLE IF EXISTS  type;

CREATE TABLE type (
	id serial primary key,
	name  varchar(100)
);

CREATE TABLE product(
	id serial primary key,
	name varchar(255),
	type_id int REFERENCES TYPE(id),
	expired_date date,
	price numeric
);

insert into type(name) values ('Сыр'), ('Мясо'), ('Хлеб'), ('Молоко'), ('Мороженое');

insert into product(name, type_id, expired_date, price) values ('Гауда', 1, '01.03.2022', 500);
insert into product(name, type_id, expired_date, price) values ('Ламбер', 1, '25.02.2022', 600);
insert into product(name, type_id, expired_date, price) values ('Говядина', 2, '17.02.2022', 1000);
insert into product(name, type_id, expired_date, price) values ('Свинина', 2, '17.02.2022', 900);
insert into product(name, type_id, expired_date, price) values ('Батон', 3, '15.02.2022', 50);
insert into product(name, type_id, expired_date, price) values ('Простоквашино', 4, '14.02.2022', 75);
insert into product(name, type_id, expired_date, price) values ('Молочная легенда', 4, '18.02.2022', 90);
insert into product(name, type_id, expired_date, price) values ('Мороженое Забава', 5, '28.02.2022', 100);
insert into product(name, type_id, expired_date, price) values ('Мороженое Щербет', 5, '17.03.2022', 80);

Select 
	p.name
From product as p
Inner Join type as t
	On p.type_id = t.id
Where t.name = 'Сыр';

Select 
	p.name
From product as p
Where p.name like '%Мороженое%';

Select 
	p.name
From product as p
Where p.expired_date < current_date;

Select
	p.name,
	p.price
From product as p
Order By 
	p.price DESC
limit 1;

Select
	t.name,
	count(p.id)
From product as p
	Inner Join type as t
		On t.id = p.type_id
Group By
	t.name;

Select 
	p.name
From product as p
Inner Join type as t
	On p.type_id = t.id
Where t.name = 'Сыр' or t.name = 'Молоко';

Select
	t.name,
	count(p.id)
From product as p
	Inner Join type as t
		On t.id = p.type_id
Group By
	t.name
Having count(p.id) < 10;	
	
Select 
	p.name,
	t.name
From product as p
Inner Join type as t
	On p.type_id = t.id;
