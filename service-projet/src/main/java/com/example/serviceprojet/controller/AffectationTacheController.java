package com.example.serviceprojet.controller;

import com.example.serviceprojet.Services.AffectationTacheServiceImpl;
import com.example.serviceprojet.entity.*;
import com.example.serviceprojet.repository.AffectationTacheRepository;
import com.example.serviceprojet.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
//@CrossOrigin(origins = "*")
@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")

@RequestMapping("/project/affectationtache")
public class AffectationTacheController {
    @Autowired
    AffectationTacheRepository affectationTacheRepository;
    @Autowired
    ProjetRepository projetRepository;
    @Autowired
    AffectationTacheServiceImpl affectationTacheService;

    @PostMapping("/add")
    public void ajouterAffectationTache(@RequestBody AffectationTache affectationTache){
        affectationTacheService.ajouterAffectationTache(affectationTache);


    }
    @GetMapping("/{idAffectationTache}")
    public AffectationTache getAffectationTacheById(@PathVariable Long idAffectAionTache) {
        return affectationTacheRepository.findById(idAffectAionTache).orElse(null);
    }
    //@GetMapping("/idProjet")
    //public List<AffectationTache> getAffectationsByProjet(@PathVariable Long idProjet) {
        //Projet projet = projetRepository.findById(idProjet).orElseThrow(() -> new NoSuchElementException("projet non trouvé"));

      //  return affectationTacheService.getAffectationsByProjet(projet);
    //}
    @GetMapping("/exists")
    public ResponseEntity<Boolean> verifierAffectationExistante(@RequestParam Long idTache, @RequestParam Long memberId) {
        boolean affectationExistante = affectationTacheService.verifierAffectationExistante(idTache, memberId);
        return ResponseEntity.ok(affectationExistante);
    }


    @GetMapping
    public ResponseEntity getAllAffectationsTaches() {
        return ResponseEntity.ok(this.affectationTacheRepository.findAll());
    }
}
