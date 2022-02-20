Drop table if exists car;
Drop table if exists body;
Drop table if exists engine;
Drop table if exists transmission;

Create table body(
 id serial primary key,
 name varchar(255)
);
 
Create table engine(
 id serial primary key,
 name varchar(255)
);

Create table transmission(
 id serial primary key,
 name varchar(255)
);
 
Create table car(
 id serial primary key,
 name varchar(255),
 body_id int references body(id),
 engine_id int references engine(id),
 transmission_id int references transmission(id)
); 
 
insert into body (name) values ('седан'), ('лифтбек'), ('универсал'), ('кроссовер');
insert into engine (name) values ('1.2'), ('1.4'), ('1.6'), ('2.0'), ('3.0');
insert into transmission (name) values ('МКП'), ('АКП'), ('Вариатор'), ('Роботизированная');

insert into car (name, body_id, engine_id, transmission_id) values ('wv tiguan 2.0 МКП', 4, 4, 1);
insert into car (name, body_id, engine_id, transmission_id) values ('wv tiguan 2.0 DSG', 4, 4, 4);
insert into car (name, body_id, engine_id, transmission_id) values ('wv tiguan 3.0 АКП', 4, 5, 2);

insert into car (name, body_id, engine_id, transmission_id) values ('wv polo 1.6 МКП', 1, 3, 1);
insert into car (name, body_id, engine_id, transmission_id) values ('wv polo 1.4 DSG', 1, 2, 4);
insert into car (name, body_id, engine_id, transmission_id) values ('wv polo 1.6 АКП', 1, 3, 2);

select 
	c.name,
	b_car.name,
	e_car.name,
	t_car.name
 from car as c
 	left join body as b_car
		on c.body_id = b_car.id
	left join engine as e_car
		on c.engine_id = e_car.id
	left join transmission as t_car
		on c.transmission_id = t_car.id;	
		
select 
	b_car.name
from body as b_car
	left join car as c
	on b_car.id = c.body_id
where c.id is null;	

select 
	e_car.name
from engine as e_car
	left join car as c
	on e_car.id = c.engine_id
where c.id is null;

select 
	t_car.name
from transmission as t_car
	left join car as c
	on t_car.id = c.engine_id
where c.id is null;
	