-- delete from DISHES;
-- delete from MENUS;
-- delete from RESTAURANTS;
-- delete from USER_ROLES;
-- delete from VOTES;
-- delete from USERS;
-- alter sequence global_seq restart with 100000;

insert into USERS (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin1', 'admin1@gmail.com', 'admin'),
       ('Admin2', 'admin2@gmail.com', 'admin');--100002

insert into USER_ROLES ("USER_ID", "ROLE")
VALUES (100000, 'USER'),
       (100001, 'ADMIN'),
       (100002, 'ADMIN');


insert into RESTAURANTS ("NAME", "ADDRESS", "OWNER_ID")
VALUES ('МакДоналдс', 'Пушкарская 1', 100001),
       ('Шеймус', 'Комсомольский проспект 63', 100002);

insert into MENUS ("NAME", "RESTAURANT_ID", "DATE", "OWNER_ID")
VALUES ('МакЗавтрак', 100003, '2019-10-20', 100001),
       ('Комплексный обед', 100003, current_date, 100001),
       ('Ирландский завтрак', 100004, '2019-10-20', 100002),
       ('Домашний ужин', 100004, current_date, 100002);--100008

insert into DISHES ("NAME", "PRICE", "MENU_ID", "OWNER_ID")
VALUES ('Чизбургер', 79, 100006, 100001),
       ('Бургер с яйцом и беконом', 99, 100005, 100001),
       ('Картошка фри', 49, 100005, 100001),
       ('Картошка фри', 49, 100006, 100001),
       ('Капучино', 69, 100005, 100001),
       ('Кока-Кола', 49, 100006, 100001),
       ('Яичница', 79, 100007, 100002),
       ('Жареные сосики', 79, 100007, 100002),
       ('Картофельное пюре', 49, 100007, 100002),
       ('Американо', 59, 100007, 100002),
       ('Овощной суп', 79, 100008, 100002),
       ('Айриш Стью', 159, 100008, 100002),
       ('Гиннесс', 149, 100008, 100002);







