package com.example.serviceprojet.controller;

import com.example.serviceprojet.Services.AffectationProjetServiceImpl;
import com.example.serviceprojet.entity.AffectationProjet;
import com.example.serviceprojet.entity.Projet;
import com.example.serviceprojet.repository.AffectationProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Member;

@RestController
//@CrossOrigin(origins = "*")
@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")

@RequestMapping("/api/project/affectation")
public class AffectaionProjet {

    @Autowired
    private AffectationProjetRepository affectationProjetRepository;

    @Autowired
    private AffectationProjetServiceImpl affectationProjetService;

    @PostMapping("/add")
    public void ajouterAffectation(@RequestBody AffectationProjet affectationProjet){

        affectationProjetService.ajouterAffectation(affectationProjet);



    }
    @PostMapping("/addtuteuracademique")

    public void  ajouterAffectationtuteuracademique(@RequestBody AffectationProjet affectationProjet){
        affectationProjetService.ajouterAffectationtuteuracademique(affectationProjet);


    }
    @PostMapping("/addapprenantverification")

    public void ajouterAffectationApprenantVerification(@RequestBody AffectationProjet affectationProjet){
        affectationProjetService.ajouterAffectationApprenantVerification(affectationProjet);


    }

    @PostMapping("/addapprenantaide")

    public void ajouterAffectationApprenantDaide(@RequestBody AffectationProjet affectationProjet){
        affectationProjetService.ajouterAffectationApprenantDaide(affectationProjet);


    }






}
