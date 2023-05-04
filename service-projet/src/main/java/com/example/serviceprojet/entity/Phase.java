package com.example.serviceprojet.entity;

import com.example.serviceprojet.enumeration.PhaseType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Phase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPhase;
    private Boolean etat;
    private PhaseType phaseType;

//manytoone projet
@ManyToOne
@JsonIgnore
private Projet projet;

//manytoone nature
@ManyToOne
@JsonIgnore
private Nature nature;

}
