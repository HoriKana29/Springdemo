package org.example.springdemo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class CreateMessage {
    private UUID uuid;
    private String message;
}
