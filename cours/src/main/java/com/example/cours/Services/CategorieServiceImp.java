package com.example.cours.Services;

import com.example.cours.Entities.Categorie;
import com.example.cours.Repositories.CategorieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieServiceImp implements CategorieService{
    @Autowired
    CategorieRepo categorieRepo;
    @Override
    public Categorie saveTheme(Categorie cat) {
        return categorieRepo.save(cat);
    }

    @Override
    public List<Categorie> retrieveAll() {
        List<Categorie> categories=(List<Categorie>)    categorieRepo.findAll();
        return categories;
    }

    @Override
    public void delete(Long idcat) {
        categorieRepo.deleteById(idcat);
    }

}
