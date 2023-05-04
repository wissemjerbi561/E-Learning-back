package com.example.serviceprojet.repository;

import com.example.serviceprojet.entity.Phase;
import com.example.serviceprojet.entity.Probleme;
import com.example.serviceprojet.entity.Projet;
import com.example.serviceprojet.enumeration.PhaseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface PhaseRepository extends JpaRepository<Phase,Long> {
    Phase findByPhaseType(PhaseType phaseType);
    List<Phase> findByProjet(Projet projet);


}
