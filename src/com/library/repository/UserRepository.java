package com.library.repository;

import com.library.exception.DuplicateException;
import com.library.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {
    private static Map<Integer, User> users=new HashMap<>();

    public void addUser(User user) {
        if (users.containsKey(user.getUserId())) {
            throw new DuplicateException("User with UserID "+ user.getUserId() +" already exists");
        }
        users.put(user.getUserId(), user);
    }
    public User getUser(int id){
        return users.get(id);
    }
    public boolean userExists(int userId) {
        return users.containsKey(userId);
    }
}
