package com.example.cours.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Set;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "sousCategorie", "categorie"})
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
    @Column(length = 1000)
    private String imageUrl;
    @Transient
    private MultipartFile imageFile;
    @Transient
    private Long idTheme;
    @Transient
    private Long idCategorie;
    @Transient
    private Long idSousCategorie;
    @JsonManagedReference
    @OneToMany(mappedBy = "coursav")
    private Set<AviCours> aviCours;
    @JsonManagedReference
    @OneToMany(mappedBy = "coursch")

    private Set<Chapitre> chapitres;


    @JsonBackReference
    @ManyToOne
    private Theme theme;
    @ManyToOne
    private Categorie categorie;
    @ManyToOne
    private SousCategorie sousCategorie;
}
