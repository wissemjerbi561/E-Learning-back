package com.example.cours.Repositories;

import com.example.cours.Entities.Tarif;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifRepo extends CrudRepository<Tarif,Long> {
    @Query(value = "SELECT * FROM tarif WHERE cours_id = :id AND date_expiration IS NULL", nativeQuery = true)
    Tarif findByCoursIdAndDateExpirationNull(@Param("id") Long idCours);
}
