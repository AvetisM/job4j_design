Drop table IF EXISTS comments;
Drop table IF EXISTS attachs;
Drop table IF EXISTS item;
Drop table IF EXISTS role_and_rules;
Drop table IF EXISTS users;
Drop table IF EXISTS rules;
Drop table IF EXISTS category;
Drop table IF EXISTS state;
Drop table IF EXISTS role;

create table rules (
	id serial primary key,
	name varchar(255)
);

create table category (
	id serial primary key,
	value text
);

create table state (
	id serial primary key,
	name varchar(255),
	value int
);

create table role (
	id serial primary key,
	name varchar(255)
);

create table users (
	id serial primary key,
	name varchar(255),
	role_id int references role(id)
);

create table role_and_rules (
	id serial primary key,
	role_id int references role(id),
	rules_id int references rules(id)
);

create table item (
	id serial primary key,
	name text,
	users_id int references users(id),
	category_id int references category(id),
	state_id int references state(id)
);

create table comments (
	id serial primary key,
	name text,
	item_id int references item(id)
);

create table attachs (
	id serial primary key,
	value text,
	item_id int references item(id)
);




