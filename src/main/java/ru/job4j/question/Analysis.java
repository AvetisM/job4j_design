package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analysis {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;

        HashMap<User, Integer> resultMap = new HashMap<>();
        for (User user : previous) {
            resultMap.put(user, 1);
        }
        for (User user : current) {
            if (resultMap.containsKey(user)) {
                boolean wasChanged = false;
                for (Map.Entry<User, Integer> entrySet : resultMap.entrySet()) {
                    User foundUser = entrySet.getKey();
                    if (foundUser.equals(user) && foundUser.getName() != user.getName()) {
                        resultMap.put(user, 2);
                        wasChanged = true;
                        break;
                    }
                }
                if (!wasChanged) {
                    resultMap.put(user, 0);
                }
            } else {
                resultMap.put(user, 3);
            }
        }
        for (Map.Entry<User, Integer> entrySet : resultMap.entrySet()) {
            int value = entrySet.getValue();
            if (value == 1) {
                deleted++;
            } else if (value == 2) {
                changed++;
            } else if (value == 3) {
                added++;
            }
        }
        return new Info(added, changed, deleted);
    }
}