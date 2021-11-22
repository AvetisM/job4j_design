package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class UserTest {

    public static void main(String[] args) {

        Calendar birthday = new GregorianCalendar(1987, 11, 03);
        User user1 = new User("Тест", 1, birthday);
        User user2 = new User("Тест", 1, birthday);

        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());

        for (Map.Entry<User, Object> item : map.entrySet()) {
            System.out.printf("Key: %s  Value: %s \n", item.getKey(), item.getValue());
        }
    }
}

