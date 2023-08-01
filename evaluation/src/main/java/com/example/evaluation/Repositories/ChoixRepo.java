package com.example.evaluation.Repositories;

import com.example.evaluation.Entities.Choix;

import com.example.evaluation.Entities.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChoixRepo extends CrudRepository<Choix,Long> {
    List<Choix> findByQuestion(Question question);
}
