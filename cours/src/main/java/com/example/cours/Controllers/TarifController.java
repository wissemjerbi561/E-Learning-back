package com.example.cours.Controllers;

import com.example.cours.Entities.Chapitre;
import com.example.cours.Entities.Tarif;
import com.example.cours.Services.TarifServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cours")
public class TarifController {
    @Autowired
    TarifServiceImp tarifService;
    @PostMapping("/ajoutaffecttarif")
    public Tarif ajouteraffecterch(@RequestBody Tarif tr){
        return tarifService.saveAffectCh(tr);
    }
    @GetMapping("/affichalltarif")
    public List<Tarif> affichertoutch(){
        return tarifService.RetrieveAllCh();
    }
    @GetMapping("/affichtr/{idtr}")
    public  Tarif afficherch(@PathVariable Long idtr){
        return tarifService.RetrieveCh(idtr);
    }
    @DeleteMapping("/supprimertr/{idtr}")
    public void supprimertr(@PathVariable Long idtr){
        tarifService.deleteCh(idtr);
    }
    @GetMapping("/affichtarifCourant/{idcr}")
    public  Tarif affichertrCourant(@PathVariable Long idcr){
        return tarifService.getTarifCourantParCours(idcr);
    }
}
