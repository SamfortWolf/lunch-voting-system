delete from DISHES;
delete from MENUS;
delete from RESTAURANTS;
delete from USER_ROLES;
delete from USERS;
delete from VOTES;
-- alter sequence global_seq restart with 100000;

insert into USERS (name, email, password) VALUES
('User', 'user@yandex.ru', 'password'),
('Admin', 'admin@gmail.com', 'admin');

insert into USER_ROLES ("USER_ID", "ROLE") VALUES
(100000, 'USER'),
(100001, 'ADMIN');

insert into RESTAURANTS ("NAME", "ADDRESS") VALUES
('МакДоналдс', 'Пушкарская 1'),
('Шеймус', 'Комсомольский проспект 63');

insert into MENUS ("NAME", "RESTAURANT_ID", "DATE") VALUES
('МакЗавтрак', 100002, '2019-10-20'),
('Комплексный обед', 100002, current_date),
('Ирландский завтрак', 100003, '2019-10-20'),
('Домашний ужин', 100003, current_date);--100007

insert into DISHES ("NAME", "PRICE", "MENU_ID") VALUES
('Чизбургер', 79, 100005),
('Бургер с яйцом и беконом', 99, 100004),
('Картошка фри', 49, 100004),
('Картошка фри', 49, 100005),
('Капучино', 69, 100004),
('Кока-Кола', 49, 100005),
('Яичница', 79, 100006),
('Жареные сосики', 79, 100006),
('Картофельное пюре', 49, 100006),
('Американо', 59, 100006),
('Овощной суп', 79, 100007),
('Айриш Стью', 159, 100007),
('Гиннесс', 149, 100007);







