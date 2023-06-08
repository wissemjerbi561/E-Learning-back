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
    @ManyToOne

    // @JsonIgnore
    @JoinColumn(name="tache_id", nullable=false)
    //@JsonIgnore
    private Tache tache;
    @Transient
    private long tacheId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    public AffectationTache(Tache tache) {
        this.tache = tache;
    }
}
