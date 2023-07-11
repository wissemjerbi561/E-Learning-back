package com.example.serviceprojet.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Member;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AffectationProjet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAffectation;
    private  String  role ;
    private boolean  existing;
  //  private long memberId;
    @ManyToOne

   // @JsonIgnore
    @JoinColumn(name="projet_id", nullable=false)
    //@JsonIgnore
    private Projet projet;
    @Transient
    private long projetId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    public AffectationProjet(Projet projet, Long memberId) {
        this.projet = projet;
        this.memberId = memberId;
    }


}
