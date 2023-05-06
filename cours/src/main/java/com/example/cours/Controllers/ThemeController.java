package com.example.cours.Controllers;

import com.example.cours.Entities.Cours;
import com.example.cours.Entities.Theme;
import com.example.cours.Services.ThemeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ThemeController {
    @Autowired
    ThemeServiceImp themeService;
    @PostMapping("/ajouttheme")
    public Theme ajouterCr(@RequestBody Theme th){
        return themeService.saveTheme(th);
    }
    @GetMapping("/affichertoutth")
    public List<Theme> affichertoutTH(){
        return themeService.retrieveAll();
    }
    @DeleteMapping("/supprimerth/{idth}")
    public void supprimercr(@PathVariable Long idth){
        themeService.delete(idth);
    }
}
