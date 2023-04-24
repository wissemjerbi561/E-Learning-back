package com.example.serviceprojet.repository;

import com.example.serviceprojet.entity.Probleme;
import com.example.serviceprojet.entity.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

@Repository
public interface ProjetRepository extends JpaRepository<Projet,Long> {
    //@Query("select * from Projet where description like:description ")
   // List<Projet> findProjetOfDescription(@Param ("description") String description);
    //Projet findByprojet(Long idProjet);
   /// List<Probleme> findByProjets(Projet p);

    @Query("SELECT DISTINCT p FROM Projet p LEFT JOIN FETCH p.problemes")
    List<Projet> findAllFetchProblemes();



}
