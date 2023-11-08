package com.example.cours.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
import java.util.TimerTask;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Session {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSession;

    private int nbrDesInscrits;
    private int nbrDesCertifiés;
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    private boolean active;
    private Integer capacite;
    private boolean actif=false;
@ManyToMany
    Set<Cours> courss;
    @JsonManagedReference
    @OneToMany(mappedBy = "sessionsc")

    private Set<Seance> seances;
    @JsonManagedReference
    @OneToMany(mappedBy = "session")

    private Set<Inscription> inscriptions;


}
