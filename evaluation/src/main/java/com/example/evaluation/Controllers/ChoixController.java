package com.example.evaluation.Controllers;

import com.example.evaluation.Entities.Choix;
import com.example.evaluation.Entities.Question;
import com.example.evaluation.Services.ChoixServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
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
