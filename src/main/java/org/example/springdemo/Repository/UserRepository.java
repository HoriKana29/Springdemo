package org.example.springdemo.Repository;

import org.example.springdemo.Model.UserModel;

import java.util.UUID;

public interface UserRepository {
    UUID addUser(String user);
    UserModel getUserByID(UUID id);
    int getUserCount();
    void incrementUserCount();
    void decrementUserCount();
}
