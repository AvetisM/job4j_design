Drop table if exists emploers;
Drop table if exists departments;

Create table departments(
	id serial primary key,
	name varchar(255)
);

Create table emploers(
	id serial primary key,
	name varchar(255),
	department_id int references departments(id)
);

insert into departments (name) values ('dep1');
insert into departments (name) values ('dep2');
insert into departments (name) values ('dep3');
insert into departments (name) values ('dep4');

insert into emploers (name, department_id) values ('emp1', 1);
insert into emploers (name, department_id) values ('emp2', 1);
insert into emploers (name, department_id) values ('emp3', 1);
insert into emploers (name, department_id) values ('emp5', 3);
insert into emploers (name, department_id) values ('emp6', 3);
insert into emploers (name, department_id) values ('emp7', 3);
insert into emploers (name, department_id) values ('emp8', 4);
insert into emploers (name, department_id) values ('emp9', 4);
insert into emploers (name, department_id) values ('emp10', 4);
insert into emploers (name, department_id) values ('emp11', 4);

Select 
	d.name,
	e.name
From departments as d
 full join emploers as e
 	on d.id = e.department_id;
	
Select 
	d.name,
	e.name
From departments as d
 cross join emploers as e;
 
Select
 	d.name,
	e.name
From
	departments as d
	left join emploers as e
		on d.id = e.department_id
Where
	 e.name is null;
	 
Select 
	d.name,
	e.name
From departments as d
 left join emploers as e
 	on d.id = e.department_id
Order by
 	d.name,
	e.name;
	
Select 
	d.name,
	e.name
From emploers as e
 right join departments as d
 	on d.id = e.department_id
Order by
 	d.name,
	e.name;	
		 
	
	
 
