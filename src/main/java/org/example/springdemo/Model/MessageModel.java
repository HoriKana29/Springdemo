package org.example.springdemo.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@AllArgsConstructor
@Getter
@Setter
public class MessageModel {
    private UUID messageID;
    private UserModel user;
    private String message;
}
