package com.example.cours.Repositories;

import com.example.cours.Entities.Cours;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursRepo extends CrudRepository<Cours,Long> {
    @Query("SELECT c FROM Cours c INNER JOIN c.categorie cat WHERE cat.nom = :nomCategorie")
    List<Cours> getCoursByNomCategorie(@Param("nomCategorie") String nomCategorie);

    @Query("SELECT c FROM Cours c INNER JOIN c.sousCategorie sc WHERE sc.nom = :nomSousCategorie")
    List<Cours> getCoursByNomSousCategorie(@Param("nomSousCategorie") String nomSousCategorie);

    @Query(value = "SELECT * FROM Cours WHERE note_moyenne_satisfaction >= 3 ORDER BY note_moyenne_satisfaction DESC", nativeQuery = true)
    List<Cours> getCoursByNoteMoyenneSatisfaction();
    @Query(value = "SELECT * FROM cours WHERE actif = false", nativeQuery = true)
    List<Cours> findCoursInactifs();
}
