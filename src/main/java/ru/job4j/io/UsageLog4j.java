package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    @SuppressWarnings("checkstyle:OperatorWrap")
    public static void main(String[] args) {
        String name = "Avetis Mkhitaryants";
        int age = 34;
        char grade = 'A';
        float gradePrs = 79.9f;
        double salary = 180.000d;
        short group = 1123;
        byte children = 2;
        boolean married = true;
        long rate = 65L;

        LOG.debug("User info name : {}, age : {}, grade : {}, gradePrs : {}, salary : {}, rate : {}, group : {}, married : {}, children : {}",
                name,
                age,
                grade,
                gradePrs,
                salary,
                rate,
                group,
                married,
                children);
    }
}
