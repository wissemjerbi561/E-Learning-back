package com.membre.membre.Repositories;

import com.membre.membre.Entities.Position;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface PositionRepository extends JpaRepository<Position,Long> {
}
