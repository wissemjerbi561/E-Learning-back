package com.membre.membre.Repositories;

import com.membre.membre.Entities.EvaluationMember;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface EvaluationMemberRepository extends JpaRepository<EvaluationMember,Long> {
}
