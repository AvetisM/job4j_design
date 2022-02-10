insert into role(name) values('разработчик');
insert into role(name) values('тестеровщик');
insert into role(name) values('аналитик');
insert into role(name) values('постановщик задач');

insert into rules(name) values('просмотр');
insert into rules(name) values('редактирование');
insert into rules(name) values('создание');

insert into category(value) values('Важная');
insert into category(value) values('Срочная');
insert into category(value) values('Не срочная');
insert into category(value) values('Не важная');

insert into state(name) values('Создана');
insert into state(name) values('В работе');
insert into state(name) values('На тестировании');
insert into state(name) values('Выполнена');

insert into users(name, role_id) values('Иванов', 1);
insert into users(name, role_id) values('Петров', 2);
insert into users(name, role_id) values('Васильева', 3);
insert into users(name, role_id) values('Сидоров', 4);

insert into role_and_rules (role_id, rules_id) values(1, 2);
insert into role_and_rules (role_id, rules_id) values(2, 2);
insert into role_and_rules (role_id, rules_id) values(3, 3);
insert into role_and_rules (role_id, rules_id) values(4, 3);

insert into item(name, users_id, category_id, state_id) values('Реализовать функционал 1', 1, 1, 2);
insert into item(name, users_id, category_id, state_id) values('Протестировать задачу 1', 2, 3, 1);

insert into comments(name, item_id) values('выполнить задачу 1', 1);
insert into comments(name, item_id) values('выполнить задачу 2', 2);

insert into attachs(value, item_id) values('описание задачи 1', 1);
insert into attachs(value, item_id) values('описание задачи 2', 2);





