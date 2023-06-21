package com.example.cours.Controllers;

import com.example.cours.Entities.Inscription;
import com.example.cours.Services.InscriptionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cours")
public class InscriptionController {
    @Autowired
    InscriptionServiceImp inscriptionService;
    @PostMapping("/ajoutaffectins")
    public Inscription ajouteraffecterch(@RequestBody Inscription ins){
        return inscriptionService.saveAffectInscription(ins);
    }
}
