package com.example.serviceprojet.Config;

import com.example.serviceprojet.entity.Notification;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class NotificationHandler implements WebSocketHandler {
    private final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        // Add the new session to the list
        sessions.add(session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        // Handle WebSocket messages (optional)
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        // Handle transport errors (optional)
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        // Remove the closed session from the list
        sessions.remove(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public void sendNotification(Notification notification) throws IOException {
        // Convert the notification to JSON
        String notificationJson = convertToJson(notification);

        // Send the notification to connected clients
        for (WebSocketSession session : sessions) {
            session.sendMessage(new TextMessage(notificationJson));
        }
    }

    private String convertToJson(Notification notification) {
        // Implement the logic to convert the notification object to JSON
        // using a JSON library like Jackson or Gson
        // Return the JSON string representation of the notification
        return "";
    }
}
