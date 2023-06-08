package com.example.serviceprojet.repository;

import com.example.serviceprojet.entity.Phase;
import com.example.serviceprojet.entity.Projet;
import com.example.serviceprojet.entity.Tache;
import com.example.serviceprojet.entity.TypePhase;
import com.example.serviceprojet.enumeration.PhaseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PhaseRepository extends JpaRepository<Phase,Long> {
    Phase findByPhaseType(PhaseType phaseType);
    List<Phase> findByProjet(Projet projet);

    @Query("SELECT p FROM Phase p WHERE p.typePhase = :typePhase")
    List<Phase> findByTypePhase(@Param("typePhase") TypePhase typePhase);
   // @Query("SELECT p.projet.idProjet, GROUP_CONCAT(p.typePhase.idTypePhase) FROM Phase p GROUP BY p.projet.idProjet")
  //  List<Object[]> findTypePhaseIdsByProjetId();



    Phase findByProjetAndTypePhaseOrdre(Projet projet, int ordre);


    // @Query("SELECT p FROM Phase p WHERE p.projet = :projet AND p.typePhase = :typePhase AND p.ordre = :ordre")
   // Phase findByProjetAndTypePhaseAndOrdre(@Param("projet") Projet projet, @Param("typePhase") TypePhase typePhase, @Param("ordre") int ordre);



}
