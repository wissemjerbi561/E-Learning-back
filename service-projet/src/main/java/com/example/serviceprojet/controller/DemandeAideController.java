package com.example.serviceprojet.controller;

import com.example.serviceprojet.entity.DemandeAide;
import com.example.serviceprojet.entity.Probleme;
import com.example.serviceprojet.repository.DemandeAideRepository;
import com.example.serviceprojet.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "*")
@CrossOrigin(exposedHeaders="Access-Control-Allow-Origin")

@RequestMapping("/api/project/demandeaide")
public class DemandeAideController {

    @Autowired
    private DemandeAideRepository demandeAideRepository;


    public DemandeAideController(DemandeAideRepository demandeAideRepository) {
        this.demandeAideRepository = demandeAideRepository;
    }

    @PostMapping("/adddemande")
    public ResponseEntity<DemandeAide> savedemande(@RequestBody DemandeAide demandeAide) {
        return ResponseEntity.ok(demandeAideRepository.save(demandeAide));
    }
}
