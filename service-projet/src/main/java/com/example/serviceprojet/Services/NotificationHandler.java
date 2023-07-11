package com.example.serviceprojet.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationHandler {

    private SimpMessagingTemplate messagingTemplate;



    public void sendNotification(String message) {
        messagingTemplate.convertAndSend("/topic/notifications", message); // Envoyer la notification à tous les abonnés
    }
}
