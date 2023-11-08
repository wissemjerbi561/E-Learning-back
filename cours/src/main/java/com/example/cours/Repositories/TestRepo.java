package com.example.cours.Repositories;


import com.example.cours.Entities.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepo extends CrudRepository<Test,Long> {
}
