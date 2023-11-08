package com.example.cours.Controllers;

import com.example.cours.Entities.Cours;
import com.example.cours.Entities.Lecon;
import com.example.cours.Services.LeconServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cours")
public class LeconController {
    @Autowired
    LeconServiceImp leconService;
    @PostMapping("/ajoutaffectlecon")
    public Lecon ajouteraffecterlecon(@RequestBody Lecon l) {
        return leconService.saveAffectLecon(l);
    }

    @PostMapping("/updateLecon")
    public ResponseEntity<Lecon> updateCours(@RequestBody Lecon updatedLecon) {
        Lecon updatedLECON = leconService.updateLecon(updatedLecon);

        if (updatedLECON != null) {
            return ResponseEntity.ok(updatedLECON);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/afficherlecon/{idl}")
    public Lecon afficherlecon(@PathVariable Long idl) {
        return leconService.retrieveLecon(idl);
    }

    @GetMapping("/affichertoutlecons")
    public List<Lecon> affichertoutlecon() {
        return leconService.retrieveAll();
    }
    @DeleteMapping("/supprimerlecon/{idl}")
    public void supprimerlecon(@PathVariable Long idl) {
        leconService.delete(idl);
    }
}
