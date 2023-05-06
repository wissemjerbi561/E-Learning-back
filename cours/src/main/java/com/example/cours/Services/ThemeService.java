package com.example.cours.Services;

import com.example.cours.Entities.Cours;
import com.example.cours.Entities.Theme;

import java.util.List;

public interface ThemeService {
    public Theme saveTheme(Theme th);

    public List<Theme> retrieveAll();

    public void delete(Long idth);
}
