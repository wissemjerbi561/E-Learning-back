package com.example.cours.Services;

import com.example.cours.Entities.Categorie;
import com.example.cours.Entities.SousCategorie;
import com.example.cours.Repositories.CategorieRepo;
import com.example.cours.Repositories.SousCategorieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SousCategorieServiceImp implements SousCategorieService{
    @Autowired
    SousCategorieRepo sousCategorieRepo;
    @Autowired
    CategorieRepo categorieRepo;
    @Override
    public SousCategorie savescat(SousCategorie scat) {
        sousCategorieRepo.save(scat);
        Categorie categorie=categorieRepo.findById(scat.getIdCategorie()).get();
        scat.setCategorie(categorie);
        return sousCategorieRepo.save(scat);
    }

    @Override
    public List<SousCategorie> retrieveAll() {
        List<SousCategorie> sousCategories=(List<SousCategorie> ) sousCategorieRepo.findAll();
        return sousCategories;
    }

    @Override
    public void delete(Long idscat) {
sousCategorieRepo.deleteById(idscat);
    }
}
