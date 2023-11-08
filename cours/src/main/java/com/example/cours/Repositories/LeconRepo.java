package com.example.cours.Repositories;


import com.example.cours.Entities.Lecon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeconRepo  extends CrudRepository<Lecon,Long> {
}
