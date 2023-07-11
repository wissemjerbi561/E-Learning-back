package com.example.serviceprojet.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HelpService {

    private NotificationHandler notificationHandler;

    @Autowired
    public HelpService(NotificationHandler notificationHandler) {
        this.notificationHandler = notificationHandler;
    }

    public void sendHelpNotification(String message) {
        // Logique métier pour la demande d'aide

        // Envoie de la notification
        notificationHandler.sendNotification(message);
    }
}

