package com.library.repository;

import com.library.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private static Map<Integer, User> users=new HashMap<>();

    public void addUser(User user){
        users.put(user.getUserId(), user);
    }

    public User getUser(int id){
        return users.get(id);
    }
}
