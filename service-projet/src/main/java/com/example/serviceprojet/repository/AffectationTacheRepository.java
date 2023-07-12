package com.example.serviceprojet.repository;

import com.example.serviceprojet.entity.AffectationTache;
import com.example.serviceprojet.entity.Projet;
import com.example.serviceprojet.entity.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface AffectationTacheRepository extends JpaRepository<AffectationTache,Long> {
  //  @Query("SELECT at FROM Tache t , affectation_tache at JOIN t.activite a JOIN a.probleme p JOIN p.projet pr WHERE pr.id = :idProjet")
  //  List<AffectationTache> getaffectationbyprojet(@Param("idProjet") Long idProjet);
  @Query("SELECT at FROM AffectationTache at JOIN at.tache t JOIN t.activite a JOIN a.probleme p JOIN p.projet pr WHERE pr.id = :idProjet")
  List<AffectationTache> findAffectationsDuProjet(@Param("idProjet") Long idProjet);


  List<AffectationTache> findByMemberId(Long memberId);













  boolean existsByTacheAndMemberId(Tache tache, Long memberId);

}
