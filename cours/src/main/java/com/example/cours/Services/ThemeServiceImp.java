package com.example.cours.Services;

import com.example.cours.Entities.Theme;
import com.example.cours.Repositories.ThemeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThemeServiceImp implements ThemeService{
    @Autowired
    ThemeRepo themeRepo;
    @Override
    public Theme saveTheme(Theme th) {
        return themeRepo.save(th);
    }

    @Override
    public List<Theme> retrieveAll() {
        List<Theme> themes=(List<Theme>)themeRepo.findAll();
        return themes;
    }

    @Override
    public void delete(Long idth) {
        themeRepo.deleteById(idth);
    }
}
