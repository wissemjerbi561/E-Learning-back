package com.example.serviceprojet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notification")
public class Notification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;
    private  Long objectId;
    private String recipientName;
    private  String title;
    private String senderName;
    private String description;

    @ManyToOne
    @JoinColumn(name = "type_id",referencedColumnName = "typeId")
    private Type type;
}
