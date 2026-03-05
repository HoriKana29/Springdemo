package org.example.springdemo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DeleteMessage {
    private UUID messageID;
    private UUID userID;
}
