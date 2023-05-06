package com.example.cours.Repositories;

import com.example.cours.Entities.Seance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeanceRepo extends CrudRepository<Seance,Long> {
}
