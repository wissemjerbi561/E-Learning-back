package com.notification.Services;

import com.notification.Entities.Notification;

import java.util.List;


public interface NotificationService {

    public Notification createNotification(Notification notification);
    public Notification getNotification(Long notificationId);
    public List<Notification> getAllNotifications();
    public Notification updateNotification(Long notificationId);
    public void delete(Long notificationId);


}
