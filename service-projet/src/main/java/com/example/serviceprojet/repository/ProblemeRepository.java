package com.example.serviceprojet.repository;

import com.example.serviceprojet.entity.Probleme;
import com.example.serviceprojet.entity.Projet;
import com.example.serviceprojet.entity.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository

public interface ProblemeRepository extends JpaRepository<Probleme,Long> {
 //   List<Probleme> findByProjet(Projet p);
 List<Probleme> findByProjet(Projet projet);
 List<Probleme> findByStatusFalse();
 @Query("SELECT p FROM Probleme p WHERE p.projet.idProjet = :projetId AND p.status = false")
 List<Probleme> findProblemesWithStatusFalseByProjetId(@Param("projetId") Long projetId);


}
