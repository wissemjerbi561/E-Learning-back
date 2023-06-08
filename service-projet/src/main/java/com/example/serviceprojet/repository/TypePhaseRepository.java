package com.example.serviceprojet.repository;

import com.example.serviceprojet.entity.TypePhase;
import com.example.serviceprojet.enumeration.PhaseType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypePhaseRepository  extends JpaRepository<TypePhase,Long> {
    TypePhase findByOrdre(Integer ordre);

}
