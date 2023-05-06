package com.example.cours.Repositories;

import com.example.cours.Entities.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepo extends CrudRepository<Session,Long> {
}
