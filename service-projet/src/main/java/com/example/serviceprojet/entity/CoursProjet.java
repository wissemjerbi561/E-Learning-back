package com.example.serviceprojet.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoursProjet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;



    //@ManyToOne
    // @JsonIgnore
//    private Projet projet;
}
