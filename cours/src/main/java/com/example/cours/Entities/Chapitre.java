package com.example.cours.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Chapitre {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChapitre;
    private String descriptionCh;
    @Transient
    private Long idCours;
    @JsonBackReference
    @ManyToOne

    private Cours coursch;
    @JsonManagedReference
    @OneToMany(mappedBy = "chapitre")

    private Set<Lecon> lecons;
}
