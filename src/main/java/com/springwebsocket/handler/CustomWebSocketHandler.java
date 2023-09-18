package com.springwebsocket.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Component
public class CustomWebSocketHandler implements WebSocketHandler {

    private final Sinks.Many<Object> sink;

    public CustomWebSocketHandler(Sinks.Many<Object> sink) {
        this.sink = sink;
    }

    // send message
    @Override
    public Mono<Void> handle(WebSocketSession session) {
        var message = sink.asFlux()
                .map(sessionMessage -> session.textMessage(sessionMessage.toString()));

        return session.send(message);
    }
}

/*@Component
public class CustomWebSocketHandler implements WebSocketHandler {

    private final Sinks.Many<Object> sink;
    private final ChatRoomManager chatRoomManager;

    public CustomWebSocketHandler(Sinks.Many<Object> sink, ChatRoomManager chatRoomManager) {
        this.sink = sink;
        this.chatRoomManager = chatRoomManager;
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        String roomName = session.getHandshakeInfo().getUri().getQuery();
        System.out.println("Room Name: " + roomName);

        if (roomName != null) {
            chatRoomManager.addSessionToRoom(roomName, session);
        } else {
            System.out.println("Room error");
        }

        var message = sink.asFlux()
                .map(sessionMessage -> session.textMessage(sessionMessage.toString()));

        // Log messages and other relevant information for debugging
        message = message.doOnNext(msg -> {
            System.out.println("Received message from sink: " + msg.getPayloadAsText());
        });

        return session.send(message)
                .doFinally(signalType -> chatRoomManager.removeSessionFromRoom(roomName, session));
    }
}*/


