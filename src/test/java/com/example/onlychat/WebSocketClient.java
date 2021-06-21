package com.example.onlychat;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;

public class WebSocketClient {

    @Test
    public void connect() {
        try {
            StandardWebSocketClient webSocketClient = new StandardWebSocketClient();

            WebSocketSession webSocketSession = webSocketClient.doHandshake(new TextWebSocketHandler() {
                @Override
                public void handleTextMessage(WebSocketSession session, TextMessage message) {
                    System.out.println("received message - " + message.getPayload());
                }

                @Override
                public void afterConnectionEstablished(WebSocketSession session) {
                    System.out.println("established connection - " + session);
                }
            }, new WebSocketHttpHeaders(), URI.create("ws://localhost:8080/test")).get();

            newSingleThreadScheduledExecutor().scheduleAtFixedRate(() -> {
                try {
                    TextMessage message = new TextMessage("Hello !!");
                    webSocketSession.sendMessage(message);
                    System.out.println("sent message - " + message.getPayload());
                } catch (Exception e) {
                    System.out.println("Exception while sending a message");
                    e.printStackTrace();
                    assert(false);
                }
            }, 1, 10, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println("Exception while accessing websockets");
            e.printStackTrace();
            assert(false);
        }
    }
}
