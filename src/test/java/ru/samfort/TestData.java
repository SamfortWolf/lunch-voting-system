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

    public static final Restaurant R1 = new Restaurant(TEST_SEQ+3, "McDonalds", "Pushkina 1", ADMIN_1);
    public static final Restaurant R2 = new Restaurant(TEST_SEQ+4, "Sheamus", "Komsomolsky Prospekt 63", ADMIN_2);
    public static final Restaurant CREATED_RESTAURANT = new Restaurant(TEST_SEQ+21, "TestRestaurant", "TestAddress", ADMIN_1);
    public static final Restaurant UPDATED_RESTAURANT = new Restaurant(TEST_SEQ+21, "UpdatedRestaurant", "TestAddress", ADMIN_1);

    public static final List <Restaurant> ALL_RESTAURANTS = List.of(R1, R2);
    public static final List <Restaurant> ADMIN1_RESTAURANTS = List.of(R1);

    public static final Menu M1 = new Menu(TEST_SEQ+5, "Mac Breakfast", LocalDate.parse("2019-10-20"), R1, ADMIN_1);
    public static final Menu M2 = new Menu(TEST_SEQ+6, "MacCombo lunch", LocalDate.now(), R1, ADMIN_1);
    public static final Menu M3 = new Menu(TEST_SEQ+7, "Irish Breakfast", LocalDate.parse("2019-10-20"), R2, ADMIN_2);
    public static final Menu M4 = new Menu(TEST_SEQ+8, "Home lunch", LocalDate.now(), R2, ADMIN_2);
    public static final Menu CREATED_MENU = new Menu(TEST_SEQ+21, "TestMenu", LocalDate.parse("2019-10-19"), R1, ADMIN_1);
    public static final Menu UPDATED_MENU = new Menu(TEST_SEQ+21, "UpdatedMenu", LocalDate.parse("2019-10-20"), R1, ADMIN_1);

    public static final List <Menu> ALL_MENUS = List.of(M1, M2, M3, M4);
    public static final List <Menu> R1_MENUS = List.of(M1, M2);

    public static final Dish D1 = new Dish(TEST_SEQ+9, "Cheeseburger", M2, 79, ADMIN_1);
    public static final Dish D2 = new Dish(TEST_SEQ+10, "Egg and Bacon Burger", M1, 99, ADMIN_1);
    public static final Dish D3 = new Dish(TEST_SEQ+11, "French fries", M1, 49, ADMIN_1);
    public static final Dish D4 = new Dish(TEST_SEQ+12, "French fries", M2, 49, ADMIN_1);
    public static final Dish D5 = new Dish(TEST_SEQ+13, "Cappuccino", M1, 69, ADMIN_1);
    public static final Dish D6 = new Dish(TEST_SEQ+14, "Coca-Cola", M2, 49, ADMIN_1);
    public static final Dish D7 = new Dish(TEST_SEQ+15, "Omelette", M3, 79, ADMIN_2);
    public static final Dish D8 = new Dish(TEST_SEQ+16, "Fried sausages", M3, 79, ADMIN_2);
    public static final Dish D9 = new Dish(TEST_SEQ+17, "Mashed potatoes", M3, 59, ADMIN_2);
    public static final Dish D10 = new Dish(TEST_SEQ+18, "Vegetable soup", M4, 79, ADMIN_2);
    public static final Dish D11 = new Dish(TEST_SEQ+19, "Irish stu", M4, 159, ADMIN_2);
    public static final Dish D12 = new Dish(TEST_SEQ+20, "Guinness", M4, 149, ADMIN_2);
    public static final Dish CREATED_DISH = new Dish(TEST_SEQ+21, "TestDish", M1, 149, ADMIN_1);
    public static final Dish UPDATED_DISH = new Dish(TEST_SEQ+21, "UpdatedDish", M1, 149, ADMIN_1);

    public static final List <Dish> ALL_DISHES = List.of(D1, D2, D3, D4, D5, D6, D7, D8, D9, D10, D11, D12);

    public static final List <Dish> M1_DISHES = List.of(D5, D2, D3);

    public static final Vote VOTE = new Vote(USER, R1, LocalDate.now());

}
