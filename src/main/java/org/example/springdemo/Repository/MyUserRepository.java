package org.example.springdemo.Repository;

import org.example.springdemo.Model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MyUserRepository implements UserRepository {

    private final Map<UUID, UserModel> users = new HashMap<>();
    private int userCount = 0;

    @Override
    public UUID addUser(String user) {
        UserModel userModel = new UserModel(UUID.randomUUID(), user);

        System.out.println("Saving user to Map: " + userModel.getUserID());

        users.put(userModel.getUserID(), userModel);

        System.out.println("Current users in Map: " + users.keySet());

        return userModel.getUserID();
    }

    @Override
    public UserModel getUserByID(UUID id) {
        System.out.println("Searching for userID: " + id);
        System.out.println("Users currently in map: " + users.keySet());
        return users.get(id);
    }

    @Override
    public int getUserCount() {
        return userCount;
    }

    @Override
    public void incrementUserCount() {
        userCount++;
    }

    @Override
    public void decrementUserCount() {
        userCount--;
    }
}
