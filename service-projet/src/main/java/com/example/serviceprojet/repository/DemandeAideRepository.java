package com.example.serviceprojet.repository;

import com.example.serviceprojet.entity.DemandeAide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface DemandeAideRepository extends JpaRepository<DemandeAide,Long> {
}
