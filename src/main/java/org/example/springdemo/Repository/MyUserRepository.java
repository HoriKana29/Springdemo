package org.example.springdemo.Repository;

import org.example.springdemo.Model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MyUserRepository implements UserRepository {

    private final Map<UUID, UserModel> users = new HashMap<>();

    @Override
    public UUID addUser(String user) {
        UserModel userModel = new UserModel(UUID.randomUUID(),user);
        users.put(userModel.getUserID(),userModel);
        return userModel.getUserID();
    }

    @Override
    public UserModel getUserByID(UUID id) {
        return users.get(id);
    }
}
