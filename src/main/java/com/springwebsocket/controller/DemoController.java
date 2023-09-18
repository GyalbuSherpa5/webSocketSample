package com.springwebsocket.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springwebsocket.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Sinks;

/*@RestController
@CrossOrigin("*")
public class DemoController {

    private final Sinks.Many<Object> sink;
    private final ObjectMapper objectMapper;

    public DemoController(Sinks.Many<Object> sink, ObjectMapper objectMapper) {
        this.sink = sink;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/chat")
    public void demo(@RequestBody Message message) {
        String messageJson;
        try {
            messageJson = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        sink.emitNext(messageJson, Sinks.EmitFailureHandler.FAIL_FAST);
    }

}*/


@RestController
@CrossOrigin("*")
@Slf4j
public class DemoController {

    private final Sinks.Many<Object> sink;
    private final ObjectMapper objectMapper;

    public DemoController(Sinks.Many<Object> sink, ObjectMapper objectMapper) {
        this.sink = sink;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/chat/{roomName}")
    public void demo(@PathVariable String roomName, @RequestBody Message message) {
        String messageJson;
        try {
            messageJson = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        // Log the received message and room name
        System.out.println("Received message for room " + roomName + ": " + messageJson);

        // Emit the message to the sink
        sink.emitNext(messageJson, Sinks.EmitFailureHandler.FAIL_FAST);
    }
}


