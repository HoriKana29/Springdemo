package org.example.springdemo.DTO;

import lombok.*;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TypingMessage {
    private UUID userID;
    private boolean typing;
}
