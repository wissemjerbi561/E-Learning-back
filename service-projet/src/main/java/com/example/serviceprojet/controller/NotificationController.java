package com.example.serviceprojet.controller;

import com.example.serviceprojet.Services.NotificationServiceImp;

import com.example.serviceprojet.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification")
@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")

public class NotificationController {
    @Autowired
    NotificationServiceImp notificationService;

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

}
