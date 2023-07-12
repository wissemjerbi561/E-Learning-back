package com.example.serviceprojet.entity;

import com.example.serviceprojet.enumeration.PhaseType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
  //  private Date DateDebut;
   // private Date DateFin;
    private Long idPhaseActuelle;
    private boolean demarre = false;
    private boolean status = false;
    private Integer memberId;
    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)
    private List<Phase> phases = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private Cours cours;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projet")
    private List<Probleme> problemes;

    public void setPhases(List<Phase> phases) {
    }

    //@ManyToMany(cascade = CascadeType.ALL)
    // @JoinTable(
    //       name = "projet_phase",
    //       joinColumns = @JoinColumn(name = "idProjet"),
    //       inverseJoinColumns = @JoinColumn(name = "idPhase")
    //)
    //private List<Phase> phases;
}

