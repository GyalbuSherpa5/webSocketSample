package com.springwebsocket.config;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatRoomManager {

    private final Map<String, List<WebSocketSession>> chatRooms = new ConcurrentHashMap<>();

    public void addSessionToRoom(String roomName, WebSocketSession session) {
        chatRooms.computeIfAbsent(roomName, k -> new ArrayList<>()).add(session);
    }

    public void removeSessionFromRoom(String roomName, WebSocketSession session) {
        chatRooms.getOrDefault(roomName, Collections.emptyList()).remove(session);
    }

    public List<WebSocketSession> getSessionsInRoom(String roomName) {
        return chatRooms.getOrDefault(roomName, Collections.emptyList());
    }
}

