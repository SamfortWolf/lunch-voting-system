package ru.samfort;

import ru.samfort.model.*;

import java.time.LocalDate;
import java.util.List;

import static ru.samfort.model.AbstractBaseEntity.START_SEQ;

public class TestData {
    public static final int TEST_SEQ = START_SEQ;

    public static final User USER = new User(TEST_SEQ+0,"Test User","user@yandex.ru","password", Role.ROLE_USER);
    public static final User ADMIN_1 = new User(TEST_SEQ+1,"Test Admin1","admin1@yandex.ru","admin1", Role.ROLE_ADMIN);
    public static final User ADMIN_2 = new User(TEST_SEQ+2,"Test Admin2","admin2@yandex.ru","admin2", Role.ROLE_ADMIN);

    public static final Restaurant r1 = new Restaurant(TEST_SEQ+3, "McDonalds", "Pushkina 1", ADMIN_1);
    public static final Restaurant r2 = new Restaurant(TEST_SEQ+4, "Sheamus", "Komsomolsky Prospekt 63", ADMIN_2);

    public static final List <Restaurant> RESTAURANTS = List.of(r1,r2);

    public static final Menu m1 = new Menu(TEST_SEQ+5, "Mac Breakfast", LocalDate.parse("2019-10-20"), r1, ADMIN_1);
    public static final Menu m2 = new Menu(TEST_SEQ+6, "MacCombo lunch", LocalDate.now(), r1, ADMIN_1);
    public static final Menu m3 = new Menu(TEST_SEQ+7, "Irish Breakfast", LocalDate.parse("2019-10-20"), r2, ADMIN_2);
    public static final Menu m4 = new Menu(TEST_SEQ+8, "Home lunch", LocalDate.now(), r2, ADMIN_2);

    public static final List <Menu> MENUS = List.of(m1,m2,m3,m4);

    public static final Dish d1 = new Dish(TEST_SEQ+9, "Cheeseburger", m2, 79, ADMIN_1);
    public static final Dish d2 = new Dish(TEST_SEQ+10, "Egg and Bacon Burger", m1, 99, ADMIN_1);
    public static final Dish d3 = new Dish(TEST_SEQ+11, "French fries", m1, 49, ADMIN_1);
    public static final Dish d4 = new Dish(TEST_SEQ+12, "French fries", m2, 49, ADMIN_1);
    public static final Dish d5 = new Dish(TEST_SEQ+13, "Cappuccino", m1, 69, ADMIN_1);
    public static final Dish d6 = new Dish(TEST_SEQ+14, "Coca-Cola", m2, 49, ADMIN_1);
    public static final Dish d7 = new Dish(TEST_SEQ+15, "Omelette", m3, 79, ADMIN_2);
    public static final Dish d8 = new Dish(TEST_SEQ+16, "Fried sausages", m3, 79, ADMIN_2);
    public static final Dish d9 = new Dish(TEST_SEQ+17, "Mashed potatoes", m3, 59, ADMIN_2);
    public static final Dish d10 = new Dish(TEST_SEQ+18, "Vegetable soup", m4, 79, ADMIN_2);
    public static final Dish d11 = new Dish(TEST_SEQ+19, "Irish stu", m4, 159, ADMIN_2);
    public static final Dish d12 = new Dish(TEST_SEQ+20, "Guinness", m4, 149, ADMIN_2);

    public static final List <Dish> DISHES = List.of(d1,d2,d3,d4,d5,d6,d7,d8,d9,d10,d11,d12);

    public static final List <Vote> VOTES = List.of();

}
