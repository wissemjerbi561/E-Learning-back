package com.example.cours.Controllers;

import com.example.cours.Entities.SousCategorie;
import com.example.cours.Services.SousCategorieServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cours")
public class SousCategorieController {
    @Autowired
    SousCategorieServiceImp sousCategorieService;
    @PostMapping("/ajoutsouscategorie")
    public SousCategorie ajoutercat(@RequestBody SousCategorie cat){
        return sousCategorieService.savescat(cat);
    }
    @GetMapping("/affichertoutsouscategories")
    public List<SousCategorie> affichertoutcat(){
        return sousCategorieService.retrieveAll();
    }
    @DeleteMapping("/supprimersouscat/{idscat}")
    public void supprimercat(@PathVariable Long idscat){
        sousCategorieService.delete(idscat);
    }
}
