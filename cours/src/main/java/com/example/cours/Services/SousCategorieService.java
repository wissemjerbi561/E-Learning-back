package com.example.cours.Services;

import com.example.cours.Entities.SousCategorie;

import java.util.List;

public interface SousCategorieService {
    public SousCategorie savescat(SousCategorie scat);

    public List<SousCategorie> retrieveAll();

    public void delete(Long idscat);
}
