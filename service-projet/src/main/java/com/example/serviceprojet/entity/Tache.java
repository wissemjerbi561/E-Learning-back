package com.example.serviceprojet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idTache;
    private String description;
    private Date DateDebut;
    private Date DateFin;
    private Float Pourcentage;
   // @ManyToOne
   // @JsonIgnore
  //  private Probleme probleme;
    //manoytooneactivites
    @ManyToOne
    @JsonIgnore
    private Activite activite;
}
