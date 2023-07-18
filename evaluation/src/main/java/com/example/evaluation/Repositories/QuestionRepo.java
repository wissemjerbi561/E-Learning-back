package com.example.evaluation.Repositories;

import com.example.evaluation.Entities.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepo extends CrudRepository<Question,Long> {
}
