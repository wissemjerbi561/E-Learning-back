package com.example.cours.Services;

import com.example.cours.Entities.Categorie;

import java.util.List;

public interface CategorieService {
    public Categorie saveTheme(Categorie cat);

    public List<Categorie> retrieveAll();

    public void delete(Long idcat);
}
