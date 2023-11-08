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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titre;
    private String description;

    private Integer points;

    private Type type;
    private Integer duree;
    private Integer noteApprenant;
    @Transient
    private Long idCours;

    @JsonManagedReference
    @OneToMany(mappedBy = "test")
    private Set<Question> question;
    @JsonBackReference
    @ManyToOne

    private Cours courst;
}
