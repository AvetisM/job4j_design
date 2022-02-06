DROP TABLE students;
DROP TABLE specialty;
DROP TABLE student;
DROP TABLE studentGroup;
DROP TABLE studentId;

create table studentGroup (
	id serial primary key,
	name varchar(255)
);
insert into studentGroup(name) values ('PO');
insert into studentGroup(name) values ('PI');

create table studentId (
	id serial primary key,
	name varchar(255)
);
insert into studentId(name) values ('MAZ 87342');
insert into studentId(name) values ('IAL 87421');

create table student (
	id serial primary key,
	name varchar(255),
	age int,
	group_id int references studentGroup(id),
 	studentId_id int references studentId(id) unique
);
insert into student(name, age, group_id, studentId_id) values ('Mkhitaryants Avetis', 33, 1, 1);
insert into student(name, age, group_id, studentId_id) values ('Ivanov Alexei', 30, 2, 2);

create table specialty (
	id serial primary key,
	name text
);

insert into specialty(name) values ('developer');
insert into specialty(name) values ('economist');

create table students (
	id serial primary key,
	student_id int references student(id),
	specialty_id int references specialty(id)
);

insert into students(student_id, specialty_id) values (1, 1);
insert into students(student_id, specialty_id) values (2, 2);





