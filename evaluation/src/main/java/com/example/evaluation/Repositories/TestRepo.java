package com.example.evaluation.Repositories;

import com.example.evaluation.Entities.Test;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepo extends CrudRepository<Test,Long> {
}
