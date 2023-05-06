package com.example.cours.Controllers;

import com.example.cours.Entities.AviCours;
import com.example.cours.Services.AviCoursServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AviCoursController {
    @Autowired
    AviCoursServiceImp aviCoursService;
    @PostMapping("/ajoutAvicr")
    public AviCours ajouterAvi(@RequestBody AviCours av){
        return aviCoursService.saveAvi(av);
    }
    @GetMapping("/affichav/{idav}")
    public  AviCours afficherAvi(@PathVariable Long idav){
        return aviCoursService.RetrieveAvi(idav);
    }
    @GetMapping("/affichallav")
    public List<AviCours> affichertoutAvi(){
        return aviCoursService.RetrieveAllAvi();
    }
    @PutMapping("/affecterAv/{idav}/{idcr}")
    public void affecterAvicr(@PathVariable Long idav,@PathVariable Long idcr){
        aviCoursService.AffectAvi(idav,idcr);
    }
    @PostMapping("/ajoutaffectAvicr")
    public AviCours ajouteraffecterAvi(@RequestBody AviCours av){
        return aviCoursService.saveAffectAv(av);
    }
}
