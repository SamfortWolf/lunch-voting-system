-- delete from DISHES;
-- delete from MENUS;
-- delete from RESTAURANTS;
-- delete from USER_ROLES;
-- delete from VOTES;
-- delete from USERS;
-- alter sequence global_seq restart with 100000;

insert into USERS (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password'),
       ('Admin1', 'admin1@gmail.com', 'admin1'),
       ('Admin2', 'admin2@gmail.com', 'admin2');--100002

insert into USER_ROLES ("USER_ID", "ROLE")
VALUES (100000, 'ROLE_USER'),
       (100001, 'ROLE_ADMIN'),
       (100002, 'ROLE_ADMIN');


insert into RESTAURANTS ("NAME", "ADDRESS", "OWNER_ID")
VALUES ('McDonalds', 'Pushkina 1', 100001),
       ('Sheamus', 'Komsomolsky Prospekt 63', 100002);

insert into MENUS ("NAME", "RESTAURANT_ID", "DATE", "OWNER_ID")
VALUES ('Mac Breakfast', 100003, '2019-10-20', 100001),
       ('MacCombo lunch', 100003, '2020-01-05', 100001),
       ('Irish Breakfast', 100004, '2019-10-20', 100002),
       ('Home lunch', 100004, '2020-01-06', 100002);--100008

insert into DISHES ("NAME", "PRICE", "MENU_ID", "OWNER_ID")
VALUES ('Cheeseburger', 79, 100006, 100001),
       ('Egg and Bacon Burger', 99, 100005, 100001),
       ('French fries', 49, 100005, 100001),
       ('French fries', 49, 100006, 100001),
       ('Cappuccino', 69, 100005, 100001),
       ('Coca-Cola', 49, 100006, 100001),
       ('Omelette', 79, 100007, 100002),
       ('Fried sausages', 79, 100007, 100002),
       ('Картофельное пюре', 49, 100007, 100002),
       ('Mashed potatoes', 59, 100007, 100002),
       ('Vegetable soup', 79, 100008, 100002),
       ('Irish stu', 159, 100008, 100002),
       ('Guinness', 149, 100008, 100002);







