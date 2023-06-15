package com.example.cours.Controllers;

import com.example.cours.Entities.Chapitre;
import com.example.cours.Entities.Cours;
import com.example.cours.Services.CoursServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cours")
public class CoursController {
    @Autowired
    CoursServiceImp coursService;
    @PostMapping("/ajoutcours")
    public Cours ajouterCr(@RequestBody Cours cr){
        return coursService.saveCours(cr);
    }
    @GetMapping("/affichecr/{idc}")
    public Cours afficherCr(@PathVariable Long idc){
        return coursService.retrieveCours(idc);
    }
    @GetMapping("/affichertoutCr")
    public List<Cours> affichertoutCr(){
        return coursService.retrieveAll();
    }
    @PutMapping("/modifiercr/{idc}")
    public Cours modifiercr(@PathVariable Long idc){
        return coursService.updateCr(idc);
    }
    @DeleteMapping("/supprimercr/{idc}")
    public void supprimercr(@PathVariable Long idc){
        coursService.delete(idc);
    }
    @PostMapping("/ajoutaffectcr")
    public Cours ajouteraffecterch(@RequestBody Cours cr){
        return coursService.saveAffectCr(cr);
    }
}
