package com.example.serviceprojet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Probleme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProbleme;
    private String description;
  private String nom;
 private Date DateDebut;
    private Date DateFin;
    @ManyToOne
    @JsonIgnore
    private Projet projet;
    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "probleme")
    private Set<Activite> activites;

}

