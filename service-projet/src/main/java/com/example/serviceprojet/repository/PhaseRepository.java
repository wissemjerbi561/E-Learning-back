package com.example.serviceprojet.repository;

import com.example.serviceprojet.entity.Phase;
import com.example.serviceprojet.entity.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PhaseRepository extends JpaRepository<Phase,Long> {
}
