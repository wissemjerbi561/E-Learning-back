package com.example.serviceprojet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemandeAide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String Objet;
    private String Message;
    private Long SenderId;
    private Long ReceiverId;

    @ManyToOne
    @JoinColumn(name="affectationtache_id")

    private AffectationTache affectationTache;
    @Transient
    private  long affectationtacheId;
}
