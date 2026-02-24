package org.example.springdemo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class EditMessage {
    private UUID userID;
    private UUID messageID;
    private String newMessage;
}