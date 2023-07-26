package com.membre.membre.Controllers;

import com.membre.membre.Entities.Notification;
import com.membre.membre.Services.NotificationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/notification")
@CrossOrigin("http://localhost:4200")
@Controller
public class NotificationController {
    @Autowired
    NotificationServiceImp notificationService;
    @PostMapping("/add-notification/{typeId}")
    public void ajouterNotification(@RequestBody Notification notification, @PathVariable("typeId") Long typeId) {
        notificationService.ajouterNotification(notification, typeId);


    }
    @PostMapping("/create")
    public Notification createNotification(@RequestBody Notification notification){
        return notificationService.createNotification(notification);
    }
    @GetMapping("/{notificationId}")
    public Notification getNotificationById(@PathVariable Long notificationId){
        return notificationService.getNotification(notificationId);
    }
    @GetMapping("/notifications")
    public List<Notification> getAllNotifications(){
        return notificationService.getAllNotifications();
    }
    @PutMapping("/update/{notificationId}")
    public Notification updateNotification(@PathVariable Long notificationId){
        return notificationService.updateNotification(notificationId);
    }
    @DeleteMapping("/delete/{notificationId}")
    public void deleteNotification(@PathVariable Long notificationId){
        notificationService.delete(notificationId);
    }




        @MessageMapping("/notification") // L'URL WebSocket à écouter
        @SendTo("/topic/notifications") // L'URL WebSocket à laquelle envoyer la notification
        public Notification handleNotification(Notification notification) {
            // Traitez la nouvelle notification ici
            // Diffusez la notification aux autres clients via WebSocket
            return notification;
        }
    }

