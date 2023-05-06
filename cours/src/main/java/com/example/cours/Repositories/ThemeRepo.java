package com.example.cours.Repositories;

import com.example.cours.Entities.Theme;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThemeRepo extends CrudRepository<Theme,Long> {
}
