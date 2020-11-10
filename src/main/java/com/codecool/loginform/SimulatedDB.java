package com.codecool.loginform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimulatedDB {
    private final List<User> userList;
    private final Map<Integer, User> userSessionMap;


    public SimulatedDB() {
        this.userList = new ArrayList<>();
        this.userSessionMap = new HashMap<>();
        userList.add(new User("eivor", "qwerty"));
        userList.add(new User("randy", "qwerty"));
        userList.add(new User("lahey", "qwerty"));
        userList.add(new User("samurai", "qwerty"));
    }

    public User getUserBySessionId(int sessionId) {
        return userSessionMap.get(sessionId);
    }

    public User getUserByName(String userName) {
        return userList.stream().filter(u -> u.getUserName().equals(userName)).findFirst().orElse(null);
    }

    public Map<Integer, User> getSessionUserMap() {
        return userSessionMap;
    }

}
