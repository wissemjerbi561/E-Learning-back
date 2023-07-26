package com.membre.membre.Services;

import com.membre.membre.Entities.Notification;
import com.membre.membre.Entities.Type;
import com.membre.membre.Repositories.NotificationRepository;
import com.membre.membre.Repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImp implements NotificationService {
    @Autowired
    TypeRepository typeRepository;
    @Autowired
    NotificationRepository notificationRepository;
    @Override
    public void ajouterNotification(Notification notification, Long typeId) {
        // TODO Auto-generated method stub

        Type type = typeRepository.findById(typeId).orElse(null);
        //  User User = userRepository.findById(Id).orElse(null);
        notification.setType(type);
        notificationRepository.save(notification);

    }
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
