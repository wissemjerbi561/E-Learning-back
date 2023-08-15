package com.membre.membre.Repositories;

import com.membre.membre.Entities.Position;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface PositionRepository extends JpaRepository<Position,Long> {
    List<Position> findByStatusFalse();
    @Query("SELECT p FROM Position p WHERE p.code = :code")
    Position findByCode(String code);


    @Query("SELECT p FROM Position p WHERE p.name LIKE 'Apprenant%'")
    List<Position> getListApprenant();
}
