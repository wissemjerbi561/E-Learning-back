package com.example.serviceprojet.repository;

import com.example.serviceprojet.entity.AffectationProjet;
import com.example.serviceprojet.entity.Probleme;
import com.example.serviceprojet.entity.Projet;
import com.example.serviceprojet.entity.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.List;

@Repository
@EnableJpaRepositories
public interface AffectationProjetRepository  extends JpaRepository<AffectationProjet,Long> {
    AffectationProjet findByProjetAndMemberId(Projet projet, Member member);
    @Query("SELECT t FROM AffectationProjet t JOIN t.projet pr WHERE pr.id = :idProjet")
    List<AffectationProjet> findMembersDuProjet(@Param("idProjet") Long idProjet);
    //  AffectationProjet findByProjetAndMembreId(Projet projet, Long membreId);

  //  List<AffectationProjet> findByMembreId(Long membreId);

   // List<AffectationProjet> findByProjetId(Long idProjet);
}
