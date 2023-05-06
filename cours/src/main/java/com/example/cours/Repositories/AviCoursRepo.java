package com.example.cours.Repositories;

import com.example.cours.Entities.AviCours;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AviCoursRepo extends CrudRepository<AviCours,Long> {
}
