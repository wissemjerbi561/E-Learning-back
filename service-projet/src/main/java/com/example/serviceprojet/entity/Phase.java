package com.example.serviceprojet.entity;

import com.example.serviceprojet.enumeration.PhaseType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Phase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPhase;
    private String description;
    private String status;
    private Boolean etat;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;
    private PhaseType phaseType;

//manytoone projet
@ManyToOne
@JoinColumn(name="projet_id_projet")
@JsonIgnore

private Projet projet;
    @ManyToOne
    @JsonIgnore
    private TypePhase typePhase;

//manytoone nature
@ManyToOne
@JsonIgnore
private Nature nature;

}
