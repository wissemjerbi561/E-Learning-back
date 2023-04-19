package com.membre.membre.Repositories;

import com.membre.membre.Entities.Evaluation;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation,Long> {
}
