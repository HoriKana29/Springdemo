package org.example.springdemo.Controller;

import lombok.RequiredArgsConstructor;
import org.example.springdemo.DTO.CreateUser;
import org.example.springdemo.Model.UserModel;
import org.example.springdemo.Repository.UserRepository;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final SimpMessageSendingOperations messagingTemplate;

    @PostMapping("/add")
    public UUID addUser(@RequestBody CreateUser createUser) {

        System.out.println("=== ADD USER CALLED ===");
        System.out.println("Username: " + createUser.getUsername());

        UUID id = userRepository.addUser(createUser.getUsername());
        messagingTemplate.convertAndSend("/topic/user-list", userRepository.getAllUsers());


        System.out.println("Generated UUID: " + id);

        return id;
    }

    @GetMapping("/user-number")
    public int getUserNumber() {
        return userRepository.getUserCount();
    }

    @GetMapping("/user-list")
    public List<UserModel> getAllUsers() { return userRepository.getAllUsers(); }
}
