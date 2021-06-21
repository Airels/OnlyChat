package com.example.onlychat.model.handlers;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SocketHandler extends TextWebSocketHandler {

    private static final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    public void pingAllClients() {
        try {
            for (WebSocketSession s : sessions) {
                s.sendMessage(new TextMessage("ping"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        for (WebSocketSession s : sessions) {
            s.sendMessage(new TextMessage("Hello"));
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws IOException {
        sessions.add(session);
        session.sendMessage(new TextMessage("Connected!"));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session);
    }
}
