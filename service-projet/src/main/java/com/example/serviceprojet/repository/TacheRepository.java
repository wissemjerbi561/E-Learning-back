package com.example.serviceprojet.repository;

import com.example.serviceprojet.entity.Activite;
import com.example.serviceprojet.entity.Probleme;
import com.example.serviceprojet.entity.Projet;
import com.example.serviceprojet.entity.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface TacheRepository  extends JpaRepository<Tache,Long> {
    List<Tache> findByActivite(Activite activite);

    @Query ("SELECT t FROM Tache t JOIN t.activite a JOIN a.probleme p JOIN p.projet pr WHERE pr.id = :idProjet")
    List<Tache> findTachesDuProjet(@Param("idProjet") Long idProjet);
    @Query("SELECT t FROM Tache t  JOIN t.activite a JOIN a.probleme p JOIN p.projet pr WHERE pr.id = :idProjet  AND t.status = false AND NOT EXISTS (SELECT at FROM AffectationTache at WHERE at.tache = t)")
    List<Tache> findTachesNonAffecteesAuProjet(@Param("idProjet") Long idProjet);
    List<Tache> findByStatusFalse();

    @Query("SELECT t FROM Tache t WHERE t.activite.idActivite = :activiteId AND t.status = false")
    List<Tache> findTachesWithStatusFalseByActiviteId(@Param("activiteId") Long activiteId);

  /*  @Query("SELECT t FROM Tache t LEFT JOIN FETCH t.projet")
    List<Tache> findAllWithProjet();*/
  /*@Query("SELECT t, pr FROM Tache t " +
          "JOIN  t.activite a " +
          "JOIN  a.probleme p " +
          "LEFT JOIN  p.projet pr ")
    List<Tache> findAllTachesWithProjet();*/
   /* @Query  (value = "SELECT t.*, pr.id_Projet FROM Tache t, activite a , probleme p , projet pr " +
            "WHERE   t.activite_id_activite= a.id_activite " +
            "AND  a.probleme_id_probleme= p.id_probleme " +
            "AND  p.projet_id_projet=pr.id_projet  ",nativeQuery = true)
    List<Tache> findAllTachesWithProjet();*/




}
