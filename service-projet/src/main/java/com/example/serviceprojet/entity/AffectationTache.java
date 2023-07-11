package com.example.serviceprojet.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AffectationTache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAffectationTache;
    private Date DateDebut;
    private Date DateFin;
    //  private long memberId;
    @OneToOne

    // @JsonIgnore
    @JoinColumn(name="tache_id", nullable=false)
    //@JsonIgnore
    private Tache tache;
    @Transient
    private long tacheId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @ManyToOne
   @JoinColumn(name="projet_id")

   private Projet projet;
    @Transient
    private  long projetId;


    //public AffectationTache(Tache tache) {
        //this.tache = tache;
   // }


}
