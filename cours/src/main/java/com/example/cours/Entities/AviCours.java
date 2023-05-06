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
public class AviCours {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAvi;
    private String nomAppr;
    private String textAvi;
    private int noteSatisfaction;
    @Transient
    private Long idCours;
    @JsonBackReference
    @ManyToOne
    private Cours coursav;
}
