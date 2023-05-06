package com.example.cours.Repositories;

import com.example.cours.Entities.Chapitre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapitreRepo extends CrudRepository<Chapitre,Long> {
}
