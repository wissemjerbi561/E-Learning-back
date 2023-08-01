package com.example.evaluation.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    @NotNull
    private Integer pointsQuestion;
    private Integer noteApprenant;
    @Transient
    private Long idTest;
    @JsonBackReference
    @ManyToOne
    private Test test;
    @JsonManagedReference
    @OneToMany(mappedBy = "question")
    private Set<Choix> choix;

}
