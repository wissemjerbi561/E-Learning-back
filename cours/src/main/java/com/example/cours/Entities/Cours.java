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

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Cours {
     @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCours;
    private String nom;
     private String description;
     private int nbrDesInscrits;
    private int nbrDesCertifiés;
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    private float noteMoyenneSatisfaction;
    @Transient
    private Long idTheme;
    @JsonManagedReference
    @OneToMany(mappedBy = "coursav")
    private Set<AviCours> aviCours;
    @JsonManagedReference
    @OneToMany(mappedBy = "coursch")

    private Set<Chapitre> chapitres;

    @JsonManagedReference
    @OneToMany(mappedBy = "courstr")
    private Set<Tarif> tarifs;
    @JsonBackReference
    @ManyToOne
    private Theme theme;


}
