package com.example.cours.Controllers;

import com.example.cours.Entities.Categorie;
import com.example.cours.Services.CategorieServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategorieCotroller {
    @Autowired
    CategorieServiceImp categorieService;
    @PostMapping("/ajoutcategorie")
    public Categorie ajoutercat(@RequestBody Categorie cat){
        return categorieService.saveTheme(cat);
    }
    @GetMapping("/affichertoutcategories")
    public List<Categorie> affichertoutcat(){
        return categorieService.retrieveAll();
    }
    @DeleteMapping("/supprimercat/{idcat}")
    public void supprimercat(@PathVariable Long idcat){
        categorieService.delete(idcat);
    }
}
