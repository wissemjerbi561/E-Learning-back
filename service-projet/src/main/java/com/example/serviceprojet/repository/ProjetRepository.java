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
    List<Projet> findByStatusFalse();
    @Query("SELECT COUNT(p) FROM Projet p WHERE p.status = false")
    int countProjectsByStatusFalse();
    @Query("SELECT COUNT(p) FROM Projet p WHERE p.status = true")
    int countProjectsByStatusTrue();
    @Query(value = "SELECT p.id_projet, ARRAY_AGG(tp.id_type_phase) AS type_phases,\n" +
            "       ARRAY_AGG(ph.date_debut) AS dates_debut, ARRAY_AGG(ph.date_fin) AS dates_fin,\n" +
            "       ARRAY_AGG(ph.description) AS descriptions, ARRAY_AGG(ph.status) AS status\n" +
            "FROM projet p\n" +
            "JOIN phase ph ON p.id_projet = ph.projet_id_projet\n" +
            "JOIN type_phase tp ON ph.type_phase_id_type_phase = tp.id_type_phase\n" +
            "GROUP BY p.id_projet;\n", nativeQuery = true)
    List<Object[]> findProjetsWithTypePhasesAndDates();
 // afficher le nombre des probleme active pour les projet en cours
    @Query("SELECT p.description, COUNT(pr) " +
            "FROM Projet p " +
            "JOIN p.problemes pr " +
            "WHERE pr.status = false AND p.status = false " +
            "GROUP BY p.idProjet")
    List<Object[]> obtenirNombreProblemesParProjet();

}
