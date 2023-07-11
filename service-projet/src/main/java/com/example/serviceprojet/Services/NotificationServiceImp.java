package com.example.serviceprojet.Services;

import com.example.serviceprojet.entity.Notification;
import com.example.serviceprojet.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class NotificationServiceImp implements NotificationService {
    @Autowired
    NotificationRepository notificationRepository;
    @Override
    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Notification getNotification(Long notificationId) {
        return notificationRepository.findById(notificationId).get();
    }

    @Override
    public List<Notification> getAllNotifications() {
        List<Notification> listNotifications= notificationRepository.findAll();
        return listNotifications;
    }

    @Override
    public Notification updateNotification(Long notificationId) {
         Notification notification= notificationRepository.findById(notificationId).get();
         return notificationRepository.save(notification);
    }

    @Override
    public void delete(Long notificationId) {
        Notification notification= notificationRepository.findById(notificationId).get();
        notificationRepository.delete(notification);

    }
}
