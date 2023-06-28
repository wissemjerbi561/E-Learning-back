package com.notification.Repositories;

import com.notification.Entities.Notification;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
