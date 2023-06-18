package com.example.cours.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInscription;
    private Long idMembre;
    private String nomMembre;
    private String prenomMembre;
    private boolean payementeffectue;
    @Transient
    private Long idSession;
    @JsonBackReference
    @ManyToOne
    private Session session;
}
