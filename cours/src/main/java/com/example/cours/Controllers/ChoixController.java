package com.example.cours.Controllers;

import com.example.cours.Entities.Choix;
import com.example.cours.Services.ChoixServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cours")
public class ChoixController {
    @Autowired
    ChoixServiceImp choixService;
    @PostMapping("/ajoutChoix")
    public Choix ajouterqs(@RequestBody Choix c){
        return choixService.saveAffectChoix(c);
    }
    @GetMapping("/affichertoutChoix")
    public List<Choix> affichertoutqs(){
        return choixService.retrieveAll();
    }
    @GetMapping("/afficherchoixParQestion/{idQuestion}")
    public List<Choix> affichertoutqsByTest(@PathVariable Long idQuestion){
        return choixService.retrieveByQuestion(idQuestion);}
}
