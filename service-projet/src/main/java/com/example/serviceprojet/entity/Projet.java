package com.example.serviceprojet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idProjet;
    private String description;
    private Date DateDebut;
    private Date DateFin;
    @ManyToOne
    @JsonIgnore
   private Cours cours;

    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "projet")
    private Set<Probleme> problemes;

}
