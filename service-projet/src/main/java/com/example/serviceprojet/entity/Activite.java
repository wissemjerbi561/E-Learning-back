package com.example.serviceprojet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idActivite;
    private String nom;
    private String description;
    private Date DateDebut;
    private Date DateFin;
    private int dure;
    private boolean status= false; ;

    @ManyToOne
    @JsonIgnore
    private Probleme probleme;

    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "activite")
    private Set<Tache> taches;
}