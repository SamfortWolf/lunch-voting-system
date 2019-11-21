delete from DISHES;
delete from MENUS;
delete from RESTAURANTS;
delete from USER_ROLES;
delete from USERS;
delete from VOTES;
-- alter sequence global_seq restart with 100000;

insert into USERS (id, name, email, password) VALUES
(0, 'User', 'user@yandex.ru', 'password'),
(1, 'Admin', 'admin@gmail.com', 'admin');

insert into USER_ROLES ("USER_ID", "ROLE") VALUES
(0, 'USER'),
(1, 'ADMIN');

insert into RESTAURANTS ("ID", "NAME", "ADDRESS") VALUES
(2, 'МакДоналдс', 'Пушкарская 1'),
(3, 'Шеймус', 'Комсомольский проспект 63');

insert into MENUS ("ID", "NAME", "RESTAURANT_ID", "DATE") VALUES
(4, 'МакЗавтрак', 2, '2019-10-20'),
(5, 'Комплексный обед', 2, current_date),
(6, 'Ирландский завтрак', 3, '2019-10-20'),
(7, 'Домашний ужин', 3, current_date);

insert into DISHES ("ID", "NAME", "PRICE", "MENU_ID") VALUES
(8, 'Чизбургер', 79, 5), (9, 'Бургер с яйцом и беконом', 99, 4),
(10, 'Картошка фри', 49, 4), (11, 'Картошка фри', 49, 5),
(12, 'Капучино', 69, 4), (13, 'Кока-Кола', 49, 5),
(14, 'Яичница', 79, 6), (15, 'Жареные сосики', 79, 6),
(16, 'Картофельное пюре', 49, 6), (17, 'Американо', 59, 6),
(18, 'Овощной суп', 79, 7),(19, 'Айриш Стью', 159, 7),
(20, 'Гиннесс', 149, 7);







