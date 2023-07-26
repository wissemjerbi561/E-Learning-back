package com.example.cours.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SousCategorie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSousCategorie;
    private String nom;
    private String description;
    @Transient
    private Long idCategorie;
    @ManyToOne
    private Categorie categorie;
}
