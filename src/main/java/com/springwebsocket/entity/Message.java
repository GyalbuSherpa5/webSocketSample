package com.springwebsocket.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {

    private String roomId;
    private String username;
    private String message;
}
