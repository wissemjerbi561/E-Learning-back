package com.example.serviceprojet.controller;

import com.example.serviceprojet.Services.ActiviteServiceImpl;
import com.example.serviceprojet.entity.Activite;
import com.example.serviceprojet.entity.Projet;
import com.example.serviceprojet.repository.ActiviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Activity")
public class ActiviteController {
    @Autowired
    ActiviteRepository activiteRepository;
    @Autowired
    ActiviteServiceImpl activiteService;

    public ActiviteController(ActiviteRepository activiteRepository) {
        this.activiteRepository = activiteRepository;
    }

    @GetMapping
    public ResponseEntity getAllActivites() {
        return ResponseEntity.ok(this.activiteRepository.findAll());
    }
    @GetMapping("/{idActivite}")
    public Activite getActiviteById(@PathVariable Long idActivite) {
        return activiteRepository.findById(idActivite).orElse(null);
    }





    @PutMapping("/{idActivite}")
    public ResponseEntity<Activite> updateactivite(@PathVariable Long idActivite, @RequestBody Activite activite) {
        Activite activite1 = activiteRepository.findById(idActivite).orElseThrow(() -> new ResourceNotFoundException("projet not exist with id" + idActivite));
        activite1.setDateDebut(activite.getDateDebut());
        activite1.setDateFin(activite.getDateFin());
        activite1.setDescription(activite.getDescription());
        Activite updateactivite = activiteRepository.save(activite1);
        return  ResponseEntity.ok(updateactivite);
    }
    @DeleteMapping("/{idActivite}")
    public void deleteActiviteById(@PathVariable Long idActivite) {
        activiteRepository.deleteById(idActivite);
    }

    @PostMapping("/add-activite/{idProbleme}")
    public void ajouterActivite(@RequestBody Activite activite,@PathVariable ("idProbleme") Long idProbleme){
        activiteService.ajouterActivite(activite, idProbleme);


    }}