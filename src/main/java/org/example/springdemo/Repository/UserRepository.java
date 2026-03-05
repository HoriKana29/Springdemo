package org.example.springdemo.Repository;

import org.example.springdemo.Model.UserModel;

import java.util.List;
import java.util.UUID;

public interface UserRepository {
    UUID addUser(String user);

    UserModel getUserByID(UUID id);

    int getUserCount();

    void incrementUserCount();

    void decrementUserCount();

    List<UserModel> getAllUsers();

    void removeUser(UUID id);

    void setTyping(UUID userID, boolean typing);

    List<UserModel> getTypingUsers();
}
