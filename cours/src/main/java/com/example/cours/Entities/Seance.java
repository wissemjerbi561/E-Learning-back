package com.example.cours.Entities;

import com.example.cours.CustomDateDeserializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Seance {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSeance;
    private Long contenu;
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @JsonDeserialize(using = CustomDateDeserializer.class)
    private Date heureReunion;
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    private String lienGoogleMeet;
    @Transient
    private Long idSession;
    @JsonBackReference
    @ManyToOne
    private Session sessionsc;
}
