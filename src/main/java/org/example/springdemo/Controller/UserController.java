package org.example.springdemo.Controller;

import lombok.RequiredArgsConstructor;
import org.example.springdemo.DTO.CreateUser;
import org.example.springdemo.Repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @PostMapping("/add")
    public UUID addUser(@RequestBody CreateUser createUser) {
        return userRepository.addUser(createUser.getUserName());
    }
}
