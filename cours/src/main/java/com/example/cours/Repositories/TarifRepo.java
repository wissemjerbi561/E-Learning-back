package com.example.cours.Repositories;

import com.example.cours.Entities.Tarif;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifRepo extends CrudRepository<Tarif,Long> {
}
