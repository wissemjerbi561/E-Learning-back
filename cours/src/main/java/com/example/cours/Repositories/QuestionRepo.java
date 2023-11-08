package com.example.cours.Repositories;


import com.example.cours.Entities.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends CrudRepository<Question,Long> {
    @Query(value = "SELECT * FROM question WHERE id_test = :idTest", nativeQuery = true)
    List<Question> findQuestionsByTestId(Long idTest);
}
