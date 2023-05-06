package com.example.cours.Repositories;

import com.example.cours.Entities.Cours;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursRepo extends CrudRepository<Cours,Long> {
}
