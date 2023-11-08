package com.example.cours.Repositories;


import com.example.cours.Entities.Choix;
import com.example.cours.Entities.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChoixRepo extends CrudRepository<Choix,Long> {
    List<Choix> findByQuestion(Question question);
}
