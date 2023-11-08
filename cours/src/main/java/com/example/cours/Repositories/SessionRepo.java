package com.example.cours.Repositories;

import com.example.cours.Entities.Session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionRepo extends CrudRepository<Session,Long> {
    @Query(value = "SELECT * FROM session WHERE actif = false", nativeQuery = true)
    List<Session> findInactiveSessions();
}
