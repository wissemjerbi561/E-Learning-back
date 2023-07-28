package com.example.cours.Controllers;

import com.example.cours.Entities.AviCours;
import com.example.cours.Entities.Chapitre;
import com.example.cours.Services.ChapitreServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cours")
public class ChapitreController {
    @Autowired
    ChapitreServiceImp chapitreService;
    @PostMapping("/ajoutch")
    public Chapitre ajouterch(@RequestBody Chapitre ch){
        return chapitreService.saveCh(ch);
    }
    @GetMapping("/affichch/{idch}")
    public  Chapitre afficherch(@PathVariable Long idch){
        return chapitreService.RetrieveCh(idch);
    }
    @GetMapping("/affichallch")
    public List<Chapitre> affichertoutch(){
        return chapitreService.RetrieveAllCh();
    }
    @PutMapping("/affecterch/{idch}/{idcr}")
    public void affecterAvicr(@PathVariable Long idch,@PathVariable Long idcr){
        chapitreService.AffectCh(idch,idcr);
    }
    @PostMapping("/ajoutaffectch")
    public Chapitre ajouteraffecterch(@RequestBody Chapitre ch){
        return chapitreService.saveAffectCh(ch);
    }
}
