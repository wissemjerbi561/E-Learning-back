package com.example.cours.Controllers;

import com.example.cours.Entities.Chapitre;
import com.example.cours.Entities.Seance;
import com.example.cours.Services.SeanceServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cours")
public class SeanceController {
    @Autowired
    SeanceServiceImp seanceService;
    @PostMapping("/ajoutsc")
    public Seance ajouterch(@RequestBody Seance s){
        return seanceService.savese(s);
    }
    @GetMapping("/affichsc/{ids}")
    public  Seance afficherch(@PathVariable Long ids){
        return seanceService.RetrieveSe(ids);
    }
    @GetMapping("/affichallsc")
    public List<Seance> affichertouts(){
        return seanceService.RetrieveAllse();
    }
    /*@PutMapping("/affectersc/{ids}/{idcr}")
    public void affecterseance(@PathVariable Long ids,@PathVariable Long idcr){
        seanceService.Affectse(ids, idcr);
    }*/
    @DeleteMapping("/deletesc/{ids}")
    public void supprimersc(@PathVariable Long ids){
        seanceService.deleteSc(ids);
    }
    @PostMapping("/ajoutaffectsc")
    public Seance ajouteraffectch(@RequestBody Seance s){
        return seanceService.saveAffectSc(s);
    }
}
