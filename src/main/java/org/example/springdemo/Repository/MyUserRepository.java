package org.example.springdemo.Repository;

import org.example.springdemo.Model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MyUserRepository implements UserRepository {

    private final Map<UUID, UserModel> users = new HashMap<>();
    private final Set<UUID> typingUsers = new HashSet<>();
    private int userCount = 0;

    @Override
    public UUID addUser(String user) {
        UserModel userModel = new UserModel(UUID.randomUUID(), user,true);

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

    @Override
    public List<UserModel> getAllUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public void removeUser(UUID id) {
        users.remove(id);
        typingUsers.remove(id);
    }

    @Override
    public void setTyping(UUID userID, boolean typing) {
        if (typing) typingUsers.add(userID);
        else typingUsers.remove(userID);
        System.out.println("Typing users now: " + typingUsers);

    }

    @Override
    public List<UserModel> getTypingUsers() {
        List<UserModel> result = new ArrayList<>();

        for (UUID id : typingUsers) {
            UserModel user = users.get(id);
            if (user != null) {
                result.add(user);
            }
        }

        System.out.println("Typing users: " + typingUsers);

        return result;
    }
}
