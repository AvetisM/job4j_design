Drop table IF EXISTS prices;
Drop table IF EXISTS goods;


Create table goods(
id serial primary key,
name varchar(255)	
);

Create table prices(
id serial primary key,
currency varchar(255),
price numeric,
goods_id int references goods(id)	
);

insert into goods (name) values ('ручка');
insert into goods (name) values ('карандаш');
insert into goods (name) values ('ластик');
insert into goods (name) values ('пенал');

insert into prices (currency, price, goods_id) values ('RUR', 50.50, 1);
insert into prices (currency, price, goods_id) values ('USD', 0.5, 1);

insert into prices (currency, price, goods_id) values ('RUR', 100, 2);
insert into prices (currency, price, goods_id) values ('USD', 0.7, 2);

insert into prices (currency, price, goods_id) values ('RUR', 30, 3);
insert into prices (currency, price, goods_id) values ('USD', 0.3, 3);

insert into prices (currency, price, goods_id) values ('RUR', 150, 4);
insert into prices (currency, price, goods_id) values ('USD', 2, 4);


select 
	g.name,
	p.price,
	p.currency
From goods as g
	join prices as p
	on g.id = p.goods_id;
	
select 
	g.name,
	p.price,
	p.currency
From goods as g
	join prices as p
	on g.id = p.goods_id
		and p.currency = 'RUR';

select 
	g.name,
	p.price,
	p.currency
From goods as g
	join prices as p
	on g.id = p.goods_id
		and p.currency = 'USD'
		and p.price > 1;
	



