package com.example.serviceprojet.repository;

import com.example.serviceprojet.entity.Activite;
import com.example.serviceprojet.entity.Probleme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActiviteRepository  extends JpaRepository<Activite,Long> {
    List<Activite> findByProbleme(Probleme probleme);
    List<Activite> findByStatusFalse();
    @Query("SELECT a FROM Activite a WHERE a.probleme.idProbleme = :problemeId AND a.status = false")
    List<Activite> findActivitesWithStatusFalseByProblemeId(@Param("problemeId") Long problemeId);


}
